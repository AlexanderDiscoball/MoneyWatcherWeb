package alex.disco.ball.service;

import alex.disco.ball.dao.common.ProductRepo;
import alex.disco.ball.entity.Category;
import alex.disco.ball.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockProductRepoTestUnit5 {

    private static final String prodName = "Пончик";
    private static final long prodId = 1L;
    private static final long wrongId = 999L;
    private static final Product product = new Product(prodId, "Пончик", Category.FOOD, 44, LocalDateTime.now().toLocalDate());

    @Mock
    private ProductRepo mockRepo;

    @BeforeEach
    public void setUp(){
        assertNotNull(mockRepo);
    }

    @Test
    public void findByIdPositive(){
        when(mockRepo.findById(prodId)).thenReturn(Optional.of(product));
        Product product = mockRepo.findById(prodId).orElse(null);
        assertNotNull(product);
        assertEquals(product.getName(), prodName);
    }

    @Test
    public void findByIdNegative(){
        when(mockRepo.findById(wrongId)).thenReturn(Optional.empty());
        Optional<Product> product = mockRepo.findById(wrongId);
        assertTrue(product.isEmpty());
    }

    @Test
    public void save(){
        when(mockRepo.save(product)).thenReturn(true);
        mockRepo.save(product);
        verify(mockRepo).save(product);
    }

    @Test
    public void delete(){
        when(mockRepo.delete(product)).thenReturn(true);
        boolean delete = mockRepo.delete(product);
        assertTrue(delete);
        verify(mockRepo).delete(product);
    }

    @Test
    public void findAll(){
        when(mockRepo.findAll()).thenReturn(Set.of(product));
        Set<Product> products = mockRepo.findAll();
        verify(mockRepo).findAll();
        assertNotNull(products);
    }

    @Test
    public void update(){
        when(mockRepo.update(product)).thenReturn(true);
        assertTrue(mockRepo.update(product));
    }

}
