package com.vladgoncharov.eshop.controller.admin;


import com.vladgoncharov.eshop.service.bucketAndOrdersService.OrderService;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public final UserService userService;
    public final OrderService orderService;

    public AdminController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping
    public String adminPage() {
        return "for-admin";
    }


}
