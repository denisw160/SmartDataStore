package me.wirries.smartdatastore.service.model;

/**
 * This enum has the types of a {@link Message} and {@link MessageId}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public enum MessageType {

    /**
     * Message is a XML document.
     */
    XML,

    /**
     * Message is a JSON document.
     */
    JSON,

    /**
     * Message has an unknown format.
     */
    UNKNOWN

}
