package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.Entity.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String username;
    private Long sum;
    private String address;
    private List<OrderDetailsDTO> details;
    private OrderStatus status;

    public static List<OrderDTO> sortedForCreatedDate(List<OrderDTO> orderDTOS) {
        orderDTOS.sort((o1, o2) -> {
            if (o1.getCreated().isBefore(o2.getCreated())) return 1;
            else return -1;
        });
        return orderDTOS;
    }

    public String getFormatDate(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yy"));
    }

}