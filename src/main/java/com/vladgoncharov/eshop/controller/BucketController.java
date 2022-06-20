package com.vladgoncharov.eshop.controller;

import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.service.bucketAndOrdersService.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping
    public String bucketView(Model model, Principal principal, HttpServletRequest request) {

        BucketDTO bucketDTO;
        if (principal == null) bucketDTO = bucketService.getBucketByAnonymous(request);
        else bucketDTO = bucketService.getBucketByUser(principal.getName());
        model.addAttribute("bucket", bucketDTO);

        return "bucket";
    }

    @GetMapping("/delete-{title}")
    public String deleteProductFromBucket(@PathVariable String title, Model model
            , Principal principal, HttpServletRequest request) {

        BucketDTO bucketDTO;
        if (principal == null) { // удаление товара из корзины в сессии

            bucketService.deleteProductInBucket(null, title, request);
            bucketDTO = bucketService.getBucketByAnonymous(request);

        } else { // удаление товара из корзины в базе данных

            bucketService.deleteProductInBucket(principal.getName(), title, null);
            bucketDTO = bucketService.getBucketByUser(principal.getName());
        }

        model.addAttribute("bucket", bucketDTO);
        return "bucket";
    }
}
