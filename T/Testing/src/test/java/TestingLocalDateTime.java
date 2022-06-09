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

        String text = "2022-01-01 03:00:00";
        String slkf = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(slkf);

        LocalDateTime firstMeetingAt = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(slkf));
        LocalDateTime parse1111 = LocalDateTime.parse("2019-12-25T10:15:20");
        LocalDateTime parse2222 = LocalDateTime.parse("2019-12-25 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String stringCreatedAt = "2022-01-01T03:00:00";
        LocalDateTime createdAt = LocalDateTime.parse(stringCreatedAt, formatter);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+createdAt);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!"+firstMeetingAt);
        System.out.println(parse1111);
        System.out.println(parse2222);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\'T\'HH:mm:ssz");
        String currentTime = "2017-10-19T22:00:00";
        LocalDateTime datetime = LocalDateTime.parse(currentTime, dateTimeFormatter);
        System.out.println(datetime);
        //2017-10-19T22:00
        String formattedDate = datetime.format(dateTimeFormatter);
        //2017-10-19 22:00:00

        System.out.println(formattedDate);

//        assertTrue(firstMeetingAt instanceof LocalDateTime);
    }


    @Test
    void sss() throws DatatypeConfigurationException {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final LocalDateTime localDateTime = LocalDateTime.parse("2018-07-06 00:00:00", dateTimeFormatter);
        final String lexicalDate = localDateTime.toString();
        System.out.println("Lexical Date : "+ lexicalDate+ localDateTime);
        final XMLGregorianCalendar gregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(lexicalDate);
        System.out.println("Gregorian Calendar : "+ gregorianCalendar);
    }

}
