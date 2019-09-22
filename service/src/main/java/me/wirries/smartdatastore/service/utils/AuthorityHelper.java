package me.wirries.smartdatastore.service.utils;

import me.wirries.smartdatastore.service.model.Permission;
import me.wirries.smartdatastore.service.model.PermissionType;
import me.wirries.smartdatastore.service.model.ResourceType;
import me.wirries.smartdatastore.service.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

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
     * @param authorities List of {@link GrantedAuthority}s
     * @param role        Role
     * @return TRUE if the role is in the list
     */
    public static boolean hasRole(Collection<? extends GrantedAuthority> authorities, String role) {
        if (role == null || authorities == null) return false;

        for (GrantedAuthority a : authorities) {
            if (role.equals(a.getAuthority())) return true;
        }
        return false;
    }

    /**
     * Checks if the user has the given permission.
     *
     * @param user           User
     * @param id             ID of the permission
     * @param resourceType   Type of resource (see {@link ResourceType}
     * @param permissionType Type of the permission (see {@link PermissionType}
     * @return TRUE, if the user has the permission
     * @throws Exception during reading the permissions
     */
    public static boolean hasPermission(User user,
                                        String id,
                                        ResourceType resourceType,
                                        PermissionType permissionType) throws Exception {

        if (user == null || id == null || resourceType == null || permissionType == null) return false;

        Permission p = findPermission(user, id, resourceType);
        if (p == null) return false;

        if (PermissionType.READ.equals(permissionType)) {
            return p.canRead();
        } else if (PermissionType.WRITE.equals(permissionType)) {
            return p.canWrite();
        } else {
            return p.canRead() && p.canWrite();
        }
    }

    /**
     * Find the matching permission for the given topic.
     *
     * @param user         User
     * @param id           ID of the permission
     * @param resourceType Type of resource (see {@link ResourceType}
     * @return Permission or NULL - if not found
     * @throws Exception during reading the permissions
     */
    public static Permission findPermission(User user, String id, ResourceType resourceType) throws Exception {
        if (user == null || id == null || resourceType == null) return null;

        List<Permission> permissionList = user.readPermissions();
        if (permissionList == null) return null;

        for (Permission p : permissionList) {
            if (resourceType.equals(p.getResource()) && StringUtils.equalsIgnoreCase(id, p.getId())) {
                return p;
            }
        }

        return null;
    }


}
