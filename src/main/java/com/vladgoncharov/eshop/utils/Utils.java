package com.vladgoncharov.eshop.utils;

import com.vladgoncharov.eshop.dto.BucketDTO;

import javax.servlet.http.HttpServletRequest;


public class Utils {

    // Products in Bucket, stored in Session.
    public static BucketDTO getBucketInSession(HttpServletRequest request) {


        // Get Bucket from Session.
        BucketDTO bucketDTO = (BucketDTO) request.getSession().getAttribute("anonymousBucket");

        // If null, create it.
        if (bucketDTO == null) {
            bucketDTO = new BucketDTO();

            // And store to Session.
            request.getSession().setAttribute("anonymousBucket", bucketDTO);
        }

        return bucketDTO;
    }

    public static void removeBucketInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("anonymousBucket");
    }

    public static void storeLastOrderedBucketInSession(HttpServletRequest request, BucketDTO bucketDTO) {
        request.getSession().setAttribute("lastOrderedBucket", bucketDTO);
    }

    public static BucketDTO getLastOrderedBucketInSession(HttpServletRequest request) {
        return (BucketDTO) request.getSession().getAttribute("lastOrderedBucket");
    }

}