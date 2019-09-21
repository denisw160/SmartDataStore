package me.wirries.smartdatastore.service.config;

import me.wirries.smartdatastore.service.AbstractApplicationTests;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Testcase for {@link SpringContextProvider}.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class SpringContextProviderTest extends AbstractApplicationTests {

    @Test
    public void getApplicationContext() {
        ApplicationContext applicationContext = SpringContextProvider.getApplicationContext();
        assertNotNull(applicationContext);
    }

}
