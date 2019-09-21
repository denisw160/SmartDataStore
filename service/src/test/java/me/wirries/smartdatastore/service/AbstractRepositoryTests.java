package me.wirries.smartdatastore.service;

import me.wirries.smartdatastore.service.model.Permission;
import me.wirries.smartdatastore.service.model.Role;
import me.wirries.smartdatastore.service.model.User;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Abstract test class for testing with the database.
 * Test drop the data and create test data in the method {@link #setUp()}.
 */
public abstract class AbstractRepositoryTests extends AbstractApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepositoryTests.class);

    @Autowired
    private MongoTemplate template;

    public MongoTemplate getTemplate() {
        return template;
    }

    @Before
    public void setUp() throws Exception {
        // drop data
        getTemplate().dropCollection(User.class);

        // create data

        LOGGER.debug("Creating default user");
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserId("admin");
        user.updatePassword("password");
        user.setCreated(new Date());

        List<Role> roleList = new ArrayList<>();
        user.updateRoles(roleList);

        List<Permission> permissionList = new ArrayList<>();
        // TODO Add permissions
        user.updatePermission(permissionList);
        getTemplate().save(user);
    }

}
