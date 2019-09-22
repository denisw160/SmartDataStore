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

    private String id;
    private ResourceType resource;
    private PermissionType permission;

    /**
     * Default constructor.
     */
    public Permission() {
    }

    /**
     * Constructor with field.
     *
     * @param id         id of the permission
     * @param resource   type of the resource (see {@link ResourceType})
     * @param permission type of the permission (see {@link PermissionType})
     */
    public Permission(String id, ResourceType resource, PermissionType permission) {
        this.id = id;
        this.resource = resource;
        this.permission = permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResourceType getResource() {
        return resource;
    }

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }

    public PermissionType getPermission() {
        return permission;
    }

    public void setPermission(PermissionType permission) {
        this.permission = permission;
    }

    /**
     * Is this a read permission?
     *
     * @return TRUE if read permitted
     */
    @JsonIgnore
    public boolean canRead() {
        return PermissionType.READ.equals(permission) || PermissionType.READWRITE.equals(permission);
    }

    /**
     * Is this a write permission?
     *
     * @return TRUE if write permitted
     */
    @JsonIgnore
    public boolean canWrite() {
        return PermissionType.WRITE.equals(permission) || PermissionType.READWRITE.equals(permission);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("resource", resource)
                .append("permission", permission)
                .toString();
    }

}
