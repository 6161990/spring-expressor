package com.friends.manage.service;

import com.friends.manage.domain.Block;
import com.friends.manage.domain.Person;
import com.friends.manage.repository.BlockRepository;
import com.friends.manage.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/* repository 같은 경우는 JPARepository를 상속 받기 때문에 에노테이션이 필요없지만
* service의 경우는 애노테이션을 달아주어야 스프링이 인식한다.*/
@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

//    @Autowired
//    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks(){
        List<Person> people = personRepository.findAll();
    //    List<Block> blocks = blockRepository.findAll();
    //    List<String> blockName = blocks.stream().map(Block::getName).collect(Collectors.toList());;

    //    return people.stream().filter(person -> !blockName.contains(person.getName())).collect(Collectors.toList());
         return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
    }
    //stream.map으로 Block에 있는 회원의 이름을 get. List<String>으로 받기 위해 collect
    //filter 어떤 조건에 일치하는 값만 돌려주는 함수

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        Person person = personRepository.findById(id).get();
        log.info("person : {}",person);
        /*
        log는 logback을 이용해서 log 출력을 제한할 수 있는 장점이 있음
        System.out.println 보다 사용권장
        하지만 select이 찍힐 때 eager 타입이라 block 문이 하나의 쿼리로 실행됨 left outer join
        fetchType Lazy로 변경
        그러면 session오류가 날 수 있으므로 @Transacional
        딱히 저장이 아니라 select이기 때문에 상관 x
        결과,  select문이 person과 block으로 나뉘어서 두 개 찍힘!
        Lazy : 실제로 person객체를 호출 할 때 block을 select 하지 않고 그 객체가 필요한 시점에 block id를 통해서 select 해서 옴. 그래서 두 개로 나뉘어 찍힘
        한꺼번에 찍힌 이유는 log로 찍었기 때문에 person과 block이 한꺼번에 찍힘
        이 때 ToString.Exclude를 지정. 불필요한 쿼리가 찍히는 것을 방지 할 수 있음.
        eager로 하고 optional = false ("항상 필요하다") 로 한 뒤, 모두 givenBlockPerson()로 data를 넣으면
        inner로 조인됨. => jpa를 사용하면 몇가지 옵션으로 알아서 쿼리문을 작성해줌.
        */

        return person;
    }
}
