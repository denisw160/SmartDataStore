package me.wirries.smartdatastore.service.service;

import me.wirries.smartdatastore.service.config.MqttBrokerConfiguration;
import me.wirries.smartdatastore.service.mqttbroker.MoquetteServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * This is the service for controlling the embedded mqtt service.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
@Service
public class MqttBrokerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttBrokerService.class);

    private MqttBrokerConfiguration config;

    private MoquetteServer server;

    /**
     * Create a new {@link MoquetteServer} with the given
     * configuration.
     *
     * @param config Configuration for the MQTT broker
     */
    @Autowired
    public MqttBrokerService(MqttBrokerConfiguration config) {
        this.config = config;
        server = new MoquetteServer(config.getBind(), config.getPort(), config.getWebsocketPort());
    }

    @PostConstruct
    private void start() {
        if (config.isEnabled()) {
            try {
                server.start();
                LOGGER.info("Embedded MQTT broker on port {} successful started", config.getPort());

            } catch (IOException e) {
                LOGGER.error("Failed to start embedded MQTT broker", e);
            }
        }
    }

    @PreDestroy
    private void stop() {
        if (server.isStarted()) {
            server.stop();
            LOGGER.info("Embedded MQTT broker successful stopped");
        }
    }

}
