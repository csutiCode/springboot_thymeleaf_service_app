package com.example.wifidemo.security;

import com.example.wifidemo.model.User;
import com.example.wifidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserNamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<User> users = userRepository.findByUsername(username);
        System.out.println(users.get(0).getPassword());
        if (users.size() > 0) {
            if (passwordEncoder.matches(password, users.get(0).getPassword())) {
                return new UsernamePasswordAuthenticationToken(username,password, null);

            } else {
                throw new BadCredentialsException("Invalid password");
            }

        } else {
            throw  new BadCredentialsException("No user registered with this details");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }
}
