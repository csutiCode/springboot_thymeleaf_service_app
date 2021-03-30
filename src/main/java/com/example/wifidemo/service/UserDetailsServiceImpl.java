package com.example.wifidemo.service;

import com.example.wifidemo.dto.UserDto;
import com.example.wifidemo.model.AuthenticatedUser;
import com.example.wifidemo.model.User;
import com.example.wifidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository  userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The user " + username + " does not exist");
        }
        return new AuthenticatedUser(user.get(0));
    }

    public boolean checkUserInDb(UserDto dto) {
        if (!userRepository.findByUsername(dto.getUsername()).isEmpty()) {
            return true;
        } else {
            return false;
        }

    }






}
