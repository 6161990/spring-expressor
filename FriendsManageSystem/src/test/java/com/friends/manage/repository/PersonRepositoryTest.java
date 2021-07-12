package com.friends.manage.repository;

import com.friends.manage.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("martin");
        person.setAge(30);
        personRepository.save(person);
        System.out.println(personRepository.findAll());

        //문제점 1 sysout은 hashcode가 출력된다. -> person객체의 toString override로 해결.
        //문제점 2 person 객체에 getter,setter 가 없다. -> getter,setter 생성으로 해결.
        //문제점 3 해당 테스트는 log를 찍을 뿐이지 자동화된 테스트 방법이 아니다.
        // -> assertThat()으로 해결
        List<Person> people = personRepository.findAll();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("martin");
        assertThat(people.get(0).getAge()).isEqualTo(30);
    }
}