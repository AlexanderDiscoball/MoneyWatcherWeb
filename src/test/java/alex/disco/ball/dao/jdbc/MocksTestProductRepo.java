package alex.disco.ball.dao.jdbc;

import alex.disco.ball.dao.common.ProductRepo;
import alex.disco.ball.entity.Category;
import alex.disco.ball.entity.Product;
import alex.disco.ball.service.ProductService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MocksTestProductRepo {

    private static final Logger LOG = LoggerFactory.getLogger(MocksTestProductRepo.class);

    private ProductRepo mockRepo;
    private ProductService service;
    private Mockery mockery = new JUnit5Mockery();
    long prodId = 1L;
    Optional<Product> productOp = Optional.of(new Product(prodId,"Пончик", Category.FOOD, 44, LocalDateTime.now().toLocalDate()));

    @Test
    public void findByIdTestEasyMock(){

        mockRepo = createMock(ProductRepo.class);

        service = new ProductService();
        service.setRepo(mockRepo);

        final Product product = productOp.orElseThrow();
        expect(mockRepo.findById(prodId)).andReturn(productOp);
        replay(mockRepo);
        Product product1 = service.findById(prodId);
        verify(mockRepo);
        assertAll(()-> assertNotNull(product1),
                () -> assertEquals(product.getId(),product1.getId()),
                () -> assertEquals(product.getName(), product1.getName()));

    }

    @Test
    public void findByIdTestJMock(){
        mockRepo = mockery.mock(ProductRepo.class);

        service = new ProductService();
        service.setRepo(mockRepo);

        final Product product = productOp.orElseThrow();

        mockery.checking(new Expectations() {{
            allowing(mockRepo).findById(prodId);
            will(returnValue(productOp));
        }});

        Product result = service.findById(prodId);
        mockery.assertIsSatisfied();
        assertAll(()-> assertNotNull(result),
                () -> assertEquals(product.getId(),result.getId()),
                () -> assertEquals(product.getName(), result.getName()));
    }

    @Test
    public void findByIdTestMockito(){
        ProductRepo mockitoRepo = mock(ProductRepo.class);

        ProductService serviceMockito = new ProductService();
        serviceMockito.setRepo(mockitoRepo);

        final Product product = productOp.orElseThrow();

        when(mockitoRepo.findById(prodId)).thenReturn(productOp);

        Product result = serviceMockito.findById(prodId);
        mockery.assertIsSatisfied();
        assertAll(()-> assertNotNull(result),
                () -> assertEquals(product.getId(),result.getId()),
                () -> assertEquals(product.getName(), result.getName()));
    }

    @Test
    public void findByIdTestPowerMock(){
        mockRepo = mockery.mock(ProductRepo.class);

        service = new ProductService();
        service.setRepo(mockRepo);

        final Product product = productOp.orElseThrow();

        mockery.checking(new Expectations() {{
            allowing(mockRepo).findById(prodId);
            will(returnValue(productOp));
        }});

        Product result = service.findById(prodId);
        mockery.assertIsSatisfied();
        assertAll(()-> assertNotNull(result),
                () -> assertEquals(product.getId(),result.getId()),
                () -> assertEquals(product.getName(), result.getName()));
    }



}
