package com.vladgoncharov.eshop.mapper;

import com.vladgoncharov.eshop.entity.Product;
import com.vladgoncharov.eshop.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    default ProductDTO fromProduct(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .category(product.getCategory() == null ? "" : product.getCategory().getTitle())
                .img(product.getImg())
                .build();
    }

    default List<ProductDTO> fromProductList(List<Product> productList) {
        return productList.stream().map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .category(product.getCategory() == null ? "" : product.getCategory().getTitle())
                        .img(product.getImg())
                        .build())
                .collect(Collectors.toList());
    }
}
