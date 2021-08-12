package com.sp.fc.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "sp_user")
public class SpUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    // @OneToMany : user가 Authority를 많이 가지고 있는 형태
    // FetchType.EAGER : 언제나 메모리에 올릴 때 user와 같이 Authority를 같이 올릴때
    // CascadeType.ALL : user와 LifeCycle이 같다.
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id"))
    private Set<SpAuthority> authorities;

    private String email; // username을 email로 쓰는 정책

    private String password;

    private boolean enabled;
/*
private Set<SpAuthority> authorities 로 해결되는 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

private String password 로 해결되는 메소드
    @Override
    public String getPassword() {
        return null;
    }*/

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
