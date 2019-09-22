package me.wirries.smartdatastore.service.mqttbroker;

import io.moquette.spi.impl.subscriptions.Topic;
import io.moquette.spi.security.IAuthorizator;
import me.wirries.smartdatastore.service.config.SpringContextProvider;
import me.wirries.smartdatastore.service.model.PermissionType;
import me.wirries.smartdatastore.service.model.ResourceType;
import me.wirries.smartdatastore.service.model.User;
import me.wirries.smartdatastore.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import static me.wirries.smartdatastore.service.utils.AuthorityHelper.hasPermission;

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
            return hasPermission(u, topic.toString(), ResourceType.MQTT_TOPIC, PermissionType.WRITE);
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
            return hasPermission(u, topic.toString(), ResourceType.MQTT_TOPIC, PermissionType.READ);
        } catch (Exception e) {
            LOGGER.error("User {} not found - access denied", user);
            return false;
        }
    }

    private UserService getUserService() {
        ApplicationContext applicationContext = SpringContextProvider.getApplicationContext();
        return applicationContext.getBean(UserService.class);
    }

}
