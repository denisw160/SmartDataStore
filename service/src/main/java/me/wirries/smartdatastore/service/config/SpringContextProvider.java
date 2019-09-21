package me.wirries.smartdatastore.service.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * This provider return the current application context for non Spring classes.
 * The application context is provided with a static method.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
@Configuration
public class SpringContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

}
