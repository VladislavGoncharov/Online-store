package com.vladgoncharov.eshop.mapper;

import com.vladgoncharov.eshop.Entity.Bucket;
import com.vladgoncharov.eshop.Entity.Product;
import com.vladgoncharov.eshop.dto.BucketDTO;
import com.vladgoncharov.eshop.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface BucketMapper {
    BucketMapper MAPPER = Mappers.getMapper(BucketMapper.class);

    default Bucket toBucket(BucketDTO bucketDTO) {
        Bucket bucket = new Bucket();

        List<Product> products = bucketDTO.getBucketDetails().stream()
                .map(bucketDetailDTO -> {
                    Product product = new Product();
                    product.setId(bucketDetailDTO.getProductId());
                    product.setTitle(bucketDetailDTO.getTitle());
                    product.setPrice(bucketDetailDTO.getPrice());
                    return product;   })
                .collect(Collectors.toList());

        bucket.setProducts(products);
        return bucket;
    }

    @InheritInverseConfiguration
    BucketDTO fromBucket(Bucket bucket);


    List<Bucket> toBucketList(List<BucketDTO> bucketDTOList);

    List<BucketDTO> fromBucketList(List<Bucket> bucketList);
}