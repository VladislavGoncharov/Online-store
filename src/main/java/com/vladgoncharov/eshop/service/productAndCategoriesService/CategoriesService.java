package com.vladgoncharov.eshop.service.productAndCategoriesService;

import com.vladgoncharov.eshop.entity.Category;
import com.vladgoncharov.eshop.dto.CategoriesDTO;

import java.util.List;

public interface CategoriesService {

    void save(CategoriesDTO categoriesDTO) throws RuntimeException;

    Category findFirstByTitle(String title);

    List<CategoriesDTO> findAll();

    void deleteById(Long id);

    void save(Category category);
}
