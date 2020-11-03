package alex.disco.ball.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"alex.disco.ball"})
@PropertySource("classpath:postgres.properties")
public class AppConfig {

    @Value("${login}")
    String login;

    @Value("${password}")
    String password;

    @Value("${url}")
    String url;

}
