package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.entity.Product;
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
