package me.wirries.smartdatastore.service;

import me.wirries.smartdatastore.service.model.*;
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

    protected MongoTemplate getTemplate() {
        return template;
    }

    @Before
    public void setUp() throws Exception {
        // drop data
        dropData();

        // create data
        createUsers();
    }

    protected void dropData() {
        getTemplate().dropCollection(User.class);
        getTemplate().dropCollection(Message.class);
        getTemplate().dropCollection(MessageId.class);
    }

    protected void createUsers() throws Exception {
        LOGGER.debug("Creating admin user");
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserId("admin");
        user.updatePassword("password");
        user.setCreated(new Date());

        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.createRole("ROLE_MQTT"));
        user.updateRoles(roleList);

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(Permission.createMessageIdPermission("messageIdRead", PermissionType.READ));
        permissionList.add(Permission.createMessageIdPermission("messageIdWrite", PermissionType.WRITE));
        permissionList.add(Permission.createMessageIdPermission("messageIdReadWrite", PermissionType.READWRITE));

        permissionList.add(Permission.createMqttTopicPermission("topicRead", PermissionType.READ));
        permissionList.add(Permission.createMqttTopicPermission("topicWrite", PermissionType.WRITE));
        permissionList.add(Permission.createMqttTopicPermission("topicReadWrite", PermissionType.READWRITE));

        user.updatePermission(permissionList);
        getTemplate().save(user);

        LOGGER.debug("Creating normal user");
        user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserId("user");
        user.updatePassword("pwd");
        user.setCreated(new Date());

        roleList = new ArrayList<>(); // TODO Setup
        user.updateRoles(roleList);

        permissionList = new ArrayList<>(); // TODO Setup
        user.updatePermission(permissionList);

        getTemplate().save(user);
    }

}
