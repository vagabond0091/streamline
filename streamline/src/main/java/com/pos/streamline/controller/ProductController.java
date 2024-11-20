package com.pos.streamline.controller;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;
import com.pos.streamline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping("/create")
    public ResponseEntity<ProductData> createProduct(@RequestBody ProductData productData) throws Exception {
        ProductData projectData = null;
        try{
            projectData =  productService.saveProduct(productData);
        }
        catch(Exception e){
            e.fillInStackTrace();
            throw new Exception("error in saving."+e.getMessage());
        }
        return new ResponseEntity<>(projectData, HttpStatus.OK);
    }
}
