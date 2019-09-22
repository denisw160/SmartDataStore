package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.model.Processed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This repository handles the {@link Processed}s.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
@Transactional
public interface ProcessedRepository extends MongoRepository<Processed, String> {

    /**
     * Find the processed messages for the given clientId.
     *
     * @param clientId clientId
     * @return a List of {@link Processed} or an empty list, if not found
     */
    @Query(value = "{'clientId': {$regex : ?0, $options: 'i'}}")
    List<Processed> findByClientId(String clientId);

}
