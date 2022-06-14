package com.vladgoncharov.eshop.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BucketDTO {

    private int amountProducts;
    private Long sum;
    private List<BucketDetailDTO> bucketDetails = new ArrayList<>();

    public void aggregate() {
        this.amountProducts = bucketDetails.size();
        this.sum = bucketDetails.stream()
                .map(BucketDetailDTO::getSum)
                .mapToLong(Long::longValue)
                .sum();

    }

}
