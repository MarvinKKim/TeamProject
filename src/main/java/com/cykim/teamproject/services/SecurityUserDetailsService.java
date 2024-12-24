package com.cykim.teamproject.services;

//import com.cykim.teamproject.detail.CustomUserDetails;
import com.cykim.teamproject.entities.UserEntity;
import com.cykim.teamproject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called with username: " + username);

        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        System.out.println("UserEntity found: " + userEntity);

//        return new CustomUserDetails(
//                userEntity.getEmail(),
//                userEntity.getNickname(),
//                userEntity.getPassword(),
//                userEntity.getAuthorities()
//        );
        return userEntity;
    }

}