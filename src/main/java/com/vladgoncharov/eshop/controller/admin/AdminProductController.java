package com.vladgoncharov.eshop.controller.admin;

import com.vladgoncharov.eshop.dto.CategoriesDTO;
import com.vladgoncharov.eshop.dto.ProductDTO;
import com.vladgoncharov.eshop.mapper.ProductMapper;
import com.vladgoncharov.eshop.service.productAndCategoriesService.CategoriesService;
import com.vladgoncharov.eshop.service.productAndCategoriesService.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    ProductMapper productMapper = ProductMapper.MAPPER;

    private final ProductService productService;
    private final CategoriesService categoriesService;

    public AdminProductController(ProductService productService, CategoriesService categoriesService) {
        this.productService = productService;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/all-categories")
    public String allCategories(Model model) {

        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("category", new CategoriesDTO());

        return "all-categories";
    }

    @PostMapping("all-categories")
    public String saveCategories(@ModelAttribute("newCategories") CategoriesDTO categoriesDTO
            , Model model) {

        try {
            categoriesService.save(categoriesDTO);
        } catch (RuntimeException e) {
            return allCategories(model);
        }

        return "redirect:/admin/all-categories";
    }

    @RequestMapping("all-categories/delete-{id}")
    public String deleteCategories(@PathVariable Long id) {
        categoriesService.deleteById(id);
        return "redirect:/admin/all-categories";
    }

    @GetMapping("/all-product")
    public String allProduct(Model model) {

        model.addAttribute("allProduct", productService.findAll());
        model.addAttribute("newProduct", new ProductDTO());
        model.addAttribute("allCategory", categoriesService.findAll());

        return "all-product";
    }

    @PostMapping("all-product")
    public String saveProduct(@ModelAttribute("newProduct") ProductDTO productDTO
            , Model model) {

        try {
            if (productDTO.getId() == null) productService.save(productDTO);
            else productService.update(productDTO);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return allProduct(model);
        }

        return "redirect:/admin/all-product";
    }

    @RequestMapping("all-product/delete-{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/all-product";
    }

    @RequestMapping("all-product/update-{id}")
    public String updateProduct(@PathVariable Long id, Model model) {

        model.addAttribute("allProduct", productService.findAll());
        model.addAttribute("newProduct",
                productMapper.fromProduct(productService.getById(id)));
        model.addAttribute("allCategory", categoriesService.findAll());

        return "all-product";
    }
}
