package com.company.design.builder.people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeopleBuilderTest {

    People asiaStudent;
    PeopleBuilder defaultAsiaStudent;

    @BeforeEach
    void setUp() {
        defaultAsiaStudent = People.builder()
                .withRace("황인")
                .withIsAdult(false);

        System.out.println("setUp AsiaStudent"  + defaultAsiaStudent.buildForTest());
    }

    @Test
    void test1() {
        asiaStudent = defaultAsiaStudent
                .withAddress("목동")
                .withAge(17)
                .withSex("남")
                .buildForTest();

        System.out.println("test1 AsiaStudent"  + asiaStudent);
    }

    @Test
    void test2() {
        asiaStudent = defaultAsiaStudent
                .withAddress("강남")
                .withAge(19)
                .withSex("여")
                .buildForTest();

        System.out.println("test2 AsiaStudent"  + asiaStudent);
    }
}
