package com.friends.manage.service;

import com.friends.manage.domain.Block;
import com.friends.manage.domain.Person;
import com.friends.manage.repository.BlockRepository;
import com.friends.manage.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private BlockRepository blockRepository;
    
    @Test
    void getPeopleExcludeBlocks(){
        givenPeople();
        givenBlock();

        List<Person> result = personService.getPeopleExcludeBlocks();
        //System.out.println(result);
        result.forEach(System.out::println); //list의 각 객체가 한 줄씩 출력
    }

    private void givenBlock() {
        givenBlock("martin");
    }

    private Block givenBlock(String name) {
       return blockRepository.save(givenBlock(name));
    }

    private void givenPeople() {
        givenPerson("martin",10,"A");
        givenPerson("sojin",13,"B");
        givenBlockPerson("sarang",7,"O");
        givenBlockPerson("martin",5,"AB");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));

        personRepository.save(blockPerson);
    }
}