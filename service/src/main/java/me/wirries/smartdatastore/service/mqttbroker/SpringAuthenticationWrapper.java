package me.wirries.smartdatastore.service.mqttbroker;

import io.moquette.spi.security.IAuthenticator;
import me.wirries.smartdatastore.service.config.SpringContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static me.wirries.smartdatastore.service.utils.AuthorityHelper.hasRole;


/**
 * A wrapper for use the Spring {@link AuthenticationProvider} to check the authentication of the user.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
public class SpringAuthenticationWrapper implements IAuthenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAuthenticationWrapper.class);

    @Override
    public boolean checkValid(String clientId, String username, byte[] password) {
        LOGGER.debug("Check client credentials - clientId {}, username {}", clientId, username);

        try {
            ApplicationContext applicationContext = SpringContextProvider.getApplicationContext();
            AuthenticationProvider authenticationProvider = applicationContext.getBean(AuthenticationProvider.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, new String(password));
            Authentication authenticate = authenticationProvider.authenticate(authentication);

            LOGGER.info("Client login for clientId {} and username {} is authenticated", clientId, username);
            return authenticate.isAuthenticated() && hasRole("ROLE_MQTT", authenticate.getAuthorities());
        } catch (Exception e) {
            LOGGER.warn("Client login for clientId {} and username {} failed", clientId, username);
            LOGGER.debug("Exception during login", e);
            return false;
        }
    }

}
