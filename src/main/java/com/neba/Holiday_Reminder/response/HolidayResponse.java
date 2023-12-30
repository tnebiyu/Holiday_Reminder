package com.neba.Holiday_Reminder.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class HolidayResponse {
    private int status;
    private String warning;
    private Requests requests;
    private List<Holiday> holidays;

    @lombok.Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @NoArgsConstructor


    public static class Requests {
        private int used;
        private int available;
        private String resets;
    }
    @lombok.Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Holiday {
        private String name;
        private String date;
        private String observed;
        private boolean publicHoliday;
        private String country;
        private String uuid;
        private Weekday weekday;
    }
    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Weekday {
        private DayDetails date;
        private DayDetails observed;
    }
    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DayDetails {
        private String name;
        private String numeric;


    }

}
