package com.pos.streamline.controller;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/v1/auth")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
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
    public ResponseEntity<?> createProduct(@RequestPart("product") ProductData productData, @RequestPart(value = "frontImage", required = false) MultipartFile frontImage,
        @RequestPart(value = "leftImage", required = false) MultipartFile leftImage,@RequestPart(value = "rightImage", required = false) MultipartFile rightImage,
        @RequestPart(value = "backImage", required = false) MultipartFile backImage ) throws Exception  {
        ProductData product = null;
        try{
            if (frontImage != null) {
                productData.setFrontImgUrl(productService.saveImage(frontImage));
            }
            if (leftImage != null) {
                productData.setLeftImgUrl(productService.saveImage(leftImage));
            }
            if (rightImage != null) {
                productData.setRightImgUrl(productService.saveImage(rightImage));
            }
            if (backImage != null) {
                productData.setBackImgUrl(productService.saveImage(backImage));
            }
            product =  productService.saveProduct(productData);


        } catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
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
            throw new Exception("error in deleting data."+e.getMessage());
        }
        return new ResponseEntity<>(projectData, HttpStatus.OK);
    }
    @PutMapping("/updateProduct/")
    public ResponseEntity<ProductData> updateProduct(@RequestBody ProductData productData) throws Exception {
        ProductData projectData = null;
        try{
            projectData =  productService.updateProduct(productData);
        }
        catch(Exception e){
            e.fillInStackTrace();
            throw new Exception("error in updating data."+e.getMessage());
        }
        return new ResponseEntity<>(projectData, HttpStatus.OK);
    }

}
