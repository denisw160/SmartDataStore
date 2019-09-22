package me.wirries.smartdatastore.service.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * This is the model for a {@link MessageId}. The messageId is used in the {@link Permission}
 * for controlling the access to the data.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
@Document(collection = "messageId")
public class MessageId {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String description;

    private MessageType defaultType;

    private Date created;
    private Date updated;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MessageType getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(MessageType defaultType) {
        this.defaultType = defaultType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageId user = (MessageId) o;
        return new EqualsBuilder()
                .append(id, user.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("description", description)
                .append("defaultType", defaultType)
                .append("created", created)
                .append("updated", updated)
                .toString();
    }

}
