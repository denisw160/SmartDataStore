package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * This repository handles the {@link User}s.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Find the user with the given userId.
     *
     * @param userId userId
     * @return a User or null, if not found
     */
    @Query(value = "{'userId': {$regex : ?0, $options: 'i'}}")
    User findByUserId(String userId);

}
