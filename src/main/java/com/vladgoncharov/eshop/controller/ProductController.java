package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.dto.BucketDetailDTO;
import com.vladgoncharov.eshop.dto.OrderStatusDTO;
import com.vladgoncharov.eshop.service.productAndCategoriesService.ProductService;
import com.vladgoncharov.eshop.utils.BucketUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String allProduct(Model model, HttpServletRequest request) {

        model.addAttribute("products", productService.findAll());
        model.addAttribute("anonymousBucket", BucketUtil.getBucketInSession(request));

        return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addProductInBucket(@PathVariable Long id, Principal principal, HttpServletRequest request) {
        if (principal != null)
            productService.addToUserBucket(id, principal.getName());
        else {
            Product product = productService.getById(id);
            BucketDTO bucketDTO = (BucketDTO) request.getSession().getAttribute("anonymousBucket");
            bucketDTO.getBucketDetails().add(new BucketDetailDTO(product));
        }
        return "redirect:/products";
    }

}
