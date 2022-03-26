package com.company.design.builder.people;

import java.util.List;

public class PeopleBuilder {
    private Integer id;
    private String name;
    private List<String> address;
    private String sex;
    private String race;
    private boolean isAdult;
    private int age;

    public People build() {
        return new People(id, name, address, sex, race, isAdult, age);
    }

    People buildForTest() {
        if(id == null) {
            id = 1;
        }
        return new People(id, name, address, sex, race, isAdult, age);
    }

    public PeopleBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public PeopleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PeopleBuilder withAddress(List<String> addressList){
        this.address.addAll(addressList);
        return this;
    }

    public  PeopleBuilder withAddress(String address){
        this.address.add(address);
        return this;
    }

    public PeopleBuilder withSex(String sex) {
        this.sex = sex;
        return this;
    }

    public PeopleBuilder withRace(String race) {
        this.race = race;
        return this;
    }

    public PeopleBuilder withIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
        return this;
    }

    public PeopleBuilder withAge(int age) {
        this.age = age;
        return this;
    }

}