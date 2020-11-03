package alex.disco.ball.dao.jdbc.integration;

import alex.disco.ball.config.ReposConfig;
import alex.disco.ball.config.dao.JdbcConfig;
import alex.disco.ball.dao.common.ProductRepo;
import alex.disco.ball.entity.Category;
import alex.disco.ball.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringJUnitConfig(classes = {ReposConfig.class, JdbcConfig.class})
@Sql(value = "classpath:db_script/test-data.sql",
     config = @SqlConfig(encoding = "utf-8", separator = ";"))
@Sql(value = "classpath:db_script/clean-tables.sql",
     config = @SqlConfig(encoding = "utf-8", separator = ";"),
     executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class JdbcProductRepoTest {

    @Autowired
    private ProductRepo jdbcProductRepo;

    private static final long productId = 1L;
    private static final long wrongId = 999L;

    @Test
    public void count(){
        assertEquals(jdbcProductRepo.count(),2);
    }

    @Test
    public void findByIdPositive(){
        assertNotNull(jdbcProductRepo.findById(productId));
    }

    @Test
    public void findByIdNegative(){
        assertTrue(jdbcProductRepo.findById(wrongId).isEmpty());
    }

    @Test
    public void findAll(){
        assertFalse(jdbcProductRepo.findAll().isEmpty());
    }

    @Test
    public void savePosistive(){
        Product product = new Product(3L, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate());
        jdbcProductRepo.save(product);
        assertEquals(3, jdbcProductRepo.count());
    }

    @Test
    public void saveNegative(){
        Product product = new Product(productId, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate());
        assertThrows(DuplicateKeyException.class,
                ()->jdbcProductRepo.save(product));
    }

    @Test
    public void updatePositive(){
        Product product = new Product(productId, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate());
        assertTrue(jdbcProductRepo.update(product));
        assertEquals(product, jdbcProductRepo.findById(product.getId()).get());
    }

    @Test
    public void updateNegative(){
        Product product = new Product(wrongId, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate());
        assertFalse(jdbcProductRepo.update(product));
    }


    @Test
    public void deletePositive(){
        Product product = new Product(productId, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate());
        assertTrue(jdbcProductRepo.delete(product));
        assertEquals(1, jdbcProductRepo.count());
    }
    @Test
    public void deleteNegative(){
        Product product = new Product(wrongId, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate());
        assertFalse(jdbcProductRepo.delete(product));
        assertEquals(2, jdbcProductRepo.count());
    }

    @Test
    public void deleteByIdPositive(){
        assertTrue(jdbcProductRepo.deleteById(productId));
        assertEquals(1, jdbcProductRepo.count());


    }@Test
    public void deleteByIdNegative(){
        assertFalse(jdbcProductRepo.deleteById(wrongId));
        assertEquals(2, jdbcProductRepo.count());
    }

}
