package com.pos.streamline.service;

import com.pos.streamline.data.ProductData;
import com.pos.streamline.entity.Product;
import com.pos.streamline.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest_deleteProductById {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testDeleteProductById_Success() {
        // Given: Mock product to be deleted
        Product product = getProduct();

        // Mock repository behavior
        when(productRepository.getProductById(1L)).thenReturn(product);
        doNothing().when(productRepository).delete(product);

        // When: Call the delete method
        ProductData actualProductData = productService.deleteProductById(1L);

        // Then: Validate that the product was deleted and mapped correctly
        assertEquals(product.getId(), actualProductData.getId());
        assertEquals(product.getName(), actualProductData.getName());
        assertEquals(product.getDescription(), actualProductData.getDescription());
        assertEquals(product.getPrice(), actualProductData.getPrice());
        assertEquals(product.getQuantity(), actualProductData.getQuantity());

        // Verify that the repository delete method was called
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testDeleteProductById_NotFound() {
        // Given: Repository returns null for nonexistent product
        when(productRepository.getProductById(999L)).thenReturn(null);

        // When & Then: Expect EntityNotFoundException
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> productService.deleteProductById(999L)
        );

        assertEquals("Product not found with ID: 999", exception.getMessage());

        // Verify that the repository delete method was not called
        verify(productRepository, never()).delete(any(Product.class));
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
