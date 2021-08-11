package com.friends.manage.service;


import com.friends.manage.controller.dto.PersonDto;
import com.friends.manage.domain.Person;
import com.friends.manage.domain.dto.Birthday;
import com.friends.manage.exception.PersonNotFoundException;
import com.friends.manage.exception.RenameIsNotPermittedException;
import com.friends.manage.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks //테스트의 대상이 되는 클래스
    private PersonService personService;
    
    @Mock //대상이 되는 클래스에서 Autowired하고 있는 클래스
    private PersonRepository personRepository;

    // => @Mock이 설정된 클래스는 Mock으로 만들어서 @InjectMocks이 설정된 클래스에 주입시킨다.
    // Mock은 가짜임. 빈껍데기 이기 때문에 기존 test코드를 돌리면 오류가 남.
    // Mock테스트용 코드를 따로 작성해야함


/*   @Autowired
    private BlockRepository blockRepository;

 */
@Test
void getAll() {
    when(personRepository.findAll(any(Pageable.class)))
            .thenReturn(new PageImpl<>(Lists.newArrayList(new Person("martin"), new Person("dennis"), new Person("tony"))));

    Page<Person> result = personService.getAll(PageRequest.of(0, 3));

    assertThat(result.getNumberOfElements()).isEqualTo(3);
    assertThat(result.getContent().get(0).getName()).isEqualTo("martin");
    assertThat(result.getContent().get(1).getName()).isEqualTo("dennis");
    assertThat(result.getContent().get(2).getName()).isEqualTo("tony");
}

    @Test
    void getPeopleByName(){
    //    givenPeople();

/*
        기존 코드
        List<Person> result = personService.getPeopleByName("martin");
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("martin");*/

        when(personRepository.findByName("martin"))  //when == if 실제로 호출되는 것이 아니라 호출되었다고 가정
                .thenReturn(Lists.newArrayList(new Person("martin")));
        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }

    @Test
    void getPerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        Person person = personService.getPerson(1L);
        assertThat(person.getName()).isEqualTo("martin");

/*        Person person = personService.getPerson(3L);
        assertThat(person.getName()).isEqualTo("dennis");*/
    }

    @Test
    void getPersonIfNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        Person person = personService.getPerson(1L);
        assertThat(person).isNull();
    }

    @Test
    void put(){
        PersonDto dto = PersonDto.of("martin","programming","판교", LocalDate.now(),"programmer", "010-1111-2222");
        personService.put(dto);

        //Mock test 새로운 방식으로 void 리턴 메소드 test를 진행
        //verify(personRepository, times(1)).save(any(Person.class));
        //진행했던 Mock에 대한 verify , Person.class 에대한 save가 어떤 것이든지 진행되었는지.
        //한번인지, 두번인지 혹은 한번도 실행(naver)하지 않았는지 .. times
        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeInserted()));
    }

    @Test
    void modifyIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, ()-> personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modifyIfNameIsDifferent(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("tony")));

        assertThrows(RenameIsNotPermittedException.class ,  ()-> personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modify(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        personService.modify(1L, mockPersonDto());
        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeUpdated()));
        //save를 할건데 거기에 들어가는 인자인 IsPersonWillBeUpdated를 통과해야한다.
    }

    @Test
    void modifyByNameIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, ()-> personService.modify(1L, "daniel"));
    }

    @Test
    void modifyByName(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        personService.modify(1L, "daniel");
        verify(personRepository, times(1)).save(argThat(new IsNameWillBeUpdated()));
    }

    @Test
    void deleteIfPersonNotFound() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.delete(1L));
    }

    @Test
    void delete() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        personService.delete(1L);

        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeDeleted()));
    }

    private PersonDto mockPersonDto(){
        return PersonDto.of("martin","programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");
    }

    private static class IsPersonWillBeInserted implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return equals(person.getName(), "martin")
                    && equals(person.getHobby(), "programming")
                    && equals(person.getAddress(), "판교")
                    && equals(person.getBirthday(), Birthday.of(LocalDate.now()))
                    && equals(person.getJob(), "programmer")
                    && equals(person.getPhoneNumber(), "010-1111-2222");
        }

        private boolean equals(Object actual, Object expected) {
            return expected.equals(actual);
        }
    }
    private static class IsPersonWillBeUpdated implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return equals(person.getName(), "martin")
                    && equals(person.getHobby(), "programming")
                    && equals(person.getAddress(), "판교")
                    && equals(person.getBirthday(), Birthday.of(LocalDate.now()))
                    && equals(person.getJob(), "programmer")
                    && equals(person.getPhoneNumber(), "010-1111-2222");
        }

        private boolean equals(Object actual, Object expected) {
            return expected.equals(actual);
        }
    }

    private static class IsNameWillBeUpdated implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return person.getName().equals("daniel");
        }
    }

    private static class IsPersonWillBeDeleted implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return person.isDeleted();
        }
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