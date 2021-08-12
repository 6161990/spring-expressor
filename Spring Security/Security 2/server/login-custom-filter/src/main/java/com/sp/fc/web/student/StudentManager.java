package com.sp.fc.web.student;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

/*
StudentManager가 AuthenticationProvider가 되서 "hong"이라는 id의 사용자가 오면
student를 담은 StudentAuthenticationToken (인증토큰) 을 발행함.
 => 통행증을 발급할 provider. 모든 학생의 명단을 가지고 있음
 AuthenticationProvider로써의 역할을 StudentManager가 하고 있다고 등록이 필요함. Security Config 에서 진행. */

//InitializingBean : 빈이 초기화할 때 사용
@Component
public class StudentManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Student> studentDB = new HashMap<>();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    /*  UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        if(studentDB.containsKey(token.getName())){
            Student student = studentDB.get(token.getName());*/
        StudentAuthenticationToken token = (StudentAuthenticationToken) authentication;
        if(studentDB.containsKey(token.getCredentials())){
            Student student = studentDB.get(token.getCredentials());
            return StudentAuthenticationToken.builder()
                    .principal(student)
                    .details(student.getUsername())
                    .authenticated(true)
                    .build();
        }
        return null; //내가 처리할 수 없는 Authentication인 경우는 null 넘기는 것이 원칙.

/*       AuthenticationToken을 StudentAuthenticationToken으로 발행
         but, 밑에서 UsernamePasswordAuthenticationToken을 대상으로 발행하기로 했기 때문에 형변환이 필요*/
    }

    @Override
    public boolean supports(Class<?> authentication) {
/*       return authentication == UsernamePasswordAuthenticationToken.class;
         해당 StudentManager의 관심 대상은 UsernamePasswordAuthenticationFilter를 통해 얻은 UsernamePasswordAuthenticationToken.
        그걸 authentication으로 받아 검증을 여기서 해주겠다.  */

        return authentication == StudentAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("hong", "홍홍홍", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
                new Student("kang", "강강강", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT"))),
                new Student("rang", "랑랑랑", Set.of(new SimpleGrantedAuthority("ROLE_STUDENT")))
        ).forEach(s-> studentDB.put(s.getId(), s));
    }
}
