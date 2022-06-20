package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.Entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDTO {

    private int amountProducts;
    private Long sum;
    private List<BucketDetailDTO> bucketDetails = new ArrayList<>();

    public BucketDTO(List<Product> productList){

        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();
        for (Product product : productList) {
            if (!mapByProductId.containsKey(product.getId())) { // добавить продукт если его нет
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));
            } else {  // если есть, изменить их количество и общую сумму
                BucketDetailDTO bucketDetail = mapByProductId.get(product.getId());
                bucketDetail.setAmount(bucketDetail.getAmount() + 1L);
                bucketDetail.setSum(bucketDetail.getSum() + product.getPrice());
            }
        }
    }

    public void aggregate() {
        this.amountProducts = bucketDetails.size();
        this.sum = bucketDetails.stream()
                .map(BucketDetailDTO::getSum)
                .mapToLong(Long::longValue)
                .sum();

    }

}
