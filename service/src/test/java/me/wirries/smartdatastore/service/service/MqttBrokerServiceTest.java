package me.wirries.smartdatastore.service.service;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

/**
 * Testcase for {@link MqttBrokerService}.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class MqttBrokerServiceTest extends AbstractRepositoryTests {

    @Test
    public void testMqtt() throws Exception {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient client = new MqttClient("tcp://127.0.0.1:1884", "test", persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName("admin");
        connOpts.setPassword("password".toCharArray());
        client.connect(connOpts);
        client.disconnect();
    }

}
