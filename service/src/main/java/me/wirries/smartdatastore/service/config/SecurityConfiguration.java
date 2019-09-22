package me.wirries.smartdatastore.service.config;

import me.wirries.smartdatastore.service.model.User;
import me.wirries.smartdatastore.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the security configuration of the application. The user will validate against the database.
 * The {@link User} model can be found under the model package.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.2019
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Value("${app.security.logSessions}")
    private boolean logSessions;

    private UserService userService;

    /**
     * Constructor for initialize the {@link SecurityConfiguration}.
     *
     * @param userService service for the userDetails
     */
    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        LOGGER.info("Enable provider for authentication");
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Enabling security for resources");
        http.authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic().realmName("SmartDataStoreService")
                .and()
                .csrf().disable();

        // Activate session management
        final int maximumSessions = 15;
        http.sessionManagement()
                .maximumSessions(maximumSessions)
                .sessionRegistry(sessionRegistry());
    }

    /**
     * Create the bean for the {@link PasswordEncoder}.
     *
     * @return see {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Create the bean for the {@link AuthenticationProvider}.
     *
     * @return see {@link AuthenticationProvider}
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }


    /**
     * The bean for the session sessionRegistry.
     *
     * @return see {@link SessionRegistryImpl}
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * Job for print the login to log file. Runs every 60s.
     */
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        if (!logSessions) return;

        List<Object> allPrincipals = sessionRegistry().getAllPrincipals();
        if (allPrincipals.isEmpty()) {
            LOGGER.info("No login found");
            return;
        }

        // Collect all (not expired) sessions in a List
        List<SessionInformation> sessions = allPrincipals.stream()
                .filter(u -> !sessionRegistry().getAllSessions(u, false).isEmpty())
                .map(u -> sessionRegistry().getAllSessions(u, false))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Log sessions to LOGGER
        LOGGER.info("Logging {} open sessions ...", sessions.size());
        LOGGER.info("SessionId \t\t\t\t\t\t\t UserId \t Last request");
        for (SessionInformation s : sessions) {
            LOGGER.info("{} \t {} \t\t {}",
                    s.getSessionId(),
                    ((User) s.getPrincipal()).getUsername(),
                    FORMAT.format(s.getLastRequest()));
        }
    }

}
