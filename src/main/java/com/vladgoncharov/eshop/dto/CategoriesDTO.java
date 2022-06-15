package com.vladgoncharov.eshop.dto;

import com.vladgoncharov.eshop.Entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesDTO {
    private Long id;
    private String title;
    private List<ProductDTO> products;
}
