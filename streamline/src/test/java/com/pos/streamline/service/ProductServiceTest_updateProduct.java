package com.pos.streamline.service;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;
import com.pos.streamline.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest_updateProduct {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testUpdateProduct_Success() {
        // Given: Mock existing product
        Product existingProduct =  getProduct();

        // Mock input data for update
        ProductData updatedData = new ProductData(1L, "Updated Product Name", "Updated Description", 150.0, 20,"asd","asd","asd","asd");

        // Mock repository behavior
        when(productRepository.getProductById(Mockito.anyLong())).thenReturn(existingProduct);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(existingProduct);

        // When: Call the update method
        ProductData actualProductData = productService.updateProduct(updatedData);

        // Then: Validate the product details were updated
        assertEquals(updatedData.getId(), actualProductData.getId());
        assertEquals(updatedData.getTitle(), actualProductData.getTitle());
        assertEquals(updatedData.getDescription(), actualProductData.getDescription());
        assertEquals(updatedData.getPrice(), actualProductData.getPrice());
        assertEquals(updatedData.getQuantity(), actualProductData.getQuantity());

        // Verify repository interactions
        verify(productRepository, times(1)).getProductById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testUpdateProduct_NotFound() {
        // Given: Repository returns null for nonexistent product
        ProductData updatedData = new ProductData(999L, "Updated Product Name", "Updated Description", 150.0, 20,"asd","asd","asd","asd");
        when(productRepository.getProductById(999L)).thenReturn(null);

        // When & Then: Expect EntityNotFoundException
        jakarta.persistence.EntityNotFoundException exception = assertThrows(
                jakarta.persistence.EntityNotFoundException.class,
                () -> productService.updateProduct(updatedData)
        );

        // Assert the exception message
        assertEquals("Product not found with ID: 999", exception.getMessage());

        // Verify repository interaction
        verify(productRepository, times(1)).getProductById(999L);
    }




    /**
     * mock the product data.
     */
    public Product getProduct() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setTitle("Old Product Name");
        existingProduct.setDescription("Old Description");
        existingProduct.setPrice(100.0);
        existingProduct.setQuantity(10);

        return existingProduct;
    }
    /**
     * Mock the product data
     */
    public ProductData getProductData() {
        return new ProductData(999L, "Updated Product Name", "Updated Description", 150.0, 20,"asd","asd","asd","asd");
    }
}
