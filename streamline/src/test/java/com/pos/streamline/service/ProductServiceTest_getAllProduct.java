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
public class ProductServiceTest_getAllProduct {
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
        List<Product> mockProductList = getProduct();
        // Mock the behavior of the repository
        when(productRepository.findAll()).thenReturn(mockProductList);

        // When: Call the service method to get all products
        List<ProductData> actualProductDataList = productService.getAllProduct();

        // Then: Validate that the list size is correct and that the mapping works
        assertEquals(2, actualProductDataList.size());


    }

    /**
     * mock the product data.
     */
    public List<Product> getProduct() {
        // Given: Create a list of mock products
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setPrice(100.0);
        product1.setQuantity(10);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setPrice(200.0);
        product2.setQuantity(5);
        return Arrays.asList(product1, product2);
    }
}
