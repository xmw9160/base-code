package com.xmw.java8.java8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author mingwei.xia
 * @date 2018/7/4 19:47
 * @since V1.0
 */
public class DateTest {
    public static void main(String[] args) {
//        Date date = new Date(114, 2, 18);
//        System.out.println(date); //Tue Mar 18 00:00:00 CST 2014
        // LocalDate 、 LocalTime 、 Instant 、 Duration 以及 Period
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        LocalDate today = LocalDate.now();
        System.out.println(today);

        date.get(ChronoField.YEAR);
        date.get(ChronoField.MONTH_OF_YEAR);
        date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate.parse("2014-03-18");
        LocalTime.parse("13:45:20");

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);

//        Duration d1 = Duration.between(time1, time2);
//        Duration d1 = Duration.between(dateTime1, dateTime2);
//        Duration d2 = Duration.between(instant1, instant2);

//        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

        LocalDate now1 = LocalDate.now();
        LocalDate nowDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2018, 6, 20);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(nowDate);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("Happy Birthday");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }

        LocalDate plusDate = nowDate.plus(1, ChronoUnit.WEEKS);
        LocalDate minusDate = nowDate.minus(1, ChronoUnit.YEARS);
        LocalDate plusDate1 = minusDate.plus(1, ChronoUnit.YEARS);

        System.out.println(LocalDateTime.now()); // 2018-07-06T12:47:25.156
        System.out.println(Instant.now());       // 2018-07-06T04:47:25.156Z 没有加时区时间差
        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));

    }
}
