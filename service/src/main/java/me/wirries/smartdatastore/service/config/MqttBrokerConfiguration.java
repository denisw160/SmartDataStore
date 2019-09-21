package me.wirries.smartdatastore.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration for the embedded mqtt broker.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
@Configuration
@ConfigurationProperties(prefix = "app.mqtt-broker")
public class MqttBrokerConfiguration {

    private boolean enabled;
    private String bind;
    private int port;
    private int websocketPort;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getWebsocketPort() {
        return websocketPort;
    }

    public void setWebsocketPort(int websocketPort) {
        this.websocketPort = websocketPort;
    }

}
