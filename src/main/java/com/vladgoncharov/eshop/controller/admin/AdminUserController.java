package com.vladgoncharov.eshop.controller.admin;

import com.vladgoncharov.eshop.Entity.Role;
import com.vladgoncharov.eshop.dto.UserDTO;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminUserController {


    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public String allUser(Model model) {
        model.addAttribute("users", userService.getUsersByRole(Role.USER));
        return "all-users";
    }

    @GetMapping("/all-moderators")
    public String allModerator(Model model) {
        model.addAttribute("moderators", userService.getUsersByRole(Role.MODERATOR));
        return "all-moderators";
    }

    @GetMapping("/new-moderator")
    public String registrationModerator(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration-for-moderator";
    }

    @PostMapping("/new-moderator")
    public String saveModerator(@ModelAttribute("user") UserDTO userDTO, Model model) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            model.addAttribute("registrationError", "Пароли не совпадают");
            return "registration-for-moderator";
        }

        if ((userService.findFirstByUsername(userDTO.getUsername())) != null) {
            model.addAttribute("registrationError", "Такой пользователь уже существует");
            return "registration-for-moderator";
        }

        userService.save(userDTO, Role.MODERATOR);
        return "redirect:/admin";
    }

    @GetMapping("/remove/{username}")
    public String removeUser(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
        return "redirect:/admin/all-users";
    }
}
