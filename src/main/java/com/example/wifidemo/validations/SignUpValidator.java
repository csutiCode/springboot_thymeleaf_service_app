package com.example.wifidemo.validations;

import com.example.wifidemo.dto.UserDto;
import com.example.wifidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpValidator {


    public static boolean passwordCheck(UserDto dto) {
        String password = dto.getPassword();
        String repeatedPassword = dto.getRepeatedPassword();
        return password.equals(repeatedPassword) ?  true :  false;

    }

    public static boolean isNull(UserDto dto) {
        return dto.getUsername() == null ||
                dto.getPassword().isBlank() ||
                dto.getRepeatedPassword().isBlank() ? false : true;

    }

}
