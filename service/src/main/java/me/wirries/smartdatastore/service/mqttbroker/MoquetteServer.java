package me.wirries.smartdatastore.service.mqttbroker;

import io.moquette.BrokerConstants;
import io.moquette.server.Server;
import io.moquette.server.config.MemoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * This server is the wrapper around the Moquette MQTT broker.
 * More about the broker under http://moquette-io.github.io/moquette/.
 * <p>
 * Idea for the integration from https://github.com/renarj/moquette-spring-docker.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class MoquetteServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoquetteServer.class);

    private String bind;
    private int serverPort;
    private int websocketPort;

    private Server server;

    /**
     * Creates a new instance for the Moquette MQTT broker. The given parameter
     * are used to the configuration of the broker. The broker is secured by
     * the Spring {@link me.wirries.smartdatastore.service.service.UserService}.
     *
     * @param bind          IP-address for bind the service
     * @param serverPort    Port for the server
     * @param websocketPort Port for websocket connections
     */
    public MoquetteServer(String bind, int serverPort, int websocketPort) {
        this.bind = bind;
        this.serverPort = serverPort;
        this.websocketPort = websocketPort;
    }

    /**
     * Create a new instance of the Moquette MQTT broker and start the server.
     *
     * @throws IOException Exception during the start
     */
    public void start() throws IOException {
        LOGGER.info("Starting MQTT for IP-address {} and port {} with websocket port {}",
                bind, serverPort, websocketPort);

        MemoryConfig config = new MemoryConfig(new Properties());
        config.setProperty("host", bind);
        config.setProperty("port", Integer.toString(serverPort));
        config.setProperty("websocket_port", Integer.toString(websocketPort));
        config.setProperty(BrokerConstants.ALLOW_ANONYMOUS_PROPERTY_NAME, "true");
        config.setProperty("authenticator_class", SpringAuthenticationWrapper.class.getName());
        config.setProperty("authorizator_class", SpringAuthorizationWrapper.class.getName());

        server = new Server();
        server.startServer(config);

        LOGGER.info("Moquette MQTT broker started successfully");
    }

    /**
     * Stops the broker and cleanup resources.
     */
    public void stop() {
        if (server == null) {
            throw new IllegalStateException("Moquette MQTT broker is not started");
        }

        try {
            server.stopServer();
            LOGGER.info("Moquette MQTT broker stopped successfully");
        } finally {
            server = null;
        }
    }

    /**
     * Return the state of the MQTT broker.
     *
     * @return TRUE, if the server is started.
     */
    public boolean isStarted() {
        return server != null;
    }

}
