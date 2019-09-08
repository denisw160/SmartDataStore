package me.wirries.smartdatastore.service.config;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import me.wirries.smartdatastore.service.model.User;
import me.wirries.smartdatastore.service.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link DevelopmentConfiguration}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public class DevelopmentConfigurationTest extends AbstractRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private DevelopmentConfiguration developmentConfiguration;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        developmentConfiguration = new DevelopmentConfiguration(userRepository);
    }

    @Test
    public void init() throws Exception {
        assertEquals(0, getTemplate().count(new Query(), User.class));
        developmentConfiguration.init();
        assertEquals(1, getTemplate().count(new Query(), User.class));
    }

}
