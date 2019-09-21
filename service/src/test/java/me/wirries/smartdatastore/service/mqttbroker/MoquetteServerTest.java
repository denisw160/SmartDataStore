package me.wirries.smartdatastore.service.mqttbroker;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import static org.junit.Assert.*;

/**
 * Testcase for {@link MoquetteServer}.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class MoquetteServerTest extends AbstractRepositoryTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoquetteServerTest.class);

    private static final String BIND = "127.0.0.1";
    private static final int SERVER_PORT = 1882;
    private static final int WEBSOCKET_PORT = 8082;

    private MoquetteServer server;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        server = new MoquetteServer(BIND, SERVER_PORT, WEBSOCKET_PORT);
    }

    @After
    public void tearDown() {
        try {
            server.stop();
        } catch (Exception e) {
            // ignore exceptions
        }
    }

    @Test
    public void start() throws Exception {
        assertTrue(available(SERVER_PORT));
        assertTrue(available(WEBSOCKET_PORT));

        server.start();

        String broker = "tcp://" + BIND + ":" + SERVER_PORT;
        String content = "ping";
        String topic = "test";
        String user = "admin";
        String password = "password";

        MqttClient client = connect(broker, user, password);

        LOGGER.info("Subscribe to topic: {}", topic);
        client.subscribe(topic, (topic1, message) -> {
            LOGGER.info("Received message {} for topic {}", message, topic1);
            assertEquals(topic1, topic1);
            assertEquals(content, message.toString());
        });

        LOGGER.info("Publishing message: {}", content);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(2);
        client.publish(topic, message);
        LOGGER.info("Message published");

        client.disconnect();
        LOGGER.info("Disconnected");

        assertFalse(available(SERVER_PORT));
        assertFalse(available(WEBSOCKET_PORT));
    }

    @Test
    public void stop() throws Exception {
        start();
        server.stop();

        assertTrue(available(SERVER_PORT));
        assertTrue(available(WEBSOCKET_PORT));
    }

    @Test(expected = IllegalStateException.class)
    public void stopWithoutStart() {
        server.stop();
        fail("IllegalStateException is not thrown");
    }

    @Test
    public void isStarted() throws Exception {
        assertFalse(server.isStarted());
        server.start();
        assertTrue(server.isStarted());
        server.stop();
        assertFalse(server.isStarted());
    }

    @Test(expected = MqttException.class)
    public void invalidLogin() throws Exception {
        server.start();

        String broker = "tcp://" + BIND + ":" + SERVER_PORT;
        String user = "admin";
        String password = "invalid";

        MqttClient client = connect(broker, user, password);
        fail("Connection should failed for this client: " + client);
    }

    /**
     * Create the mqtt client and connect.
     */
    private MqttClient connect(String broker, String user, String password) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient client = new MqttClient(broker, "test", persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(user);
        connOpts.setPassword(password.toCharArray());
        LOGGER.info("Connecting to broker: {}", broker);

        client.connect(connOpts);
        LOGGER.info("Connected");
        return client;
    }

    /**
     * Checks to see if a specific port is available.
     */
    public static boolean available(int port) {
        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            // ignore exception
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }

}
