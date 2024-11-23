package com.pos.streamline.service;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;
import com.pos.streamline.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest_getProductById {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    /**
     * Test case for saving the product.
     */

    @Test
    public void testGetAllProduct() {
        // Given: Create a list of mock products
        Product product = getProduct();
        // Mock the behavior of the repository
        when(productRepository.getProductById(1L)).thenReturn(product);

        // When: Call the service method to get product
        ProductData actualProductData = productService.getProductById(1L);

        // Then: Validate that the list size is correct and that the mapping works
        assertEquals(product.getId(), actualProductData.getId());
        assertEquals(product.getName(), actualProductData.getName());
        assertEquals(product.getDescription(), actualProductData.getDescription());
        assertEquals(product.getPrice(), actualProductData.getPrice());
        assertEquals(product.getQuantity(), actualProductData.getQuantity());


    }

    public Product getProduct() {
        // Given: Create a list of mock products
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Description 1");
        product.setPrice(100.0);
        product.setQuantity(10);
        return product;
    }
}
