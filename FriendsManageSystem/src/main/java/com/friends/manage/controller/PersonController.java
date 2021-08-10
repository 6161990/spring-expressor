package com.friends.manage.controller;

import com.friends.manage.controller.dto.PersonDto;
import com.friends.manage.domain.Person;
import com.friends.manage.exception.PersonNotFoundException;
import com.friends.manage.exception.RenameIsNotPermittedException;
import com.friends.manage.exception.dto.ErrorResponse;
import com.friends.manage.repository.PersonRepository;
import com.friends.manage.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/person") //scope에 의해 하위 메소드들이 해당 주소로 mapping됨
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping("/{id}")
//    @RequestMapping(value = "/{id}")
    public Person getPerson(@PathVariable Long id){
    //public Person getPerson(@RequestParam(required = false, defaultValue = "1") Long id){
    // 둘 중 하나로 하면  GET http://localhost:8083/api/person/1 => ' ?id= ' 없이 rest형식의 url로 표시할 수 있게됨
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // data가 생성되었다는 것을 명확하게 알 수 있게끔. 단순 200ok 가 아니라 create 201 번 ok
    public void postPerson(@RequestBody @Valid  PersonDto personDto){
        personService.put(personDto);
        log.info("person -> {}", personRepository.findAll());
    }

    @PutMapping("{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        personService.modify(id, personDto);
        log.info("person -> {}", personRepository.findAll());
    }

    @PatchMapping("/{id}") //일부만 수정하려할 때 patch
    public void modifyPerson(@PathVariable Long id, String name){
        personService.modify(id, name);
        log.info("person -> {}", personRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);
        log.info("person -> {}", personRepository.findAll());
       // return personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(id));
    }




}
