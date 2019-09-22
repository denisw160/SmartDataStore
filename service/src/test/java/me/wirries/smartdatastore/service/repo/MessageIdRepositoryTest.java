package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import me.wirries.smartdatastore.service.model.MessageId;
import me.wirries.smartdatastore.service.model.MessageType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Testcase for {@link MessageIdRepository}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
public class MessageIdRepositoryTest extends AbstractRepositoryTests {

    @Autowired
    private MessageIdRepository repository;

    private MessageId messageId;

    @Test
    public void create() {
        messageId = new MessageId();
        messageId.setId(UUID.randomUUID().toString());
        messageId.setName("test");
        messageId.setDescription("test description");
        messageId.setDefaultType(MessageType.JSON);
        messageId.setCreated(new Date());
        messageId.setUpdated(new Date());

        repository.save(messageId);
    }

    @Test
    public void load() {
        create();

        Optional<MessageId> findById = repository.findById(messageId.getId());
        assertTrue(findById.isPresent());
        MessageId loadedMessageId = findById.get();
        assertNotNull(loadedMessageId);
        assertEquals(messageId.getName(), loadedMessageId.getName());
        assertEquals(messageId.getDescription(), loadedMessageId.getDescription());
        assertEquals(messageId.getDefaultType(), loadedMessageId.getDefaultType());
        assertEquals(messageId.getCreated(), loadedMessageId.getCreated());
        assertEquals(messageId.getUpdated(), loadedMessageId.getUpdated());
    }

    @Test
    public void findByName() {
        create();

        MessageId test = repository.findByName("test");
        assertNotNull(test);
        assertEquals("test", test.getName());

        MessageId testUpper = repository.findByName("TEST");
        assertNotNull(testUpper);
        assertEquals("test", testUpper.getName());

        MessageId unknown = repository.findByName("unknown");
        assertNull(unknown);
    }

}
