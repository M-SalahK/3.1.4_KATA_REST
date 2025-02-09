package ru.kata.spring.boot_security.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.coverterDTO.ConverterToUser;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/securityPage")
public class AdminController {

    private final ConverterToUser converterToUser;
    private final UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PatchMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateToUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(converterToUser.converToUser(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
