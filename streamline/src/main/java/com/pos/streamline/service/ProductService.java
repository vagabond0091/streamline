package com.pos.streamline.service;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;
import com.pos.streamline.repository.ProductRepository;
import com.pos.streamline.serviceimpl.ProductServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.streamline.mapper.ProductMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceImpl {
    private ProductMapper productMapper;
    private static final String UPLOAD_DIR = "uploads/";
    @Autowired
    private ProductRepository productRepository;
    /**
     * save the product and return the response
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
     * return all products
     *
     */
    @Override
    public List<ProductData> getAllProduct() {

        return  productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     *
     * return single product
     */
    @Override
    public ProductData getProductById(Long productId) {
        Product product = productRepository.getProductById(productId);
        if(product == null){
            throw new EntityNotFoundException("Product not found with ID: " + productId);
        }
        return ProductMapper.toDTO(product);
    }

    /**
     * update product data
     */
    public ProductData updateProduct(ProductData productData) {
        Product product = productRepository.getProductById(productData.getId());
        if(product == null){
            throw new jakarta.persistence.EntityNotFoundException(
                    "Product not found with ID: " + productData.getId()
            );

        }
        product.setTitle(productData.getTitle());
        product.setDescription(productData.getDescription());
        product.setPrice(productData.getPrice());
        product.setQuantity(productData.getQuantity());
        productRepository.save(product);
        return ProductMapper.toDTO(product);
    }
    /**
     * delete single product
     */
    @Override
    public ProductData deleteProductById(Long productId) {
        Product product = productRepository.getProductById(productId);
        if(product == null){
            throw new EntityNotFoundException("Product not found with ID: " + productId);

        }
         productRepository.delete(product);

        return ProductMapper.toDTO(product);
    }

    public String saveImage(MultipartFile file) throws IOException {
        Files.createDirectories(Paths.get(UPLOAD_DIR));
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/" + fileName;  // Store file URL in DB
    }

}
