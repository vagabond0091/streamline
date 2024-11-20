package com.pos.streamline.serviceimpl;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;

import java.util.List;

public interface  ProductServiceImpl {
    ProductData saveProduct(ProductData productData);
    List<Product> getAllProduct();
}

