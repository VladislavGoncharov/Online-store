package com.vladgoncharov.eshop.utils;

import com.vladgoncharov.eshop.dto.BucketDTO;

import javax.servlet.http.HttpServletRequest;

public class BucketUtil {

    public static BucketDTO getBucketInSession(HttpServletRequest request) {

        BucketDTO bucketDTO = (BucketDTO) request.getSession().getAttribute("anonymousBucket");

        if (bucketDTO == null) {
            bucketDTO = new BucketDTO();
            request.getSession().setAttribute("anonymousBucket", bucketDTO);
        }

        return bucketDTO;
    }

}