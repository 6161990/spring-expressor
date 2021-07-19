package com.friends.manage.service;


import com.friends.manage.domain.Person;
import com.friends.manage.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonRepository personRepository;
    
/*   @Autowired
    private BlockRepository blockRepository;

 */

    @Test
    void getPeopleByName(){
    //    givenPeople();

        List<Person> result = personService.getPeopleByName("martin");
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("martin");
    }

    @Test
    void getPerson(){
        Person person = personService.getPerson(3L);

        Assertions.assertThat(person.getName()).isEqualTo("dennis");
    }


    /* @Test
    void getPeopleExcludeBlocks(){
       givenPeople();

        List<Person> result = personService.getPeopleExcludeBlocks();
        //System.out.println(result);
        result.forEach(System.out::println); //list의 각 객체가 한 줄씩 출력

        Assertions.assertThat(result.size()).isEqualTo(4); //judi포함
        Assertions.assertThat(result.get(0).getName()).isEqualTo("martin");
        Assertions.assertThat(result.get(1).getName()).isEqualTo("david");
        Assertions.assertThat(result.get(2).getName()).isEqualTo("benny");
        Assertions.assertThat(result.get(3).getName()).isEqualTo("judi");
    }*/



    /*
    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));

        personRepository.save(blockPerson);
    }

    private void givenPeople() {
        givenPerson("martin",10,"A");
        givenPerson("sojin",13,"B");
        givenBlockPerson("sarang",7,"O");
        givenBlockPerson("martin",5,"AB");
    }

    @Test
    void cascadeTest(){
        //CascadeType.PERSIST
        givenPeople();
        List<Person> result = personRepository.findAll();
        result.forEach(System.out::println);

        //CascadeType.MERGE
        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

        //CascadeType.REMOVE
//        personRepository.delete(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);

        //orphanRemoval = true , Block을 null 로 만들면 blockRepository에서도 null
        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

 */

}