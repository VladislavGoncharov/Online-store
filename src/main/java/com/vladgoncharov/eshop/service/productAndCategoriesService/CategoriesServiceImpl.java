package com.vladgoncharov.eshop.service.productAndCategoriesService;

import com.vladgoncharov.eshop.Entity.Category;
import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dao.CategoriesRepository;
import com.vladgoncharov.eshop.dao.ProductRepository;
import com.vladgoncharov.eshop.dto.CategoriesDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final ProductRepository productRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, ProductRepository productRepository) {
        this.categoriesRepository = categoriesRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void save(CategoriesDTO categoriesDTO) throws RuntimeException {
        if (categoriesRepository.findFirstByTitle(categoriesDTO.getTitle()) != null)
            throw new RuntimeException("Категория с названием " + categoriesDTO.getTitle() + " уже существует");
        categoriesRepository.save(Category.builder()
                .title(categoriesDTO.getTitle())
                .build());
    }

    @Override
    public Category findFirstByTitle(String title) {
        return categoriesRepository.findFirstByTitle(title);
    }

    @Override
    public List<CategoriesDTO> findAll() {
        return categoriesRepository.findAll().stream()
                .map(category -> CategoriesDTO.builder()
                        .id(category.getId())
                        .title(category.getTitle()).build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoriesRepository.getById(id);
        List<Product> productList = productRepository.findAll();

        productList = productList.stream()
                .filter(product -> product.getCategory() == category)
                .peek(product -> product.setCategory(null))
                .collect(Collectors.toList());
        productRepository.saveAll(productList);

        categoriesRepository.deleteById(id);
    }

    @Override
    public void save(Category category) {
        categoriesRepository.save(category);
    }
}
