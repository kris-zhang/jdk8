/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.qunar.kris.share.jdk8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * jdk8的时间函数
 *
 * @author gongzuo.zy
 * @version $Id: TimeStudy.java, v0.1 2017-02-16 16:42  gongzuo.zy Exp $
 */
public class TimeStudy {
    public static void main(String[] args) {

        /**
         * 伴随lambda表达式、streams以及一系列小优化，Java 8 推出了全新的日期时间API，在教程中我们将通过一些简单的实例来学习如何使用新API。
         * Java处理日期、日历和时间的方式一直为社区所诟病，
         * 将 java.util.Date设定为可变类型，以及SimpleDateFormat的非线程安全使其应用非常受限。
         * Java也意识到需要一个更好的 API来满足社区中已经习惯了使用JodaTime API的人们。全新API的众多好处之一就是，明确了日期时间概念，
         * 例如：瞬时（instant）、 长短（duration）、日期、时间、时区和周期。同时继承了Joda库按人类语言和计算机各自解析的时间处理方式。
         * 不同于老版本，新API基于ISO标准日历系统，java.time包下的所有类都是不可变类型而且线程安全。下面是新版API中java.time包里的一些关键类：
         *      Instant：瞬时实例。
         *      LocalDate：本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
         *      LocalTime：本地时间，不包含日期。
         *      LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
         *      ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
         * 新API还引入了ZoneOffSet和ZoneId类，使得解决时区问题更为简便。解析、格式化时间的DateTimeFormatter类也全部重新设计。
         */

        // 获得当前时间戳，类似Date
        Instant instant = Instant.now();
        instant.plus(10, ChronoUnit.HOURS);
        instant.isAfter(instant);
        instant.isBefore(instant);
        System.out.println(instant.toEpochMilli());
        System.out.println(instant);

        // 只表示日期
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);

        System.out.println(today);
        System.out.println(today.getYear()+ ":" + today.getMonthValue() + ":" + today.getDayOfMonth());

        // 初始化特定日期
        LocalDate dateOfBirth = LocalDate.of(2010, 1, 14);

        // 对比日期是否相同，忽略年份，MonthDay的使用
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if(currentMonthDay.equals(birthday)){
            System.out.println("Many Many happy returns of the day !!");
        }else{
            System.out.println("Sorry, today is not your birthday");
        }

        // 获取当前时间
        LocalTime time = LocalTime.now();
        System.out.println(time);

        // 加减一定时间
        System.out.println(time.plusHours(10));
        System.out.println(time.minusMinutes(10));

        // 获得一周以后的时间
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println(nextWeek);

        // 使用clock
        Clock clock = Clock.systemUTC();
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println(clock);
        System.out.println(defaultClock);

        // 处理时区
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

        // 与MonthDay一样，可以用来处理信用卡时间
        YearMonth currentYearMonth = YearMonth.now();
        System.out.println(currentYearMonth);

        // 判断是否是闰年
        System.out.println(today.isLeapYear());

        // 时间间隔
        Period period = Period.between(today, yesterday);
        System.out.println(period.getDays());

        // 日期格式化
        String dayAfterTommorrow = "20140116";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n",
                dayAfterTommorrow, formatted);

        // 自定义时间解析
        String goodFriday = "12 12 1990";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate holiday = LocalDate.parse(goodFriday, formatter);
        System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday);

        // 自定义输出格式
        System.out.println(LocalDateTime.now().format(formatter));

    }

}
