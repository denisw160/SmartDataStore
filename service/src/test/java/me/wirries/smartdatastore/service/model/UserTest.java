package me.wirries.smartdatastore.service.model;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testcase for {@link User}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setUserId("test");
        user.setCreated(new Date());
    }

    @Test
    public void updatePassword() {
        assertNull(user.getPassword());
        user.setPassword("test");
        assertNotNull(user.getPassword());
        assertEquals("test", user.getPassword());
        assertNull(user.getUpdated());

        user.updatePassword("test");
        assertNotNull(user.getPassword());
        assertNotEquals("test", user.getPassword());
        assertNotNull(user.getUpdated());
    }

    @Test
    public void validatePassword() {
        updatePassword();
        assertTrue(user.validatePassword("test"));
        assertFalse(user.validatePassword("Test"));
    }

    @Test
    public void updateRoles() throws Exception {
        assertNull(user.getRoles());
        user.setRoles("plain");
        assertNotNull(user.getRoles());
        assertEquals("plain", user.getRoles());
        assertNull(user.getUpdated());

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("role1"));
        roleList.add(new Role("role2"));

        user.updateRoles(roleList);
        assertNotNull(user.getRoles());
        assertEquals("[{\"name\":\"role1\"},{\"name\":\"role2\"}]", user.getRoles());
        assertNotNull(user.getUpdated());
    }

    @Test
    public void readRoles() throws Exception {
        assertNull(user.readRoles());

        updateRoles();
        List<Role> roleList = user.readRoles();
        assertNotNull(roleList);
        assertEquals(2, roleList.size());
        assertEquals("role1", roleList.get(0).getName());
        assertEquals("role2", roleList.get(1).getName());
    }

    @Test
    public void updatePermissions() throws Exception {
        assertNull(user.getPermissions());
        user.setPermissions("plain");
        assertNotNull(user.getPermissions());
        assertEquals("plain", user.getPermissions());
        assertNull(user.getUpdated());

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(new Permission("messageId1", PermissionType.READ));
        permissionList.add(new Permission("messageId2", PermissionType.WRITE));
        permissionList.add(new Permission("messageId3", PermissionType.READWRITE));

        user.updatePermission(permissionList);
        assertNotNull(user.getPermissions());
        assertEquals("[{\"messageId\":\"messageId1\",\"type\":\"READ\"},{\"messageId\":\"messageId2\",\"type\":\"WRITE\"},{\"messageId\":\"messageId3\",\"type\":\"READWRITE\"}]", user.getPermissions());
        assertNotNull(user.getUpdated());
    }

    @Test
    public void readPermissions() throws Exception {
        assertNull(user.readPermissions());

        updatePermissions();
        List<Permission> permissionList = user.readPermissions();
        assertNotNull(permissionList);
        assertEquals(3, permissionList.size());
        assertEquals("messageId1", permissionList.get(0).getMessageId());
        assertEquals(PermissionType.READ, permissionList.get(0).getType());
        assertEquals("messageId2", permissionList.get(1).getMessageId());
        assertEquals(PermissionType.WRITE, permissionList.get(1).getType());
        assertEquals("messageId3", permissionList.get(2).getMessageId());
        assertEquals(PermissionType.READWRITE, permissionList.get(2).getType());
    }

}
