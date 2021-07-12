package com.friends.manage.repository;

import com.friends.manage.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/*
'Person 클래스(도메인)를 핸들링 할 수 있는 Repository'
JPA 에서는 JPA Repository를 상속받은 Repository interface를 만들면
자동으로 CRUD 메소드를 만들어 줌.
<Entity 타입, id 타입>
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
