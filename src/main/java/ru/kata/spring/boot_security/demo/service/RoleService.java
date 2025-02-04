package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Repository.RoleRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService  {

    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}
