package com.pos.streamline.service;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;
import com.pos.streamline.repository.ProductRepository;
import com.pos.streamline.serviceimpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.streamline.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImpl {
    @Autowired
    private ProductRepository productRepository;
    /**
     * save the product and return the response
     * @return
     */
    @Override
    public ProductData saveProduct(ProductData productData) {
        // convert dto to entity
        Product product = ProductMapper.toEntity(productData);
        // saved the product to the entity
        Product savedProductData =  productRepository.save(product);
        //return the saved product and convert it to data transfer object
        return ProductMapper.toDTO(savedProductData);
    }

    /**
     *
     * return all products
     * @return
     */
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
