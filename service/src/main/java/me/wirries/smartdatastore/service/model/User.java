package me.wirries.smartdatastore.service.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * This is the model for a User with roles and permissions.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
@Document(collection = "user")
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;
    private String password;
    private UserType type;

    private String roles;
    private String permissions;

    private Date created;
    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
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

    /**
     * Updates the user object with the given password.
     *
     * @param pwd Password in plaintext
     */
    @Transient
    public void updatePassword(String pwd) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        setPassword(encoder.encode(pwd));
        setUpdated(new Date());
    }

    /**
     * Validates the password. If the password matched, TRUE is return.
     *
     * @param pwd Password in plaintext
     * @return TRUE if password matched
     */
    @Transient
    public boolean validatePassword(String pwd) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(pwd, getPassword());
    }

    /**
     * Update the roles with the given role list.
     *
     * @param roleList list of {@link Role}s
     * @throws Exception during transforming in JSON
     */
    @Transient
    public void updateRoles(List<Role> roleList) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        setRoles(mapper.writeValueAsString(roleList));
        setUpdated(new Date());
    }

    /**
     * Read the roles from JSON and create a list of {@link Role}s.
     *
     * @return list of {@link Role}s
     * @throws Exception during transforming from JSON
     */
    @Transient
    public List<Role> readRoles() throws Exception {
        if (StringUtils.isBlank(getRoles())) return null;
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(getRoles(), new TypeReference<List<Role>>() {
        });
    }

    /**
     * Update the permission with the given permission list.
     *
     * @param permissionList list of {@link Permission}s
     * @throws Exception during transforming in JSON
     */
    @Transient
    public void updatePermission(List<Permission> permissionList) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        setPermissions(mapper.writeValueAsString(permissionList));
        setUpdated(new Date());
    }

    /**
     * Read the permissions from JSON and create a list of {@link Permission}s.
     *
     * @return list of {@link Permission}s
     * @throws Exception during transforming from JSON
     */
    @Transient
    public List<Permission> readPermissions() throws Exception {
        if (StringUtils.isBlank(getPermissions())) return null;
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(getPermissions(), new TypeReference<List<Permission>>() {
        });
    }

    @Transient
    @Override
    public String getUsername() {
        return getUserId();
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            return readRoles();
        } catch (Exception e) {
            return null;
        }
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
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
                .append("userId", userId)
                .append("type", type)
                .append("roles", roles)
                .append("permissions", permissions)
                .append("created", created)
                .append("updated", updated)
                .toString();
    }

}
