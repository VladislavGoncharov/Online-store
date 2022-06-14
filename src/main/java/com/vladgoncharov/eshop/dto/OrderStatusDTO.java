package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.Entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderStatusDTO {
    private OrderStatus status;

    public static List<OrderStatus> getAllStatuses(){
        return Arrays.asList(
                OrderStatus.NEW,
                OrderStatus.PAID,
                OrderStatus.CANSEL,
                OrderStatus.CLOSED,
                OrderStatus.RETURNED);
    }
}
