package com.friends.manage.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//JPA
//  DB의 종류에 상관없이 이용가능하다.
//  JPA문법에 맞춰 로직을 작성하면 사용하는 DB종류애 따라 적당한 쿼리를 생성해서 ORM이 처리를 하도록 되어있다.


@Entity //값을 담고 있는 객체 , 실제 저장하고 불러오는 건 repository
public class Person {
    @Id  //pk
    @GeneratedValue  //자동생성
    private Long id;

    private String name;

    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
