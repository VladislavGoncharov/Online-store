package com.vladgoncharov.eshop.service.productAndCategoriesService;

import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dao.ProductRepository;
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

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService, CategoriesService categoriesService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
        this.categoriesService = categoriesService;
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

        productRepository.save(Product.builder()
                .id(productDTO.getId())
                .title(productDTO.getTitle())
                .price(productDTO.getPrice())
                .categories(productDTO.getCategories().stream()
                        .map(categoriesService::findFirstByTitle)
                        .collect(Collectors.toList()))
                .build());
    }

}
