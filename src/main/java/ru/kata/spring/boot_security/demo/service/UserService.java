package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Repository.UserRepository;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User findUserById(Long id) {

        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateToUser(User user, Long id) {

        User userForUpdate = findUserById(id);
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setAge(user.getAge());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userForUpdate.setRoles(user.getRoles());
        userRepository.save(userForUpdate);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User converToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO converToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

}
