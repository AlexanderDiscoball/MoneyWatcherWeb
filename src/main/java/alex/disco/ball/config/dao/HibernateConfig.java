package alex.disco.ball.config.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("alex.disco.ball.dao.hibernate")
@PropertySource({"classpath:postgres.properties","classpath:hibernate.properties"})
@EnableTransactionManagement
public class HibernateConfig {

    @Value("${db.login}")
    private String login;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;
    @Value("${db.driver}")
    private String driverClassName;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl}")
    private String hbm2ddl;
    @Value("${hibernate.connection_pool_size}")
    private int connectionPoolSize;
    @Value("${hibernate.format_sql}")
    private boolean formatSql;
    @Value("${hibernate.use_sql_comments}")
    private boolean useSqlComments;
    @Value("${hibernate.show_sql}")
    private boolean showSql;

    @Bean
    public SessionFactory sessionFactory(){
        return new LocalSessionFactoryBuilder(dataSourceHikari())
                .scanPackages("alex.disco.ball.entity")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }


    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", dialect);
        hibernateProp.put("hibernate.hbm2ddl.auto", hbm2ddl);
        hibernateProp.put("hibernate.format_sql", formatSql);
        hibernateProp.put("hibernate.use_sql_comments", useSqlComments);
        hibernateProp.put("hibernate.show_sql", showSql);
        return hibernateProp;
    }


    @Bean
    @Autowired
    public ResourceTransactionManager transactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }


    @Bean
    public DataSource dataSourceHikari() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(driverClassName);
            hikariConfig.setJdbcUrl(url);
            hikariConfig.setUsername(login);
            hikariConfig.setPassword(password);
            hikariConfig.setMaximumPoolSize(connectionPoolSize);
            hikariConfig.setConnectionTestQuery("SELECT 1");
            hikariConfig.setPoolName("hamsterPool");
            return new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            return null;
        }
    }
}
