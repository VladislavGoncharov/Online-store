package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.Entity.OrderDetails;
import com.vladgoncharov.eshop.Entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderDetailsDTO {

    private Product product;
    private Long amount;
    private Long price;

}
