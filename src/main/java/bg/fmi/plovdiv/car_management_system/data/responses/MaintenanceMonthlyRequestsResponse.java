package bg.fmi.plovdiv.car_management_system.data.responses;

import java.time.YearMonth;

public class MaintenanceMonthlyRequestsResponse {

    private YearMonth yearMonth;
    private Integer requests;

    public MaintenanceMonthlyRequestsResponse(YearMonth yearMonth, Integer requests) {
        this.yearMonth = yearMonth;
        this.requests = requests;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public Integer getRequests() {
        return requests;
    }

}
