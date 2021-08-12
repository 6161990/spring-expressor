package com.sp.fc.web.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    BasicAuthenticationFilter basicAuthenticationFilter;
    /*  basicAuthenticationFilter에 requestHeaderNames를 가보면 여러가지 header 중에서 basic token이 넘어온 것을 확인 할 수 있다.
    * convert에서 HttpHeaders.AUTHORIZATION 를 캐치해내고 basic token인지를 확인하고
    * basic token이라면  뒷 부분을 읽어서 인코딩하고 split한 다음 UsernamePasswordAuthenticationtToken을 만들어서
    * setDetails로 디테일정보를 담은 다음에 넘긴다.
    *
    * Authentication manager에게 authRequest를 authenticate로 넘긴다. 그전에 basicAuthenticationToken같은 경우에는
    * Authentication이 필요한지 한번 더 체크를 한다. Security Context Holder를 뒤져봐서 security가없다. 아직 한번도 인증받지 않은 상황이다.
    * 라면 인증을 거쳐주고 이미 인증 받은 사용자나 인증받은 다른 사용자의 경우는 인증을 스킵한다.
    *
    * 그 다음 rememberMeService를 통해 success 이벤트를 날려서 쿠키 세팅해줌
    *  */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(
                        User.withDefaultPasswordEncoder()
                        .username("user1")
                        .password("1111")
                        .roles("USER")
                        .build()
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                ;
    }
}
