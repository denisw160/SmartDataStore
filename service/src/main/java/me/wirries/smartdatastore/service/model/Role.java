package me.wirries.smartdatastore.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;

/**
 * This is a role of a {@link User}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public class Role implements GrantedAuthority {

    private String name;

    /**
     * Default constructor.
     */
    public Role() {
    }

    /**
     * Constructor with the role name.
     *
     * @param name name of the role
     */
    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return new EqualsBuilder()
                .append(name, role.name)
                .isEquals();
    }

    /**
     * Creates a new role with the given name.
     *
     * @param name name of the role
     * @return a {@link Role}
     */
    public static Role createRole(String name) {
        return new Role(name);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }

}
