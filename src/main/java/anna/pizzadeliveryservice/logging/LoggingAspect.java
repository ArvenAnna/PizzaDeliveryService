package anna.pizzadeliveryservice.logging;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Logging for Pizza Delivery Service
 * @author Anna
 */
@Aspect
@Component
public class LoggingAspect {

    private Logger logger;

    @PostConstruct
    public void addAppender() {
        logger = Logger.getRootLogger();
    }

    @Around("within(anna.pizzadeliveryservice.service.*) || within(anna.pizzadeliveryservice.controller.*)"
            + " || within(anna.pizzadeliveryservice.repository.*)")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            before();
            logger.info("Object of type:");
            logger.info(joinPoint.getTarget().getClass().getName());
            logger.info("Method named:");
            logger.info(joinPoint.getSignature().getName());
            logger.info("Args are:");
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                logger.info("arg " + (i + 1));
                logger.info(args[i]);
            }
            Object retVal = joinPoint.proceed();
            after();
            logger.info("Returns value:");
            logger.info(retVal);
            return retVal;

        } catch (Throwable throwable) {
            afterThrowing();
            logger.error("Exception occurs");
            logger.error(throwable.getMessage());
            throwable.printStackTrace();
            return null;
        }
    }

    public void before() {
        logger.info("Before method......");
    }

    public void after() {
        logger.info("After method......");
    }

    public void afterThrowing() {
        logger.info("After throwing......");
    }

}
