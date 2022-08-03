package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.entity.User;
import com.vladgoncharov.eshop.dto.UserDTO;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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
    public String updateProfileUser(@ModelAttribute("userDTO") UserDTO userDTO) {

        userService.updateProfile(userDTO);
        return "redirect:/users/profile";
    }

}
