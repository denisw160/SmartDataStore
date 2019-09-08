package me.wirries.smartdatastore.service.config;

import me.wirries.smartdatastore.service.model.Permission;
import me.wirries.smartdatastore.service.model.Role;
import me.wirries.smartdatastore.service.model.User;
import me.wirries.smartdatastore.service.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
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

    private UserRepository userRepository;

    public DevelopmentConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() throws Exception {
        dropCollections();
        createUser();
    }

    private void dropCollections() {
        LOGGER.info("Deleting all user");
        userRepository.deleteAll();
    }

    private void createUser() throws Exception {
        LOGGER.info("Creating default user");
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserId("admin");
        user.updatePassword("password");
        user.setCreated(new Date());

        List<Role> roleList = new ArrayList<>();
        user.updateRoles(roleList);

        List<Permission> permissionList = new ArrayList<>();
        user.updatePermission(permissionList);
        userRepository.save(user);
    }

}
