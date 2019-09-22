package me.wirries.smartdatastore.service.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testcase for {@link AuthorityHelper}.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class AuthorityHelperTest {

    private Collection<GrantedAuthority> authorities;

    @Before
    public void setUp() {
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ROLE1"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ROLE2"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ROLE3"));
    }

    @Test
    public void hasRole() {
        assertTrue(AuthorityHelper.hasRole(authorities, "ROLE_ROLE1"));
        assertFalse(AuthorityHelper.hasRole(authorities, "ROLE_UNKNOWN"));
    }

    @Test
    public void hasPermission() {
        // TODO write test
    }

    @Test
    public void findPermission() {
        // TODO write test
    }

}
