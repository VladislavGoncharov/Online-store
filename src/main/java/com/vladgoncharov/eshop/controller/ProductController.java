package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.dto.BucketDetailDTO;
import com.vladgoncharov.eshop.dto.ProductDTO;
import com.vladgoncharov.eshop.service.productAndCategoriesService.ProductService;
import com.vladgoncharov.eshop.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model, HttpServletRequest request) {
        List<ProductDTO> productList = productService.findAll();
        model.addAttribute("products",productList);

        BucketDTO bucket = Utils.getBucketInSession(request);

        model.addAttribute("anonymousBucket",bucket);

        return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal, HttpServletRequest request) {
        if (principal != null)
            productService.addToUserBucket(id, principal.getName());
        else {
            Product product = productService.findFirstById(id);
            BucketDTO bucketDTO = (BucketDTO) request.getSession().getAttribute("anonymousBucket");
            bucketDTO.getBucketDetails().add(new BucketDetailDTO(product));

        }

        return "redirect:/products";
    }
}
