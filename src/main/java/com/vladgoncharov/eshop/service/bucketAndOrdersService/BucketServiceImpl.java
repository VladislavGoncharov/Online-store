package com.vladgoncharov.eshop.service.bucketAndOrdersService;

import com.vladgoncharov.eshop.Entity.Bucket;
import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.Entity.User;
import com.vladgoncharov.eshop.dao.BucketRepository;
import com.vladgoncharov.eshop.dao.ProductRepository;
import com.vladgoncharov.eshop.dao.UserRepository;
import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.dto.BucketDetailDTO;
import com.vladgoncharov.eshop.utils.BucketUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void deleteProductInBucket(String username, String title, HttpServletRequest request) {
        if (username == null) { // удаление товара из корзины анонимного пользователя
            BucketDTO bucketDTO = (BucketDTO) request.getSession().getAttribute("anonymousBucket");

            BucketDetailDTO product = bucketDTO.getBucketDetails().stream()
                    .filter(prod -> prod.getTitle().equals(title))
                    .findFirst()
                    .get();

            if (product.getAmount() > 1) product.setAmount(product.getAmount() - 1L);
            else bucketDTO.getBucketDetails().remove(product);
        } else {   // удаление товара из корзины зарегистрированного пользователя
            User user = userRepository.findFirstByUsername(username);
            user.getBucket().getProducts().remove(productRepository.findFirstByTitle(title));
            userRepository.save(user);
        }
    }

    @Override
    public void deleteAllProductUser(String name) {
        User user = userRepository.findFirstByUsername(name);
        Bucket bucket = user.getBucket();
        bucket.getProducts().removeAll(bucket.getProducts());
        user.setBucket(bucket);
        userRepository.save(user);
    }

    @Override
    public void addProductInBucket(String username, Long productId) {
        Bucket bucket = userRepository.findFirstByUsername(username).getBucket();
        if (bucket == null) {
            User user = userRepository.findFirstByUsername(username);

            bucket = new Bucket();
            bucket.setUser(user);
            user.setBucket(bucket);

            List<Product> productList = Collections.singletonList(productRepository.getById(productId));
            bucket.setProducts(productList);

            userRepository.save(user);
            bucketRepository.save(bucket);
        } else {
            List<Product> products = new ArrayList<>(bucket.getProducts());
            products.add(productRepository.getById(productId));
            bucket.setProducts(products);
            bucketRepository.save(bucket);
        }

    }

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userRepository.findFirstByUsername(name);

        if (user == null || user.getBucket() == null) {
            return new BucketDTO();
        }

        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();
        for (Product product : user.getBucket().getProducts()) {
            if (!mapByProductId.containsKey(product.getId())) { // добавить продукт если его нет
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));
            } else {  // если есть, изменить их количество и общую сумму
                BucketDetailDTO bucketDetail = mapByProductId.get(product.getId());
                bucketDetail.setAmount(bucketDetail.getAmount() + 1L);
                bucketDetail.setSum(bucketDetail.getSum() + product.getPrice());
            }
        }

        BucketDTO bucketDTO = new BucketDTO();
        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }

    @Override
    public BucketDTO getBucketByAnonymous(HttpServletRequest request) {
        BucketDTO bucketDTO = (BucketDTO) request.getSession().getAttribute("anonymousBucket");

        if (bucketDTO == null) {
            return BucketUtil.getBucketInSession(request);
        }

        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();
        for (BucketDetailDTO product : bucketDTO.getBucketDetails()) {

            if (!mapByProductId.containsKey(product.getProductId())) {
                mapByProductId.put(product.getProductId(), product);
            } else {
                BucketDetailDTO bucketDetail = mapByProductId.get(product.getProductId());
                bucketDetail.setAmount(bucketDetail.getAmount() + 1L);
                bucketDetail.setSum(bucketDetail.getSum() + product.getPrice());
            }
        }

        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }

}
