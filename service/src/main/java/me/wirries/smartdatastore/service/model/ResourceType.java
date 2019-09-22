package me.wirries.smartdatastore.service.model;

/**
 * This enum has the resources of a {@link Permission}.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public enum ResourceType {

    /**
     * Access via WEB.
     */
    MESSAGE_ID,

    /**
     * Access via MQTT.
     */
    MQTT_TOPIC

}
