package com.backstreetbrogrammer.ch01_cleancode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;

public class CleanCodeDemo {

    public void badCode() {
        Calendar c = Calendar.getInstance();
        c.set(123, 5, 26, 0, 0, 0);
        c.add(13, 60); // adds one minute
        //new BirthdayWishService().wish(c.getTime());
        System.out.println(c.getTime());
    }

    public void cleanCode() {
        final LocalDate birthdayDate = LocalDate.of(2023, Month.JUNE, 26);
        final LocalTime birthdayWishTime = LocalTime.of(0, 1, 0);
        //new BirthdayWishService().wish(LocalDateTime.of(birthdayDate, birthdayWishTime));
        System.out.println(LocalDateTime.of(birthdayDate, birthdayWishTime));
    }

}
