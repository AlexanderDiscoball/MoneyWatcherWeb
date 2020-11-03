package alex.disco.ball.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("alex.disco.ball.dao.common")
@EnableAspectJAutoProxy
public class ReposConfig {


}
