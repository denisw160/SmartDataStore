package me.wirries.smartdatastore.service.mqttbroker;


import io.moquette.spi.impl.subscriptions.Topic;
import io.moquette.spi.security.IAuthorizator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    // TODO Implement security for the topics

    @Override
    public boolean canWrite(Topic topic, String user, String client) {
        return true;
    }

    @Override
    public boolean canRead(Topic topic, String user, String client) {
        return true;
    }

}
