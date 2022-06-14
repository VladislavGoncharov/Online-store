package com.vladgoncharov.eshop.service.productAndCategoriesService;

import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    void addToUserBucket(Long productId, String username);
    Product findFirstByTitle(String title);
    Product findFirstById(Long id);

    void deleteById(Long id);

    void save(ProductDTO productDTO);
}
