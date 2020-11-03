package alex.disco.ball.config.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@PropertySource("classpath:postgres.properties")
@ComponentScan("alex.disco.ball.dao.jdbc")
public class JdbcConfig {

    @Value("${db.login}")
    private String login;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String url;

    @Value("${db.driver}")
    private String driverClassName;

    @Bean
    @Autowired
    private NamedParameterJdbcTemplate jdbcNamedTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    private DataSource dataSource(){
        DriverManagerDataSource driver = new DriverManagerDataSource(url, login, password);
        driver.setDriverClassName(driverClassName);
        return driver;
    }

    @Bean
    @Autowired
    private TransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
