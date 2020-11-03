package alex.disco.ball.logger;

import alex.disco.ball.entity.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {
    private static final Logger logger = LoggerFactory.getLogger(AspectLogger.class);

    @AfterReturning(value = "execution(* alex.disco.ball.dao.*.*Repo+.findBy(..))", returning = "product")
    public void afterFindById(JoinPoint joinPoint, Object product){
        logger.info("--> Были найдены такие объекты "+product);
    }


    @Before("execution(* alex.disco.ball.dao.*.*Repo+.save*(..)) && args(product)")
    public void beforeSave(Product product){
        logger.info("--> Был сохранен такой продукт "+product);
    }
}
