package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.Entity.Product;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailDTO {

    private Long productId;
    private String title;
    private Long price;
    private Long amount;
    private Long sum;

    public BucketDetailDTO(Product product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.amount = 1L;
        this.sum = product.getPrice();
    }
}
