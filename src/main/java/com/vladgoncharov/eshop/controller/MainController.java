package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.entity.Role;
import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.dto.BucketDetailDTO;
import com.vladgoncharov.eshop.dto.ProductDTO;
import com.vladgoncharov.eshop.dto.UserDTO;
import com.vladgoncharov.eshop.service.bucketAndOrdersService.BucketService;
import com.vladgoncharov.eshop.service.productAndCategoriesService.ProductService;
import com.vladgoncharov.eshop.service.userService.UserService;
import com.vladgoncharov.eshop.utils.BucketUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class MainController {

    private final UserService userService;
    private final BucketService bucketService;
    private final ProductService productService;

    public MainController(UserService userService, BucketService bucketService, ProductService productService) {
        this.userService = userService;
        this.bucketService = bucketService;
        this.productService = productService;
    }

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {

        List<ProductDTO> productDTOS = productService.findAll();
        List<ProductDTO> productRandom = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            productRandom.add(productDTOS.get(new Random().nextInt(productDTOS.size())));
        }
        model.addAttribute("productsRandom", productRandom);
        model.addAttribute("anonymousBucket", BucketUtil.getBucketInSession(request));
        return "index";
    }

    // Добавление товаров в корзину пользователя из анонимной корзины
    @RequestMapping("/transferOfBasket")
    public String transferOfBasketAfterSuccessfulRegistration(HttpServletRequest request, Principal principal) {
        BucketDTO bucketDTO = (BucketDTO) request.getSession().getAttribute("anonymousBucket");

        if (bucketDTO.getBucketDetails() != null) {
            for (BucketDetailDTO product : bucketDTO.getBucketDetails())
                for (int i = 0; i < product.getAmount(); i++) {
                    bucketService.addProductInBucket(principal.getName(),product.getProductId());
                }
        }
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/registration")
    public String registrationUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration-for-user";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDTO userDTO, Model model) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            model.addAttribute("registrationError", "Пароли не совпадают");
            return "registration-for-user";
        }

        if ((userService.findFirstByUsername(userDTO.getUsername())) != null) {
            model.addAttribute("registrationError", "Такой пользователь уже существует");
            return "registration-for-user";
        }

        userService.save(userDTO, Role.USER);
        return "redirect:/";
    }
}
