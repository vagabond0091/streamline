package com.pos.streamline.serviceimpl;

import com.pos.streamline.data.ProductData;

import java.util.List;

public interface  ProductServiceImpl {
    ProductData saveProduct(ProductData productData);
    List<ProductData> getAllProduct();
    ProductData getProductById(Long productId);
}

