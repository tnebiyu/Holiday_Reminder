package com.neba.Holiday_Reminder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Holiday {
    private String name;
    private String date;
    private String observed;
    private boolean isPublic;
    private String country;

}
