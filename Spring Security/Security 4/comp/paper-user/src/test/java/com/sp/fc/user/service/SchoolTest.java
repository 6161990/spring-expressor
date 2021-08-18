package com.sp.fc.user.service;

import com.sp.fc.user.domain.School;
import com.sp.fc.user.repository.SchoolRepository;
import com.sp.fc.user.service.helper.SchoolTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//db에 데이터를 넣고 빼는 integration test이기 때문에
@DataJpaTest
//db 데이터 소스를 h2 db의 inMemory방식으로 만들고 repository 객체는 스프링 컨테이너 안에서 기본적으로 만들어짐
//repository까지만 만들어주므로 서비스는 직접 만들어야함
public class SchoolTest {

    // 학교를 생성한다.
    // 학교 이름을 수정한다.
    // 지역 목록을 가져온다.
    // 지역으로 학교 목록을 가져온다.

    @Autowired
    private SchoolRepository schoolRepository;
    // Repository객체 이기 때문에 data jpa 테스트에서 빈을 자동으로 만들어주는 scope에 해당
    // @Autowired가 적용될 수 있음

    private SchoolService schoolService;
    //하지만 service는 해당이 안되기 때문에 다음과 같은 메소드로 직접 생성

    private SchoolTestHelper schoolTestHelper;

    School school;

    @BeforeEach
    void before(){
        this.schoolRepository.deleteAll(); // test는 처음에 기존 데이터를 다 삭제하고 하는 것이 완전. test는 순서가 정해지지 않고 돌아가기 때문
        this.schoolService = new SchoolService(schoolRepository);
        this.schoolTestHelper = new SchoolTestHelper(schoolService);
        school = this.schoolTestHelper.createSchool("테스트 학교","서울");
    }

    @DisplayName("1. 학교를 생성한다.")
    @Test
    void test_1(){
        List<School> list = schoolRepository.findAll();
        assertEquals(1, list.size());
        SchoolTestHelper.assertSchool(list.get(0), "테스트 학교", "서울");
    }

    @DisplayName("2. 학교 이름을 수정한다.")
    @Test
    void test_2(){
        schoolService.updateName(school.getSchoolId(), "테스트2 학교");
        assertEquals("테스트2 학교", schoolRepository.findAll().get(0).getName());

    }

    @DisplayName("3. 지역 목록을 가져온다.")
    @Test
    void test_3(){
        List<String> cities = schoolService.cities();
        assertEquals(1, cities.size());
        assertEquals("서울", cities.get(0));

        schoolTestHelper.createSchool("부산 학교","부산");
        cities = schoolService.cities();
        assertEquals(2, cities.size());
    }

    @DisplayName("4. 지역으로 학교목록을 가져온다.")
    @Test
    void test_4(){
        List<School> cities = schoolService.findAllByCity("서울");
        assertEquals(1, cities.size());

        schoolTestHelper.createSchool("서울2 학교","서울");
        cities = schoolService.findAllByCity("서울");
        assertEquals(2, cities.size());
        
    }

}
