package me.wirries.smartdatastore.service.config;

import me.wirries.smartdatastore.service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * This bean creates the local configuration for development.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
@Component
@Profile("development")
public class DevelopmentConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevelopmentConfiguration.class);

    private MongoTemplate template;

    /**
     * Constructor with AutoWiring dependencies.
     *
     * @param template {@link MongoTemplate}
     */
    @Autowired
    public DevelopmentConfiguration(MongoTemplate template) {
        this.template = template;
    }

    /**
     * Initialize the database.
     *
     * @throws Exception during initialize
     */
    @PostConstruct
    public void init() throws Exception {
        dropCollections();

        createUser();
        createDefaultMessageId();
    }

    private void dropCollections() {
        LOGGER.info("Deleting all user");
        template.dropCollection(User.class);

        LOGGER.info("Deleting all processed messages");
        template.dropCollection(Processed.class);

        LOGGER.info("Deleting all messages");
        template.dropCollection(Message.class);

        LOGGER.info("Deleting all messageId");
        template.dropCollection(MessageId.class);
    }

    private void createUser() throws Exception {
        LOGGER.info("Creating default user");
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserId("admin");
        user.updatePassword("password");
        user.setCreated(new Date());

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role("ROLE_ADMIN"));
        roleList.add(new Role("ROLE_MQTT"));
        roleList.add(new Role("ROLE_REST"));
        user.updateRoles(roleList);

        List<Permission> permissionList = new ArrayList<>();
        user.updatePermission(permissionList); // TODO add default permissions

        template.save(user);
    }

    private void createDefaultMessageId() {
        LOGGER.info("Creating default messageId");
        MessageId messageId = new MessageId();
        messageId.setId("d7d7ec0c-77ad-4cf5-95f1-2d52fd39d407");
        messageId.setName("default");
        messageId.setDescription("default messageId");
        messageId.setDefaultType(MessageType.JSON);
        messageId.setCreated(new Date());
        messageId.setUpdated(new Date());
    }

}
