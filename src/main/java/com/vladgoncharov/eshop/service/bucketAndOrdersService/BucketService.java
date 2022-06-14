package com.vladgoncharov.eshop.service.bucketAndOrdersService;

import com.vladgoncharov.eshop.dto.BucketDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BucketService {

    void addProductInBucket(String username, List<Long> productIds);
    void deleteProductInBucket(String username, String title, HttpServletRequest request);

    void deleteProductUser(String name);
    BucketDTO getBucketByUser(String name);

    BucketDTO getBucketByAnonymous(HttpServletRequest request);
}
