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
    private PermissionType type;

    /**
     * Default constructor.
     */
    public Permission() {
    }

    /**
     * Constructor with the fields.
     *
     * @param messageId Id of the message
     * @param type      type of the permission
     */
    public Permission(String messageId, PermissionType type) {
        this.messageId = messageId;
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("messageId", messageId)
                .append("type", type)
                .toString();
    }

}
