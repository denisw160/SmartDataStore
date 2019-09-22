package me.wirries.smartdatastore.service.controller;

import me.wirries.smartdatastore.service.AbstractApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

import static org.junit.Assert.*;

/**
 * Testcase for {@link LoginController}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
public class LoginControllerTest extends AbstractApplicationTests {

    @Autowired
    private LoginController controller;

    private Principal admin;

    @Before
    public void setUp() throws Exception {
        admin = () -> "admin";
    }

    @Test
    public void login() {
        LoginResponse response = controller.login(admin);
        assertTrue(response.isSuccess());
        assertEquals("admin", response.getUser());

        response = controller.login(null);
        assertFalse(response.isSuccess());
        assertEquals("notAuthenticated", response.getUser());
    }

}
