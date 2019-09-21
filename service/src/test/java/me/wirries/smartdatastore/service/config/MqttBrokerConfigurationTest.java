package me.wirries.smartdatastore.service.config;

import me.wirries.smartdatastore.service.AbstractApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testcase for {@link MqttBrokerConfiguration}.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class MqttBrokerConfigurationTest extends AbstractApplicationTests {

    @Autowired
    private MqttBrokerConfiguration config;

    @Test
    public void isEnabled() {
        assertTrue(config.isEnabled());
    }

    @Test
    public void getBind() {
        assertEquals("0.0.0.0", config.getBind());
    }

    @Test
    public void getPort() {
        assertEquals(1884, config.getPort());
    }

    @Test
    public void getWebsocketPort() {
        assertEquals(8084, config.getWebsocketPort());
    }

}
