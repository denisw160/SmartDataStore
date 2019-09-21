package me.wirries.smartdatastore.service.mqttbroker;


import io.moquette.spi.impl.subscriptions.Topic;
import io.moquette.spi.security.IAuthorizator;
import me.wirries.smartdatastore.service.config.SpringContextProvider;
import me.wirries.smartdatastore.service.model.Permission;
import me.wirries.smartdatastore.service.model.ResourceType;
import me.wirries.smartdatastore.service.model.User;
import me.wirries.smartdatastore.service.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * A wrapper for the mqtt broker to use the Spring authorization.
 * The authorization is check against the {@link me.wirries.smartdatastore.service.service.UserService}.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class SpringAuthorizationWrapper implements IAuthorizator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAuthorizationWrapper.class);

    @Override
    public boolean canWrite(Topic topic, String user, String client) {
        try {
            UserService userService = getUserService();

            User u = userService.loadUserByUsername(user);
            Permission p = findPermission(u, topic.toString());
            return p != null && p.isWrite();
        } catch (Exception e) {
            LOGGER.error("User {} not found - access denied", user);
            return false;
        }
    }

    @Override
    public boolean canRead(Topic topic, String user, String client) {
        try {
            UserService userService = getUserService();

            User u = userService.loadUserByUsername(user);
            Permission p = findPermission(u, topic.toString());
            return p != null && p.isRead();
        } catch (Exception e) {
            LOGGER.error("User {} not found - access denied", user);
            return false;
        }
    }

    /**
     * Find the matching permission for the given topic.
     *
     * @param user  User
     * @param topic Topic
     * @return Permission or NULL - if not found
     */
    private Permission findPermission(User user, String topic) throws Exception {
        List<Permission> permissionList = user.readPermissions();
        if (permissionList == null) return null;

        for (Permission p : permissionList) {
            if (ResourceType.MQTT.equals(p.getResource()) && StringUtils.equalsIgnoreCase(topic, p.getTopic())) {
                return p;
            }
        }
        return null;
    }

    private UserService getUserService() {
        ApplicationContext applicationContext = SpringContextProvider.getApplicationContext();
        return applicationContext.getBean(UserService.class);
    }

}
