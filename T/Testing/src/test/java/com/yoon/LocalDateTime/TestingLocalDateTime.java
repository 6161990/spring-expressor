package com.yoon.LocalDateTime;

import org.junit.jupiter.api.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestingLocalDateTime {

    @Test
    void LocalDateTimeTest(){

        String text = "2022-01-01T03:00:00";
        String localDateFormat = "yyyy-MM-dd'T'HH:mm:ss[.SSS]";

        LocalDateTime firstMeetingAt = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(localDateFormat));
        System.out.println("111111  "+firstMeetingAt);

        LocalDateTime parse2222 = LocalDateTime.parse("2019-12-25T10:15:20");
        System.out.println("222222  "+parse2222);
        //date_hour_minute_second("uuuu-MM-dd'T'HH:mm:ss")
        LocalDateTime parse3333 = LocalDateTime.parse("2019-12-25 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS]"));
        System.out.println("333333  "+parse3333);

        String currentTime = "2017-10-19T22:00:00"; //yyyy-MM-dd'T'HH:mm:ss[.SSS]
        LocalDateTime datetime = LocalDateTime.parse(currentTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]"));
        System.out.println("444444   "+datetime); //2017-10-19T22:00

        String formattedDate = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        System.out.println("555555   "+formattedDate); //2017-10-19 22:00:00

    }


    @Test
    void sss()  {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final LocalDateTime localDateTime = LocalDateTime.parse("2018-07-06 00:00:00", dateTimeFormatter);
        final String lexicalDate = localDateTime.toString();
        System.out.println("Lexical Date : "+ lexicalDate+ localDateTime);
    }

}
