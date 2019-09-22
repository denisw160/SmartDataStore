package me.wirries.smartdatastore.service.config;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import me.wirries.smartdatastore.service.model.MessageId;
import me.wirries.smartdatastore.service.model.User;
import org.junit.Test;
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

    private DevelopmentConfiguration developmentConfiguration;

    @Override
    public void setUp() {
        dropData();
        developmentConfiguration = new DevelopmentConfiguration(getTemplate());
    }

    @Test
    public void init() throws Exception {
        assertEquals(0, getTemplate().count(new Query(), User.class));
        assertEquals(0, getTemplate().count(new Query(), MessageId.class));
        assertEquals(0, getTemplate().count(new Query(), User.class));
        developmentConfiguration.init();
        assertEquals(1, getTemplate().count(new Query(), User.class));
    }

}
