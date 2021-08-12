package com.sp.fc.web.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentAuthenticationToken implements Authentication {

    private String credentials;
    private String details;
    private Student principal;
    private boolean authenticated;  // 현재 통행증의 인증도장


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return principal == null ? new HashSet<>() : principal.getRole();
    }


    @Override
    public String getName() {
        return principal == null ? "" : principal.getUsername();
    }

    /*
    상단에 선언
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    student가 principal 역할을 하기 때문에 상단에 자동으로 만들어지게끔 선언
    @Override
    public Object getPrincipal() {
        return null;
    }

     @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    */
}
