package com.friends.manage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.friends.manage.controller.dto.PersonDto;
import com.friends.manage.domain.Person;
import com.friends.manage.domain.dto.Birthday;
import com.friends.manage.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@Slf4j
@Transactional
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception{
   //     mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("martin"));
    }

    @Test
    void postPerson() throws Exception{
     //   mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        mockMvc.perform(
                 MockMvcRequestBuilders.post("/api/person")
      //          MockMvcRequestBuilders.post("/api/person?name=martin2&age=20&bloodType=A"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"name\":\"martin2\",\n" +
                        "    \"age\": 20,\n" +
                        "    \"bloodType\": \"A\"\n" +
                        "}"))
                .andDo(print())
               // .andExpect(status().isOk());
                .andExpect(status().isCreated());
    }

    @Test
    void modifyPerson() throws Exception {
        PersonDto dto  = PersonDto.of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

    //    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                /*.content("{\n" +
                        "    \"name\":\"martin\""+
                        "}"))*/
                .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk());

        //body에 content로 넣으면 번거로우므로 ObjectMapper 이용.

        Person result = personRepository.findById(1L).get();

        assertAll( // 단 한번의 test로 어느 부분에서  error가 나는지 확인 가능
                () -> assertThat(result.getName()).isEqualTo("martin"),
                () -> assertThat(result.getHobby()).isEqualTo("programming"),
                () -> assertThat(result.getAddress()).isEqualTo("판교"),
                () -> assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                () -> assertThat(result.getJob()).isEqualTo("programmer"),
                () ->assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
        );

    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception {
        PersonDto dto  = PersonDto.of("james", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

        assertThrows(NestedServletException.class, () ->
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)

                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk()));


    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto); //personDto를 json형식으로 serialization해줌
    }

    @Test
    void checkJsonString() throws  JsonProcessingException{
        PersonDto dto = new PersonDto();
        dto.setName("martin");
        dto.setBirthday(LocalDate.now());
        dto.setAddress("판교");
        System.out.println(">>>"+ toJsonString(dto));
    }

    @Test
    void modifyName() throws Exception{
    //    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name", "martinModified"))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");
    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
       //         .andExpect(content().string("true"));

        log.info("people deleted : {}", personRepository.findPeopleDeleted());

        //실제 해당 데이터가 사라졌는지 아닌지 알아야 함
        //방법 1 : PersonContoller에서 return 값을 boolean true로 확인
        //방법 2 : 삭제된 데이터를 확인해서 그걸 return 값으로 받음( 해당 데이터가 진짜로 사라졌는지 알 수 있음)
        //방법 3 : test를 위해 메인 로직을 수정하는 것은 좋은 방법이 아니므로, 아래와 같이 수정한다.
        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(1L)));
    }

}