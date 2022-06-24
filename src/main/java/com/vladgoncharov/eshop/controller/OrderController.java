package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.Entity.OrderStatus;
import com.vladgoncharov.eshop.Entity.User;
import com.vladgoncharov.eshop.dto.AddressForOrder;
import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.dto.OrderDTO;
import com.vladgoncharov.eshop.service.bucketAndOrdersService.BucketService;
import com.vladgoncharov.eshop.service.bucketAndOrdersService.OrderService;
import com.vladgoncharov.eshop.service.userService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final BucketService bucketService;
    private final UserService userService;

    public OrderController(OrderService orderService, BucketService bucketService, UserService userService) {
        this.orderService = orderService;
        this.bucketService = bucketService;
        this.userService = userService;
    }

    @RequestMapping
    public String ordersOfAnAuthorizedUser(Principal principal, Model model) {

        List<OrderDTO> orders = orderService.findOrdersByUser(principal.getName());
        model.addAttribute("orders", orders);

        return "orders";
    }

    @GetMapping("/new")
    public String confirmationOfPurchase(Principal principal, Model model) {
        BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
        User user = userService.findFirstByUsername(principal.getName());

        if (!user.getAddress().isEmpty()) {
            model.addAttribute("currentAddress"
                    , new AddressForOrder(user.getAddress(), "", "", ""));
        }

        model.addAttribute("bucket", bucketDTO);
        model.addAttribute("newAddress", new AddressForOrder());

        return "confirmation-of-purchase";
    }

    @PostMapping("/new")
    public String newOrder(@ModelAttribute("address") AddressForOrder address, Principal principal, Model model) {
        User user = userService.findFirstByUsername(principal.getName());

        if (user.getAddress().isEmpty()) {
            user.setAddress(address.fullAddress());
            userService.save(user);
        }

        orderService.save(principal.getName(), address.fullAddress());
        bucketService.deleteAllProductUser(principal.getName());

        model.addAttribute("orders", orderService.findOrdersByUser(principal.getName()));
        return "redirect:/orders";
    }

    @GetMapping("/cancel-status/{id}")
    public String changeStatus(@PathVariable("id") Long id) {
        orderService.setStatusOrder(id, OrderStatus.ОТМЕНЕН);
        return "redirect:/orders";
    }
}
