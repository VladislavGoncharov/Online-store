package com.vladgoncharov.eshop.service.userService;

import com.vladgoncharov.eshop.Entity.Role;
import com.vladgoncharov.eshop.Entity.User;
import com.vladgoncharov.eshop.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserRepository {

    private final com.vladgoncharov.eshop.dao.UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(com.vladgoncharov.eshop.dao.UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Такой пользователь не найден: " + username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(UserDTO userDTO, Role role) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .address("")
                .role(role)
                .build();

        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getUsersByRole(Role role) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals(role))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDTO toDto(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .address(user.getAddress())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public void updateProfile(UserDTO userDTO) {
        User saveUser = userRepository.findFirstByUsername(userDTO.getUsername());

        saveUser.setEmail(userDTO.getEmail());
        saveUser.setAddress(userDTO.getAddress());
        saveUser.setPhone(userDTO.getPhone());
        userRepository.save(saveUser);

    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
