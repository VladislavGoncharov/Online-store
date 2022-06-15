package com.vladgoncharov.eshop.service.productAndCategoriesService;

import com.vladgoncharov.eshop.Entity.Category;
import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dao.CategoriesRepository;
import com.vladgoncharov.eshop.dao.ProductRepository;
import com.vladgoncharov.eshop.dto.CategoriesDTO;
import com.vladgoncharov.eshop.dto.ProductDTO;
import com.vladgoncharov.eshop.mapper.ProductMapper;
import com.vladgoncharov.eshop.service.userService.UserService;
import com.vladgoncharov.eshop.service.bucketAndOrdersService.BucketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;
    private final CategoriesService categoriesService;
    private final CategoriesRepository categoriesRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService, CategoriesService categoriesService, CategoriesRepository categoriesRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
        this.categoriesService = categoriesService;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = mapper.fromProductList(productRepository.findAll());
        productDTOS.sort((o1, o2) -> {
            if (o1.getId() > o2.getId()) return 1;
            return -1;
        });
        return productDTOS;
    }

    @Override
    public void addToUserBucket(Long productId, String username) {
        bucketService.addProductInBucket(username, Collections.singletonList(productId));
    }

    @Override
    public Product findFirstByTitle(String title) {
        return productRepository.findFirstByTitle(title);
    }

    @Override
    public Product findFirstById(Long id) {
        return productRepository.findFirstById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void save(ProductDTO productDTO) throws RuntimeException {
        if (productDTO.getId() == null &&
                productRepository.findFirstByTitle(productDTO.getTitle()) != null)
                    throw new RuntimeException("Такой товар уже существует");
        Category category = categoriesService.findFirstByTitle(productDTO.getCategory());
        Product product = Product.builder()
                .id(productDTO.getId())
                .title(productDTO.getTitle())
                .price(productDTO.getPrice())
                .category(category)
                .build();

        productRepository.save(product);

        category.getProduct().add(product);

        categoriesService.save(category);

    }

    @Override
    public void update(ProductDTO productDTO) {
        List<Category> categories = categoriesRepository.findAll();
        Product oldProduct = productRepository.findFirstById(productDTO.getId());
        categories = categories.stream()
                .peek(category -> category.getProduct().remove(oldProduct))
                .collect(Collectors.toList());
        categoriesRepository.saveAll(categories);

        save(productDTO);

    }

}
