package com.company.design.builder.people;


import java.util.List;

public class People {
    private Integer id;
    private String name;
    private List<String> address;
    private String sex;
    private String race;
    private boolean isAdult;
    private int age;

    People(Integer id,String name, List<String> address, String sex, String race, boolean isAdult, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.race = race;
        this.isAdult = isAdult;
        this.age = age;
    }

    public static PeopleBuilder builder() {
        return new PeopleBuilder();
    }
}
