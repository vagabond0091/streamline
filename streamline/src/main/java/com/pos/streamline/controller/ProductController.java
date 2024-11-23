package com.pos.streamline.controller;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public  ResponseEntity<List<ProductData>> getAllProduct() throws Exception {
        List<ProductData> projectData = null;
        try{

            projectData =  productService.getAllProduct();
        }
        catch(Exception e){
            e.fillInStackTrace();
            throw new Exception("error in saving."+e.getMessage());
        }
        return new ResponseEntity<>(projectData, HttpStatus.OK);
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
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductData> getProduct(@PathVariable Long id) throws Exception {
        ProductData projectData = null;
        try{
            projectData =  productService.getProductById(id);
        }
        catch(Exception e){
            e.fillInStackTrace();
            throw new Exception("error in fetching data."+e.getMessage());
        }
        return new ResponseEntity<>(projectData, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductData> deleteProduct(@PathVariable Long id) throws Exception {
        ProductData projectData = null;
        try{
            projectData =  productService.deleteProductById(id);
        }
        catch(Exception e){
            e.fillInStackTrace();
            throw new Exception("error in fetching data."+e.getMessage());
        }
        return new ResponseEntity<>(projectData, HttpStatus.OK);
    }

}
