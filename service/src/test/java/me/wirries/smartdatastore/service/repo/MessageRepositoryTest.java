package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import me.wirries.smartdatastore.service.model.Message;
import me.wirries.smartdatastore.service.model.MessageId;
import me.wirries.smartdatastore.service.model.MessageType;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Testcase for {@link MessageRepository}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
public class MessageRepositoryTest extends AbstractRepositoryTests {

    @Autowired
    private MessageRepository repository;

    private Message message;
    private MessageId messageId;

    @Test
    public void create() {
        createData("test", 100);
    }

    private void createData(String messageIdName, int count) {
        messageId = new MessageId();
        messageId.setId(UUID.randomUUID().toString());
        messageId.setName(messageIdName);
        messageId.setDescription("test description");
        messageId.setDefaultType(MessageType.JSON);
        messageId.setCreated(new Date());
        messageId.setUpdated(new Date());
        getTemplate().save(messageId);

        for (int i = 0; i < count; i++) {
            message = new Message();
            message.setId(UUID.randomUUID().toString());
            message.setMessageId(messageId);
            message.setPayload("{\"value\":" + i + "}");
            if (i % 3 == 0) message.setType(MessageType.JSON);
            else if (i % 3 == 2) message.setType(MessageType.XML);
            else message.setType(MessageType.UNKNOWN);
            message.setCreated(DateUtils.addMinutes(new Date(), -i));
            message.setSource("unit test");
            repository.save(message);
        }
    }

    @Test
    public void load() {
        create();

        Optional<Message> findById = repository.findById(message.getId());
        assertTrue(findById.isPresent());
        Message loadedMessage = findById.get();
        assertNotNull(loadedMessage);
        assertEquals(message.getMessageId(), loadedMessage.getMessageId());
        assertEquals(message.getPayload(), loadedMessage.getPayload());
        assertEquals(message.getType(), loadedMessage.getType());
        assertEquals(message.getCreated(), loadedMessage.getCreated());
        assertEquals(message.getSource(), loadedMessage.getSource());
    }

    @Test
    public void findByMessageId() {
        createData("test1", 100);
        createData("test2", 200);

        List<Message> byMessageId = repository.findByMessageId(messageId.getId());
        assertNotNull(byMessageId);
        assertEquals(200, byMessageId.size());
        Iterator<Message> iterator = byMessageId.iterator();
        for (int i = 0; i < 200; i++) {
            Message next = iterator.next();
            assertEquals("{\"value\":" + i + "}", next.getPayload());
        }
    }

    @Test
    public void findByMessageIdNotFound() {
        createData("test1", 100);

        List<Message> byMessageId = repository.findByMessageId("notFound");
        assertNotNull(byMessageId);
        assertEquals(0, byMessageId.size());
    }

    @Test
    public void findByMessageIdPageable() {
        createData("test1", 100);
        createData("test2", 200);

        List<Message> byMessageId = repository.findByMessageId(messageId.getId(), PageRequest.of(1, 20));
        assertNotNull(byMessageId);
        assertEquals(20, byMessageId.size());
        Iterator<Message> iterator = byMessageId.iterator();
        for (int i = 0; i < 20; i++) {
            Message next = iterator.next();
            assertEquals("{\"value\":" + (20 + i) + "}", next.getPayload());
        }
    }

    @Test
    public void findByMessageIdPageableNotFound() {
        createData("test1", 100);

        List<Message> byMessageId = repository.findByMessageId("notFound", PageRequest.of(1, 20));
        assertNotNull(byMessageId);
        assertEquals(0, byMessageId.size());
    }

    @Test
    public void findLastByMessageId() {
        createData("test1", 100);
        createData("test2", 200);

        Message byMessageId = repository.findLastByMessageId(messageId.getId());
        assertNotNull(byMessageId);
        assertEquals("{\"value\":0}", byMessageId.getPayload());
    }

    @Test
    public void findLastByMessageIdNotFound() {
        createData("test1", 100);

        Message byMessageId = repository.findLastByMessageId("notFound");
        assertNull(byMessageId);
    }

    @Test
    public void findByMessageIdType() {
        createData("test2", 300);

        List<Message> byMessageId = repository.findByMessageId(messageId.getId(), messageId.getDefaultType());
        assertNotNull(byMessageId);
        assertEquals(100, byMessageId.size());
        Message m = byMessageId.get(0);
        assertEquals("{\"value\":0}", m.getPayload());
        m = byMessageId.get(99);
        assertEquals("{\"value\":297}", m.getPayload());
    }

    @Test
    public void findByMessageIdTypeNotFound() {
        createData("test2", 300);

        List<Message> byMessageId = repository.findByMessageId("notFound", messageId.getDefaultType());
        assertNotNull(byMessageId);
        assertEquals(0, byMessageId.size());
    }

    @Test
    public void findByMessageIdTypePageable() {
        createData("test2", 300);

        List<Message> byMessageId = repository.findByMessageId(
                messageId.getId(), messageId.getDefaultType(), PageRequest.of(1, 20));
        assertNotNull(byMessageId);
        assertEquals(20, byMessageId.size());
        Message m = byMessageId.get(0);
        assertEquals("{\"value\":60}", m.getPayload());
        m = byMessageId.get(19);
        assertEquals("{\"value\":117}", m.getPayload());
    }

    @Test
    public void findByMessageIdTypePageableNotFound() {
        createData("test2", 300);

        List<Message> byMessageId = repository.findByMessageId(
                "notFound", messageId.getDefaultType(), PageRequest.of(1, 20));
        assertNotNull(byMessageId);
        assertEquals(0, byMessageId.size());
    }

    @Test
    public void findLastByMessageIdType() {
        createData("test2", 300);

        Message byMessageId = repository.findLastByMessageId(messageId.getId(), messageId.getDefaultType());
        assertNotNull(byMessageId);
        assertEquals("{\"value\":0}", byMessageId.getPayload());
    }

    @Test
    public void findLastByMessageIdTypeNotFound() {
        createData("test2", 300);

        Message byMessageId = repository.findLastByMessageId("notFound", messageId.getDefaultType());
        assertNull(byMessageId);
    }

}
