package com.friends.manage.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

//JPA
//  DB의 종류에 상관없이 이용가능하다.
//  JPA문법에 맞춰 로직을 작성하면 사용하는 DB종류애 따라 적당한 쿼리를 생성해서 ORM이 처리를 하도록 되어있다.


@Entity //값을 담고 있는 객체 , 실제 저장하고 불러오는 건 repository

//생성자 : 의미가 없는 생성자를 만들면 중요한 로직이 묻힐 수도 있음
@NoArgsConstructor //인자가없는 Constructor
//@AllArgsConstructor //인자가 있는 Constructor
@RequiredArgsConstructor //NOT NULL로 설정된 인자로 구성된 Constructor

//데이터 (하위 4개는 @Data 하나로 치환가능함. but, 쓰지 않는 것이 좋다는 의견도 많음.
@Getter
@Setter
@ToString //(exclude = "phoneNumber")
//필드가 추가될 때마다 toString을 수정해줘야하므로 클래스에 설정
//핸드폰 번호를 노출시키지 않기 위해 toString에서 제외
//but, exclude는 해당 필드에만 적용시키는 것이 바람직.
@EqualsAndHashCode

public class Person {
    @Id  //pk
    @GeneratedValue  //자동생성
    private Long id;

    //@Getter
    //@Setter 모든 필드에서 중복되므로 클래스에 설정
    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true /*fetch = FetchType.LAZY */)
    //PERSIST : Person Entity에서 block에 관한 영속성을 함께 관리하겠다는 뜻
    //MERGE : LocalDate 필드 통합
    //REMOVE : 함께 삭제 -> CascadeType.ALL 이면 다 해결
    @ToString.Exclude
    private Block block; //person과 (name)으로로연결하기 위해

    public Person(String name, int age, String bloodType) {
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
    }

    public Person(Long id, String name, int age, String hobby, String bloodType, String address, LocalDate birthday, String job, String phoneNumber, Block block) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.bloodType = bloodType;
        this.address = address;
        this.birthday = birthday;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.block = block;
    }
    /*
    이렇게 block에 관한 필드가 여러개있다면 따로 클래스로 빼두는 것이 좋음
    private boolean block;
    private String blockReason;
    private LocalDate blockStartDate;
    private LocalDate blockEndDate;
     */


/* 필드가 수정될 때 마다 equals와 hashCode 를 수정해줘야함 -> @EqualsAndHashCode
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        Person person = (Person) object;

        if (!person.getName().equals(this.getName())){
            return false;
        }
        if (person.getAge() != this.getAge()){
            return false;
        }
        return true;
    }

    public int hashCode(){
        return (name + age).hashCode();
    }
*/


}
