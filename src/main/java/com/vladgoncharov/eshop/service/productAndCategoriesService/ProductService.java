package com.vladgoncharov.eshop.service.productAndCategoriesService;

import com.vladgoncharov.eshop.entity.Product;
import com.vladgoncharov.eshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();

    void addToUserBucket(Long productId, String username);

    Product getById(Long id);

    void deleteById(Long id);

    void save(ProductDTO productDTO);

    void update(ProductDTO productDTO);
}
