package com.pos.streamline.mapper;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;

public class ProductMapper {
    public static ProductData toDTO(Product product) {
        return new ProductData(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public static Product toEntity(ProductData productData) {
        Product product = new Product();
        product.setId(productData.getId());
        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        product.setPrice(productData.getPrice());
        product.setQuantity(productData.getQuantity());
        return product;
    }
}
