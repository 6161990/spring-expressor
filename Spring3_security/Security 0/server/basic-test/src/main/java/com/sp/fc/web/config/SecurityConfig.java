package com.sp.fc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*application.properties에 사용자는 한명만 추가할 수 있기 때문에
    WebSecurityConfigurerAdapter 가 제공하는 걸로 사용자(admin)을 추가한다.
    이 경우 application.properties에 추가했던 사용자는 접근 차단된다. */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.builder()
                .username("user2")
                        .password(passwordEncoder().encode("2222"))
                        .roles("USER")
                ).withUser(User.builder()
                .username("admin")
                .password(passwordEncoder().encode("3333"))
                .roles("ADMIN"));
    }

    /*이렇게만 하면 오류가 남 -> 사용자의 패스워드 (2222)를 인코딩하지 않았기 때문!!*/


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /* 그런데 HOME PAGE 같은 경우는 모두에게 public 하게 접근 권한을 주고 싶다. */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests.antMatchers("/").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin();
        http.httpBasic();
    }
}
