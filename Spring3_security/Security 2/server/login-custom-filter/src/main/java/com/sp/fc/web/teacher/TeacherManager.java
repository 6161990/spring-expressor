package com.sp.fc.web.teacher;


import com.sp.fc.web.student.Student;
import com.sp.fc.web.student.StudentAuthenticationToken;
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
public class TeacherManager implements AuthenticationProvider, InitializingBean {

    private HashMap<String, Teacher> teacherDB = new HashMap<>();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
/*      UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        if(teacherDB.containsKey(token.getName())){
            Teacher teacher = teacherDB.get(token.getName());*/
        TeacherAuthenticationToken token = (TeacherAuthenticationToken) authentication;
        if(teacherDB.containsKey(token.getCredentials())){
            Teacher teacher = teacherDB.get(token.getCredentials());
            return TeacherAuthenticationToken.builder()
                    .principal(teacher)
                    .details(teacher.getUsername())
                    .authenticated(true)
                    .build();
        }
        return null;

    }

    @Override
    public boolean supports(Class<?> authentication) {
    //    return authentication == UsernamePasswordAuthenticationToken.class;
        return authentication == TeacherAuthenticationToken.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Teacher("kim", "김김김", Set.of(new SimpleGrantedAuthority("ROLE_Teacher")))
        ).forEach(t-> teacherDB.put(t.getId(), t));
    }
}
