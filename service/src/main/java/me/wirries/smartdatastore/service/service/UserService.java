package me.wirries.smartdatastore.service.service;

import me.wirries.smartdatastore.service.model.User;
import me.wirries.smartdatastore.service.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This service is for validate the {@link User}s.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;

    /**
     * Constructor for initialize the UserService.
     *
     * @param repository Repository for the {@link User}
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Load user {} from database", username);
        User user = repository.findByUserId(username);
        if (user == null) throw new UsernameNotFoundException(String.format("Username [%s] not found", username));

        LOGGER.debug("Found: {}", user);
        return user;
    }

}
