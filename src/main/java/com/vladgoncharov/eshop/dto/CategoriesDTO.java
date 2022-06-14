package com.vladgoncharov.eshop.dto;

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
}
