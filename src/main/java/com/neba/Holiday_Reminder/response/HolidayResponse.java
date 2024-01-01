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
//for Calendarific api response
public class HolidayResponse {
    private Meta meta;
    private Response response;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Meta {
        private int code;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        private List<Holiday> holidays;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Holiday {
        private String name;
        private String description;
        private DateDetails date;
        private List<String> type;
        private String primary_type;
        private String canonical_url;
        private String urlid;
        private String locations;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DateDetails {
        private String iso;
        private DateTime datetime;
        private Timezone timezone;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DateTime {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;
        private int second;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Timezone {
        private String offset;
        private String zoneabb;
        private int zoneoffset;
        private int zonedst;
        private int zonetotaloffset;
    }
}

