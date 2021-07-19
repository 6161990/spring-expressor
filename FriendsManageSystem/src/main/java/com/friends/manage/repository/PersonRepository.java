package com.friends.manage.repository;

import com.friends.manage.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/*
'Person 클래스(도메인)를 핸들링 할 수 있는 Repository'
JPA 에서는 JPA Repository를 상속받은 Repository interface를 만들면
자동으로 CRUD 메소드를 만들어 줌.
@Repository 에노테이션도 자동 인식함
<Entity 타입, id 타입>
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    //find = select , by = where , name = parameter(person 테이블의 name)
    List<Person> findByName(String name);

  //    List<Person> findByBlockIsNull(); // 차단이 되지 않은 person을 가져오겠다.

  //  List<Person> findByBloodType(String bloodType); //같은 혈액형은 많다.

  //  List<Person> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday =?1")
            //and person.birthday.dayOfBirthday=?2")
    //JPA Query : entity 기반으로 쿼리를 실행시키는 문법
    List<Person> findByMonthOfBirth(int monthOfBirthday /*, int dayOfBirthday*/);

    @Query(value = "select * from Person person where person.deleted = true",nativeQuery = true)
    List<Person> findPeopleDeleted();
}
