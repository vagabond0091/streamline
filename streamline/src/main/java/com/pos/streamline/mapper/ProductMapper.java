package com.pos.streamline.mapper;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;

public class ProductMapper {
    public static ProductData toDTO(Product product) {
        return new ProductData(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getFrontImgUrl(),
                product.getRightImgUrl(),
                product.getLeftImgUrl(),
                product.getBackImgUrl()
        );
    }

    public static Product toEntity(ProductData productData) {
        Product product = new Product();
        product.setId(productData.getId());
        product.setTitle(productData.getTitle());
        product.setDescription(productData.getDescription());
        product.setPrice(productData.getPrice());
        product.setQuantity(productData.getQuantity());
        product.setFrontImgUrl(productData.getFrontImgUrl());
        product.setRightImgUrl(productData.getLeftImgUrl());
        product.setLeftImgUrl(productData.getLeftImgUrl());
        product.setBackImgUrl(productData.getBackImgUrl());
        return product;
    }
}
