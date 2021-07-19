package com.friends.manage.repository;

import com.friends.manage.domain.Person;
import com.friends.manage.domain.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){

        Person person = new Person();
        person.setName("judi");
//        person.setBloodType("A");
        personRepository.save(person);
//        System.out.println(personRepository.findAll());

        //문제점 1 sysout은 hashcode가 출력된다. -> person객체의 toString override로 해결.
        //문제점 2 person 객체에 getter,setter 가 없다. -> getter,setter 생성으로 해결.
        //문제점 3 해당 테스트는 log를 찍을 뿐이지 자동화된 테스트 방법이 아니다.
        // -> assertThat()으로 해결
//        List<Person> people = personRepository.findAll();
        List<Person> result = personRepository.findByName("judi");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("judi");
       // assertThat(result.get(0).getAge()).isEqualTo(9);
    }

/*     @Test
    void findByBloodType(){
      givenPerson("martin",10,"A");
        givenPerson("sojin",13,"B");
        givenPerson("sora",10,"A");
        givenPerson("dongjin",13,"B");

        List<Person> result = personRepository.findByBloodType("A");
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("benny");
    }*/

    @Test
    void findByBirthdayBetween(){
    /*    givenPerson("martin",10,"A",LocalDate.of(1991,8,4));
        givenPerson("sojin",13,"B",LocalDate.of(1993,9,1));
        givenPerson("sora",10,"A",LocalDate.of(1995,4,1));
        givenPerson("dongjin",13,"B",LocalDate.of(1996,8,30));*/

      //  List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1991,8,1),LocalDate.of(1991,8,31));
        List<Person> result = personRepository.findByMonthOfBirth(8);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
    }



/*  private void givenPerson(String name, int age, String bloodType, LocalDate birthday) {
       Person person = new Person(name, age, bloodType);
    //   person.setBirthday(new Birthday(birthday.getYear(),birthday.getMonthValue(),birthday.getDayOfMonth()));
       person.setBirthday(new Birthday(birthday));
       personRepository.save(person);
    }


    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }



    void constructorTest(){
      // Person person = new Person(1L, "martin", 30, "reading", "O" , "분당", LocalDate.of(1992,3,12),"programmer","010-2222-3333");

        //NOT NULL로 설정된 인자로 구성된 Constructor (@RequiredArgsConstructor)
       // Person person1 = new Person("marson",32);
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
    }*/
}