package alex.disco.ball.dao.hibernate.integration;

import alex.disco.ball.config.dao.HibernateConfig;
import alex.disco.ball.dao.common.PersonRepo;
import alex.disco.ball.dao.common.ProductRepo;
import alex.disco.ball.entity.Category;
import alex.disco.ball.entity.Person;
import alex.disco.ball.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = HibernateConfig.class)
@Sql(value = "classpath:db_script/test-data.sql",
        config = @SqlConfig(encoding = "utf-8", separator = ";"))
@Sql(value = "classpath:db_script/clean-tables.sql",
        config = @SqlConfig(encoding = "utf-8", separator = ";"),
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Transactional
public class HiberanteProductRepoTest {

    @Autowired
    @Qualifier("hibernateProductRepo")
    ProductRepo hibernateProductRepo;

    @Autowired
    @Qualifier("hibernatePersonRepo")
    PersonRepo hibernatePersonRepo;

    private static final long productId = 1L;
    private static final long personId = 2L;
    private static final String personName = "Sherlock";
    private static final long wrongId = 999L;

    @Test
    public void getByIdPositive(){
        Product product = hibernateProductRepo.findById(productId).get();
        assertNotNull(product);
        assertEquals(personName, product.getPerson().getFirstName());
    }

    @Test
    public void getByIdNegative(){
        assertTrue(hibernateProductRepo.findById(wrongId).isEmpty());
    }

    @Test
    public void count(){
        assertEquals(2,hibernateProductRepo.count());
    }

    @Test
    public void savePositive(){
        Person person = new Person(personId,"irene.adler", "Irene", "Adler", "id123ds", new ArrayList<>());
        Product product = new Product(3L, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate(), LocalDateTime.now().toLocalDate(), person);
        assertTrue(hibernateProductRepo.save(product));
        assertEquals(3, hibernateProductRepo.count());
        assertEquals(product.getId(), hibernateProductRepo.findById(product.getId()).get().getId());
    }

    @Test
    public void saveNegative(){
        System.out.println(hibernateProductRepo.findById(productId).get());
        Person person = new Person(productId,"irene.adler", "Irene", "Adler", "id123ds", new ArrayList<>());
        Product product = new Product(productId, "HAHHAHAH", Category.FOOD, 44, LocalDateTime.now().toLocalDate(), LocalDateTime.now().toLocalDate(), person);
        assertTrue(hibernateProductRepo.save(product));
        assertEquals(2, hibernateProductRepo.count());
    }

}
