package com.vladgoncharov.eshop.mapper;

import com.vladgoncharov.eshop.Entity.Order;
import com.vladgoncharov.eshop.Entity.OrderDetails;
import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dto.BucketDetailDTO;
import com.vladgoncharov.eshop.dto.OrderDetailsDTO;
import com.vladgoncharov.eshop.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderDetailsMapper {

    OrderDetailsMapper MAPPER = Mappers.getMapper(OrderDetailsMapper.class);

    default OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails){
        return OrderDetailsDTO.builder()
                .product(orderDetails.getProduct())
                .price(orderDetails.getPrice())
                .amount(orderDetails.getAmount())
                .build();
    }
}
