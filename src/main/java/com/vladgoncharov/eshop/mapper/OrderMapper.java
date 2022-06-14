package com.vladgoncharov.eshop.mapper;

import com.vladgoncharov.eshop.Entity.Order;
import com.vladgoncharov.eshop.Entity.OrderDetails;
import com.vladgoncharov.eshop.dto.OrderDTO;
import com.vladgoncharov.eshop.dto.OrderDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {

    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    default List<OrderDTO> fromOrderList(List<Order> orders) {
        return orders.stream().map(this::fromOrder).collect(Collectors.toList());
    }

    default OrderDTO fromOrder(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .username(order.getUser().getUsername())
                .address(order.getAddress())
                .created(order.getCreated())
                .sum(order.getSum())
                .details(order.getDetails().stream().map(this::toOrderDetailsDTO).collect(Collectors.toList()))
                .status(order.getStatus())
                .updated(order.getUpdated())
                .build();
    }

    default OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails){
        return OrderDetailsDTO.builder()
                .product(orderDetails.getProduct())
                .price(orderDetails.getPrice())
                .amount(orderDetails.getAmount())
                .build();
    }

}
