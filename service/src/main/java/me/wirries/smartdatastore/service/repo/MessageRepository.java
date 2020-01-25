package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.model.Message;
import me.wirries.smartdatastore.service.model.MessageType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This repository handles the {@link Message}s.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
@Transactional
public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * Find the messages with the given messageId.
     *
     * @param messageId UUID of the {@link me.wirries.smartdatastore.service.model.MessageId}
     * @return a List of {@link Message}s or an empty list, if not found
     */
    @Query(value = "{'messageId.$id': {$regex: ?0, $options: 'i'}}", sort = "{'created': 1}")
    List<Message> findByMessageId(String messageId);

    /**
     * Find the messages with the given messageId.
     *
     * @param messageId UUID of the {@link me.wirries.smartdatastore.service.model.MessageId}
     * @param page      for scrolling over the dataset
     * @return a List of {@link Message}s or an empty list, if not found
     */
    @Query(value = "{'messageId.$id': {$regex: ?0, $options: 'i'}}")
    List<Message> findByMessageId(String messageId, Pageable page);

    /**
     * Find the last message with the given messageId.
     *
     * @param messageId UUID of the {@link me.wirries.smartdatastore.service.model.MessageId}
     * @return a {@link Message} or null, if not found
     */
    default Message findLastByMessageId(String messageId) {
        List<Message> results = findByMessageId(messageId,
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "created")));
        return (!results.isEmpty()) ? results.get(0) : null;
    }

    /**
     * Find the messages with the given messageId and type (see {@link MessageType}).
     *
     * @param messageId UUID of the {@link me.wirries.smartdatastore.service.model.MessageId}
     * @param type      Type of the message
     * @return a List of {@link Message}s or an empty list, if not found
     */
    @Query(value = "{'messageId.$id': {$regex: ?0, $options: 'i'}, 'type': ?1}", sort = "{'created': 1}")
    List<Message> findByMessageId(String messageId, MessageType type);

    /**
     * Find the messages with the given messageId and type (see {@link MessageType}).
     *
     * @param messageId UUID of the {@link me.wirries.smartdatastore.service.model.MessageId}
     * @param type      Type of the message
     * @param page      for scrolling over the dataset
     * @return a List of {@link Message}s or an empty list, if not found
     */
    @Query(value = "{'messageId.$id': {$regex: ?0, $options: 'i'}, 'type': ?1}")
    List<Message> findByMessageId(String messageId, MessageType type, Pageable page);

    /**
     * Find the last message with the given messageId and type (see {@link MessageType}).
     *
     * @param messageId UUID of the {@link me.wirries.smartdatastore.service.model.MessageId}
     * @param type      Type of the message
     * @return a {@link Message} or null, if not found
     */
    default Message findLastByMessageId(String messageId, MessageType type) {
        List<Message> results = findByMessageId(messageId, type,
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "created")));
        return (!results.isEmpty()) ? results.get(0) : null;
    }

}
