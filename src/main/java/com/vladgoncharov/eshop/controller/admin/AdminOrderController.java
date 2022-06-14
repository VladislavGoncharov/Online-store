package com.vladgoncharov.eshop.controller.admin;

import com.vladgoncharov.eshop.dto.OrderStatusDTO;
import com.vladgoncharov.eshop.service.bucketAndOrdersService.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all-orders")
    public String allOrders(Model model) {

        model.addAttribute("orders",orderService.getAll());
        model.addAttribute("statusDTO",new OrderStatusDTO());
        model.addAttribute("statuses", OrderStatusDTO.getAllStatuses());

        return "all-orders";
    }

    @PostMapping("/all-orders/change-status/{id}")
    public String changeStatus(@PathVariable("id") Long id , @ModelAttribute("statusDTO") OrderStatusDTO statusDTO, Model model) {
        orderService.setStatusOrder(id,statusDTO.getStatus());
        return "redirect:/admin/all-orders";
    }

}
