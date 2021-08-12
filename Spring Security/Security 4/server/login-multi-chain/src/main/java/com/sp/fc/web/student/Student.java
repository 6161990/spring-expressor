package com.sp.fc.web.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    private String id;
    private String username;

    @JsonIgnore // GrantedAuthority는 json으로  serialization 하기 어려우므로로
   private Set<GrantedAuthority> role;

    private String teacherId;
}
