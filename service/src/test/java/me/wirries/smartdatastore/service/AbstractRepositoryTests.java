package me.wirries.smartdatastore.service;

import me.wirries.smartdatastore.service.model.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Abstract test class for testing with the database.
 * Test drop the data and create test data in the method {@link #setUp()}.
 */
public abstract class AbstractRepositoryTests extends AbstractApplicationTests {

    @Autowired
    private MongoTemplate template;

    public MongoTemplate getTemplate() {
        return template;
    }

    @Before
    public void setUp() throws Exception {
        // drop data
        getTemplate().dropCollection(User.class);
    }

}
