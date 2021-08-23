package com.sp.fc.user.service;

import com.sp.fc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 // DaoSecurityProvider는 UserDetailsService를 필요로하기 때문에 그때 UserSecurityService 를 넘겨주면 됨 

@Service
@RequiredArgsConstructor
@Transactional
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new IllegalArgumentException(username+" 사용자가 존재하지 않습니다."));
    }
}
