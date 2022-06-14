package com.vladgoncharov.eshop.mapper;

import com.vladgoncharov.eshop.Entity.Category;
import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    default ProductDTO fromProduct(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .categories(product.getCategories().stream()
                        .map(Category::getTitle)
                        .collect(Collectors.toList()))
                .build();
    }

    default List<ProductDTO> fromProductList(List<Product> productList){
        return productList.stream().map(product -> ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .categories(product.getCategories().stream()
                        .map(Category::getTitle)
                        .collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());
    }
}
