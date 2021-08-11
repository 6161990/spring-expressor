package com.sp.fc.web.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthDetail customAuthDetail;

    public SecurityConfig(CustomAuthDetail customAuthDetail) {
        this.customAuthDetail = customAuthDetail;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(
                        User.withDefaultPasswordEncoder()
                                .username("user1")
                                .password("1111")
                                .roles("USER")
                ).withUser(
                        User.withDefaultPasswordEncoder()
                                .username("admin")
                                .password("2222")
                                .roles("ADMIN")
        );

        /* withDefaultPasswordEncoder : PasswordEncoder 메소드 (안전하지 않아서 Test에 한정해서 사용) */
    }

    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        return roleHierarchy;
    }

    /* ADMIN은 USER보다 많은 권한을 가지고 있다.
    * 관리자가 USER보다 많은 페이지에 접근 할 수 있게됨 */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(request-> {
                    request.antMatchers("/").permitAll()
                            .anyRequest().authenticated();  /* 이렇게만 하면 css가 적용되지 않음 */

                })
                .formLogin(
                        login->login.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/",false)
                        .failureUrl("/login-error")
                        .authenticationDetailsSource(customAuthDetail)
                )
                .logout(logout->logout.logoutSuccessUrl("/"))
                .exceptionHandling(exception-> exception.accessDeniedPage("/access-denied"))
                ;
            /*

            1.
            이 상태에서 user 페이지 접근 시 막힘(loginForm 이 뜨지 않음 ),
            request 처리해주는 filter를 아직 적용해주지 않았기 때문.
            UsernamePasswordAuthenticationFilter -> formlogin 적용.
            유저 페이지 버튼 누르면 그제서야 디폴트 login form 뜸.
            아무것도 지정해주지 않으면 디폴트 login form 이 띄워지게 됨.

            로그인 page 자체는 permition이 허용되지 않은, 권한을 얻어야만 들어올 수 있는 페이지로 인식이 되고 있기 때문에
            permitAll을 지정.

            2.
             .defaultSuccessUrl("/",false)
             갈때가 없으면 "/" -> 홈으로
             로그인에서 걸린 거라면 로그인 성공 후, 원래 가려던 곳으로

             3. log 실패시,
              디폴트로 다시 loginForm 으로 가게끔 되어있음.
              디본 디폴트 url : login?error

              직접 설정한다면 : .failureUrl("/login-error")


              4. 로그아웃버튼을 누르면 디폴트로 로그인 form으로 가게되는데, 이를 홈으로 change
               그리고 메인 페이지에서 로그인이 안되어있을 경우, 로그인 버튼만 보이고
                다른 메뉴(유저 페이지, 관리자페이지 등)은 안보이게 설정
              -> 타임리프에서 security를 적용할 수 있는 태그 이용


               5. EnableGlobalMethodSecurity true 적용으로 인해 (controller ROLE 참고)
               유저가 관리자 페이지로 갈 경우, 에러 페이지 뜸
               접근 에러를 ExceptionHandling 으로 에러날 경우 page 처리

               6. authenticationDetailsSource
               사용자의 request detail을 얻기 위해 customizing
            */
    }


    /* 웹 리소스 (css, ) StaticResources 에 대해서는 security filter가 적용되지 않도록 ignore 시켜주어야함  */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                );
    }

}
