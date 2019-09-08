package me.wirries.smartdatastore.service.service;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import me.wirries.smartdatastore.service.model.*;
import me.wirries.smartdatastore.service.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Testcase for {@link UserService}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public class UserServiceTest extends AbstractRepositoryTests {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService userService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        User user = new User();
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
        permissionList.add(new Permission("messageId1", PermissionType.READ));
        permissionList.add(new Permission("messageId2", PermissionType.WRITE));
        permissionList.add(new Permission("messageId3", PermissionType.READWRITE));
        user.updatePermission(permissionList);

        repository.save(user);
    }

    @Test
    public void loadUserByUsername() {
        UserDetails test = userService.loadUserByUsername("test");
        assertNotNull(test);
        assertEquals("test", test.getUsername());
        assertTrue(test instanceof User);

        assertNotNull(test.getAuthorities());
        assertEquals(2, test.getAuthorities().size());
        List<GrantedAuthority> authorities = new ArrayList<>(test.getAuthorities());
        assertEquals("role1", authorities.get(0).getAuthority());
        assertEquals("role2", authorities.get(1).getAuthority());
        assertTrue(authorities.get(0) instanceof Role);

        test = userService.loadUserByUsername("TEST");
        assertNotNull(test);
        assertEquals("test", test.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameUnknown() {
        userService.loadUserByUsername("unknown");
        fail("User should not exists");
    }

}
