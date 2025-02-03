package ru.kata.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.Setter;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String username;

    private int age;

    private String email;

    private String password;

    List<Role> roles;
}
