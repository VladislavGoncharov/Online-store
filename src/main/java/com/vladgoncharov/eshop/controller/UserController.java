package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.Entity.User;
import com.vladgoncharov.eshop.dto.UserDTO;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profileUser(Model model, Principal principal) {
        User user = userService.findFirstByUsername(principal.getName());

        UserDTO userDTO = UserDTO.builder()
                .username(user.getUsername())
                .address(user.getAddress())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
        model.addAttribute("userDTO", userDTO);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfileUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model, Principal principal) {
        if (principal == null || !Objects.equals(principal.getName(), userDTO.getUsername())) {
            throw new RuntimeException("You are not authorize");
        }
        if (userDTO.getPassword() != null
                && !userDTO.getPassword().isEmpty()
                && !Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            // нужно добавить какое-то сообщение, но это потом
            return "profile";
        }
        userService.updateProfile(userDTO);
        return "redirect:/users/profile";
    }

}
