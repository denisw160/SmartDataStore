package me.wirries.smartdatastore.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This is a permission of an {@link User}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public class Permission {

    private String messageId;
    private String topic;
    private PermissionType type;
    private ResourceType resource;

    /**
     * Default constructor.
     */
    public Permission() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }

    public ResourceType getResource() {
        return resource;
    }

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }

    /**
     * Is this a read permission?
     *
     * @return TRUE if read permitted
     */
    @JsonIgnore
    public boolean isRead() {
        return PermissionType.READ.equals(type) || PermissionType.READWRITE.equals(type);
    }

    /**
     * Is this a write permission?
     *
     * @return TRUE if write permitted
     */
    @JsonIgnore
    public boolean isWrite() {
        return PermissionType.WRITE.equals(type) || PermissionType.READWRITE.equals(type);
    }

    /**
     * Creates a {@link Permission} for a messageId.
     *
     * @param messageId Id of the message
     * @param type      type of the permission
     * @return Permission for a messageId
     */
    public static Permission createMessageIdPermission(String messageId, PermissionType type) {
        Permission permission = new Permission();
        permission.setMessageId(messageId);
        permission.setType(type);
        permission.setResource(ResourceType.WEB);
        return permission;
    }

    /**
     * Creates a {@link Permission} for a MQTT topic.
     *
     * @param topic topic of the mqtt
     * @param type  type of the permission
     * @return Permission for a topic
     */
    public static Permission createMqttTopicPermission(String topic, PermissionType type) {
        Permission permission = new Permission();
        permission.setTopic(topic);
        permission.setType(type);
        permission.setResource(ResourceType.MQTT);
        return permission;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("messageId", messageId)
                .append("topic", topic)
                .append("type", type)
                .append("resource", resource)
                .toString();
    }

}
