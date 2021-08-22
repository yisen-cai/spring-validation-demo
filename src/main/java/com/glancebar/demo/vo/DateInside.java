package com.glancebar.demo.vo;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author YISHEN CAI
 */
public class DateInside {
    @NotNull(message = "date can't be null.")
    private LocalDate date;

    @NotNull(message = "date time can't be null")
    private LocalDateTime dateTime;


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
