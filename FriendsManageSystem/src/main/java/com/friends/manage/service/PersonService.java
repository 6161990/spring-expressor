package com.friends.manage.service;

import com.friends.manage.domain.Block;
import com.friends.manage.domain.Person;
import com.friends.manage.repository.BlockRepository;
import com.friends.manage.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/* repository 같은 경우는 JPARepository를 상속 받기 때문에 에노테이션이 필요없지만
* service의 경우는 애노테이션을 달아주어야 스프링이 인식한다.*/
@Service
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

}
