package me.wirries.smartdatastore.service.utils;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * A helper class for the {@link org.springframework.security.core.GrantedAuthority}s.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public final class AuthorityHelper {

    private AuthorityHelper() {
        // only static methods
    }

    /**
     * Check if role is in the authorities.
     *
     * @param role        Role
     * @param authorities List of {@link GrantedAuthority}s
     * @return TRUE if the role is in the list
     */
    public static boolean hasRole(String role, Collection<? extends GrantedAuthority> authorities) {
        if (role == null || authorities == null) return false;

        for (GrantedAuthority a : authorities) {
            if (role.equals(a.getAuthority())) return true;
        }
        return false;
    }

}
