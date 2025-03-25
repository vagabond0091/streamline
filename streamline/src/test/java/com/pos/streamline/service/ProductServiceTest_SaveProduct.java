package com.pos.streamline.service;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;
import com.pos.streamline.mapper.ProductMapper;
import com.pos.streamline.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest_SaveProduct {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    /**
     * Test case for saving the product.
     */
    @Test
    public void testSaveProduct() {
        // Given: Mock input DTO and expected entity
        ProductData inputDTO = getProductData();
        Product productEntity = ProductMapper.toEntity(inputDTO);

        Product savedEntity = getProductEntity();
        ProductData expectedDTO = ProductMapper.toDTO(savedEntity);

        // Mock repository save behavior
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(savedEntity);

        // When: Service method is called
        ProductData actualData = productService.saveProduct(inputDTO);

        // Then: Validate the results
        assertEquals(expectedDTO.getId(), actualData.getId());
        assertEquals(expectedDTO.getTitle(), actualData.getTitle());
        assertEquals(expectedDTO.getDescription(), actualData.getDescription());
        assertEquals(expectedDTO.getPrice(), actualData.getPrice());
        assertEquals(expectedDTO.getQuantity(), actualData.getQuantity());
    }

    /**
     * set up the given product entity
     * @return
     */
    public Product getProductEntity() {
        Product productEntity = new Product();
        productEntity.setId(1L);
        productEntity.setTitle("Test Product");
        productEntity.setDescription("Description");
        productEntity.setPrice(100);
        productEntity.setQuantity(10);
        return productEntity;
    }

    /**
     * set up for the given product dto.
     * @return
     */
    public ProductData getProductData() {
        return new ProductData(0L, "Test Product", "Description", 100, 10,"asd","asd","asd","asd");

    }
}