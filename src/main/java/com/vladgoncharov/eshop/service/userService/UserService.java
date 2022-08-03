package com.vladgoncharov.eshop.service.userService;

import com.vladgoncharov.eshop.entity.Role;
import com.vladgoncharov.eshop.entity.User;
import com.vladgoncharov.eshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    void save(UserDTO userDTO, Role role);

    List<UserDTO> getUsersByRole(Role role);


    User findFirstByUsername(String name);

    void updateProfile(UserDTO userDTO);

    void deleteByUsername(String username);
}


