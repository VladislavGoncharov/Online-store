package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.entity.OrderStatus;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusDTO {
    private OrderStatus status;

    public static List<OrderStatus> getAllStatuses(){
        return Arrays.asList(
                OrderStatus.СОЗДАН,
                OrderStatus.ПОДТВЕРЖДЕН,
                OrderStatus.ОПЛАЧЕН,
                OrderStatus.ОТМЕНЕН,
                OrderStatus.ЗАКРЫТ,
                OrderStatus.ВОЗВРАЩЕН);
    }
}
