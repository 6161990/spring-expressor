package com.sp.fc.web.config;

import com.sp.fc.web.student.StudentAuthenticationToken;
import com.sp.fc.web.teacher.TeacherAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    /* 2 AuthenticationManager를 줘야 UsernamePasswordAuthenticationFilter 이 동작하기 때문에*/
    public CustomLoginFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }


    /* 1 UsernamePasswordAuthenticationFilter에서 토큰을 만드는 메소드 */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        username = (username != null) ? username : "";
        username = username.trim();
        String password = obtainPassword(request);
        password = (password != null) ? password : "";
        String type = request.getParameter("type"); // loginForm 에서 학생이냐, 선생이냐 check
        if(type == null || !type.equals("teacher")){
            // student
            StudentAuthenticationToken token = StudentAuthenticationToken.builder()
                    .credentials(username).build();
            return this.getAuthenticationManager().authenticate(token);
        }else{
            // teacher
            TeacherAuthenticationToken token = TeacherAuthenticationToken.builder()
                    .credentials(username).build();
            return this.getAuthenticationManager().authenticate(token);
        }
    }

    /* 3 Security Config로 */
}
