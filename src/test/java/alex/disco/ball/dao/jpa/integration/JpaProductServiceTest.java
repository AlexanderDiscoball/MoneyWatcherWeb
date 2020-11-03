package alex.disco.ball.dao.jpa.integration;

import alex.disco.ball.config.dao.JpaConfig;

import alex.disco.ball.entity.Category;
import alex.disco.ball.entity.Person;
import alex.disco.ball.entity.Product;
import alex.disco.ball.service.jpa.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = JpaConfig.class)
@Sql(value = "classpath:db_script/test-data.sql",
        config = @SqlConfig(encoding = "utf-8", separator = ";"))
@Sql(value = "classpath:db_script/clean-tables.sql",
        config = @SqlConfig(encoding = "utf-8", separator = ";"),
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class JpaProductServiceTest {

    private static final long productId = 1L;
    private static final long personId = 2L;
    private static final long wrongId = 999L;
    private static final Person person = new Person(personId,"irene.adler", "Irene", "Adler", "id123ds", new ArrayList<>());
    private static final Product productWrongId = new Product(wrongId, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate(), LocalDateTime.now().toLocalDate(), person);
    private static final Product productProdId = new Product(productId, "'Супчик'", Category.FOOD, 44,
            LocalDateTime.of(2020,9,7,0,0,0).toLocalDate(),
            LocalDateTime.of(2020,10,13,0,0,0).toLocalDate(),
            person);

    @Autowired
    ProductService productService;

    @Test
    public void findByIdPositive(){
        productService.findById(productId).ifPresentOrElse(
                p -> assertEquals("Супчик", p.getName()),
                () -> fail("Product not found!"));
    }

    @Test
    public void findByIdNegative(){
        assertTrue(productService.findById(wrongId).isEmpty());
    }

    @Test
    public void update(){
        Product product = productService.update(productProdId);
        assertNotNull(product);
        assertEquals(productProdId, product);
    }

    @Test
    public void updateName(){
        Product product = productService.updateName(productProdId,"Бутер");
        assertNotNull(product);
        assertEquals("Бутер", product.getName());
    }

    @Test
    public void save(){
        productService.save(productWrongId);
        assertEquals(3, productService.countProducts());
        assertNotNull(productService.findById(wrongId));
    }

    @Test
    public void deletePositive(){
        productService.delete(productProdId);
        assertEquals(1, productService.countProducts());
    }

    @Test
    public void deleteNegative(){
        productService.delete(productWrongId);
        assertEquals(2, productService.countProducts());
    }

    @Test
    public void deleteByIdPositive(){
        productService.deleteById(productId);
        assertEquals(1, productService.countProducts());
    }

    @Test
    public void deleteByIdNegative(){
        assertThrows(EmptyResultDataAccessException.class, () ->productService.deleteById(wrongId));
        assertEquals(2, productService.countProducts());
    }

    @Test
    public void findAll(){
        List<Product> productList = productService.findAll();
        assertNotNull(productList);
        assertEquals(2, productList.size());
    }

    @Test
    public void count(){
        assertEquals(2, productService.countProducts());
    }
}
