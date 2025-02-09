package ru.kata.spring.boot_security.demo.coverterDTO;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;

@Component
@RequiredArgsConstructor
public class ConverterToUser {

    private final ModelMapper modelMapper;

    public User converToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
