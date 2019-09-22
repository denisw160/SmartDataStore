package me.wirries.smartdatastore.service.service;

import me.wirries.smartdatastore.service.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service handles the requests for the {@link me.wirries.smartdatastore.service.model.Message}s.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
@Service
public class MessageService {

    // TODO Implement service methods

    @Transactional
    public Message store(String messageId, String source, String payload) {

        return null;
    }

}
