package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import me.wirries.smartdatastore.service.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Testcase for {@link UserRepository}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public class UserRepositoryTest extends AbstractRepositoryTests {

    @Autowired
    private UserRepository repository;

    private User user;

    @Test
    public void create() throws Exception {
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserId("test");
        user.updatePassword("password");
        user.setType(UserType.USER);
        user.setCreated(new Date());

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("role1"));
        roleList.add(new Role("role2"));
        user.updateRoles(roleList);

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(Permission.createMessageIdPermission("messageId1", PermissionType.READ));
        permissionList.add(Permission.createMessageIdPermission("messageId2", PermissionType.WRITE));
        permissionList.add(Permission.createMessageIdPermission("messageId3", PermissionType.READWRITE));
        user.updatePermission(permissionList);

        repository.save(user);
    }

    @Test
    public void load() throws Exception {
        create();

        Optional<User> userById = repository.findById(user.getId());
        assertTrue(userById.isPresent());
        User loadedUser = userById.get();
        assertNotNull(loadedUser);
        assertEquals("test", loadedUser.getUserId());
        assertTrue(loadedUser.validatePassword("password"));
        assertEquals(UserType.USER, loadedUser.getType());
        assertNotNull(loadedUser.getCreated());
        assertEquals(user.getCreated(), loadedUser.getCreated());
        assertNotNull(loadedUser.getUpdated());
        assertEquals(user.getUpdated(), loadedUser.getUpdated());

        assertEquals(2, loadedUser.readRoles().size());
        assertEquals(3, loadedUser.readPermissions().size());
    }

    @Test
    public void findByUserId() throws Exception {
        create();

        User test = repository.findByUserId("test");
        assertNotNull(test);
        assertEquals("test", test.getUserId());

        User testUpper = repository.findByUserId("TEST");
        assertNotNull(testUpper);
        assertEquals("test", testUpper.getUserId());

        User unknown = repository.findByUserId("unknown");
        assertNull(unknown);
    }

}
