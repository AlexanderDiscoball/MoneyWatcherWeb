package alex.disco.ball.dao.jpa.integration;

import alex.disco.ball.config.dao.JpaConfig;
import alex.disco.ball.entity.Person;
import alex.disco.ball.entity.Product;
import alex.disco.ball.service.jpa.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = JpaConfig.class)
@Sql(value = "classpath:db_script/test-data.sql",
        config = @SqlConfig(encoding = "utf-8", separator = ";"))
@Sql(value = "classpath:db_script/clean-tables.sql",
        config = @SqlConfig(encoding = "utf-8", separator = ";"),
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class JpaPersonServiceTest {

    private static final long personId = 2L;
    private static final long wrongId = 999L;
    private static final Person personInDb = new Person(personId,"irene.adler","id123ds",  "Irene", "Adler",  new ArrayList<>());
    private static final Person personNotInDb = new Person(wrongId,"heh.opa", "1234567", "Pashok", "Noobok", new ArrayList<>());

    @Autowired
    private PersonService personService;

    @Test
    public void findById(){
        personService.findById(personId).ifPresentOrElse(
                p -> assertEquals("Irene", p.getFirstName()),
                () -> fail("Person not found!"));
    }

    @Test
    public void findByIdNegative(){
        assertTrue(personService.findById(wrongId).isEmpty());
    }

    @Test
    public void update(){
        Person person = personService.update(personInDb);
        assertNotNull(person);
        assertEquals(personId, person.getId());
    }

    @Test
    public void updateName(){
        String firstName = "Дрон лох";
        Person person = personService.updateFirstName(personInDb, firstName);
        assertNotNull(person);
        assertEquals(firstName, person.getFirstName());
    }

    @Test
    public void save(){
        personService.save(personNotInDb);
        assertEquals(3, personService.countProducts());
        assertNotNull(personService.findById(wrongId));
    }

    @Test
    public void deletePositive(){
        personService.delete(personInDb);
        assertEquals(1, personService.countProducts());
    }

    @Test
    public void deleteNegative(){
        personService.delete(personNotInDb);
        assertEquals(2, personService.countProducts());
    }

    @Test
    public void deleteByIdPositive(){
        personService.deleteById(personId);
        assertEquals(1, personService.countProducts());
    }

    @Test
    public void deleteByIdNegative(){
        assertThrows(EmptyResultDataAccessException.class, () -> personService.deleteById(wrongId));
        assertEquals(2, personService.countProducts());
    }

    @Test
    public void findAll(){
        List<Person> productList = personService.findAll();
        assertNotNull(productList);
        assertEquals(2, productList.size());
    }

    @Test
    public void count(){
        assertEquals(2, personService.countProducts());
    }

}
