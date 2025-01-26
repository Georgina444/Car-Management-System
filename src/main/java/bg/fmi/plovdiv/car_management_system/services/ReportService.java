package bg.fmi.plovdiv.car_management_system.services;

import bg.fmi.plovdiv.car_management_system.data.model.MaintenanceEntity;
import bg.fmi.plovdiv.car_management_system.data.responses.GarageDailyAvailabilityResponse;
import bg.fmi.plovdiv.car_management_system.data.responses.MaintenanceMonthlyRequestsResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportService {

    private final GarageService garageService;
    private final MaintenanceService maintenanceService;

    public ReportService(GarageService garageService, MaintenanceService maintenanceService) {
        this.garageService = garageService;
        this.maintenanceService = maintenanceService;
    }

    public List<GarageDailyAvailabilityResponse> getDailyAvailabilityReport(Long garageId, LocalDate startDate, LocalDate endDate) {
        List<GarageDailyAvailabilityResponse> responses = new ArrayList<>();
        List<MaintenanceEntity> maintenanceList = maintenanceService.getMaintenanceEntitiesByGarageIdAndScheduledDateBetween(garageId, startDate, endDate);
        Map<LocalDate, Long> dailyRequests = maintenanceList.stream()
                .collect(Collectors.groupingBy(
                        MaintenanceEntity::getScheduledDate,
                        Collectors.counting()
                ));

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            int requestsForTheDay = dailyRequests.getOrDefault(currentDate, 0L).intValue();
            int remainingCapacity = garageService.getGarageCapacity(garageId) - (int) requestsForTheDay;
            responses.add(new GarageDailyAvailabilityResponse(currentDate, requestsForTheDay, remainingCapacity));
            currentDate = currentDate.plusDays(1);
        }

        return responses;
    }

    public List<MaintenanceMonthlyRequestsResponse> getMonthlyMaintenanceReport(Long garageId, String startDate, String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate + "-01");
        LocalDate endLocalDate = LocalDate.parse(endDate + "-01").plusMonths(1).minusDays(1);
        List<MaintenanceEntity> maintenanceList = maintenanceService.getMaintenanceEntitiesByGarageIdAndScheduledDateBetween(garageId, startLocalDate, endLocalDate);
        Map<YearMonth, Long> monthlyRequests = maintenanceList.stream()
                .collect(Collectors.groupingBy(
                        maintenance -> YearMonth.from(maintenance.getScheduledDate()),
                        Collectors.counting()
                ));

        List<MaintenanceMonthlyRequestsResponse> responses = new ArrayList<>();
        for (Map.Entry<YearMonth, Long> entry : monthlyRequests.entrySet()) {
            responses.add(new MaintenanceMonthlyRequestsResponse(entry.getKey(), entry.getValue().intValue()));
        }

        return responses;
    }
}
