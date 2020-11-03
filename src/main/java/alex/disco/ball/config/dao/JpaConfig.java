package alex.disco.ball.config.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("alex.disco.ball.service.jpa.impl")
@PropertySource({"classpath:postgres.properties","classpath:hibernate.properties"})
@EnableJpaRepositories(basePackages = {"alex.disco.ball.dao.jpa"})
@EnableTransactionManagement
public class JpaConfig {

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
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSourceHikari());
        managerFactoryBean.setPackagesToScan("alex.disco.ball.entity");
        managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        managerFactoryBean.setJpaProperties(hibernateProperties());
        managerFactoryBean.afterPropertiesSet();
        return managerFactoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
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
    public ResourceTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
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
