package com.friends.manage.repository;

import com.friends.manage.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.CascadeType;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void constructorTest(){
//        Person person = new Person(1L, "martin", 30, "reading", "O" ,
//                "분당", LocalDate.of(1992,3,12),"programmer","010-2222-3333");

        //NOT NULL로 설정된 인자로 구성된 Constructor (@RequiredArgsConstructor)
        Person person1 = new Person("marson",32);
    }

    @Test
    void hashCodeAndEquals(){
        Person person = new Person("martin", 20);
        Person person1 = new Person("martin",20);

        System.out.println(person.equals(person1));   //hashCodeAndEquals를 오버라이딩하면 true 값이 나옴.

        //하지만 둘의 hashCode는 다름름
       System.out.println(person.hashCode());
       System.out.println(person1.hashCode());

        //hashCode가 다르면 생기는 문제점
        Map<Person, Integer> map = new HashMap<>();
        map.put(person, person.getAge()); //person은 put했기때문에 가져올 수 있지만

        System.out.println(map);
        System.out.println(map.get(person1)); //person1은 비록 person과 값이 같더라도 put하지 않았기 때문에 가져올 수 없음.
    }
}