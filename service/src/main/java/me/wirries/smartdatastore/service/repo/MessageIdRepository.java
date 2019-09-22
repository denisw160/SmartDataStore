package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.model.MessageId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * This repository handles the {@link MessageId}s.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
public interface MessageIdRepository extends MongoRepository<MessageId, String> {

    /**
     * Find the messageId with the given name.
     *
     * @param name name of the {@link MessageId}
     * @return a {@link MessageId} or null, if not found
     */
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    MessageId findByName(String name);

}
