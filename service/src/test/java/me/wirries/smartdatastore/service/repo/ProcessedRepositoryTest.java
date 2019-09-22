package me.wirries.smartdatastore.service.repo;

import me.wirries.smartdatastore.service.AbstractRepositoryTests;
import me.wirries.smartdatastore.service.model.Message;
import me.wirries.smartdatastore.service.model.MessageId;
import me.wirries.smartdatastore.service.model.MessageType;
import me.wirries.smartdatastore.service.model.Processed;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Testcase for {@link ProcessedRepository}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
public class ProcessedRepositoryTest extends AbstractRepositoryTests {

    @Autowired
    private ProcessedRepository repository;

    private Processed processed;

    @Test
    public void create() {
        createData("test", 100);
    }

    private void createData(String id, int count) {
        MessageId messageId = new MessageId();
        messageId.setId(UUID.randomUUID().toString());
        messageId.setName(id);
        messageId.setDescription("test description");
        messageId.setDefaultType(MessageType.JSON);
        messageId.setCreated(new Date());
        messageId.setUpdated(new Date());
        getTemplate().save(messageId);

        for (int i = 0; i < count; i++) {
            Message message = new Message();
            message.setId(UUID.randomUUID().toString());
            message.setMessageId(messageId);
            message.setPayload("{\"value\":" + i + "}");
            if (i % 3 == 0) message.setType(MessageType.JSON);
            else if (i % 3 == 2) message.setType(MessageType.XML);
            else message.setType(MessageType.UNKNOWN);
            message.setCreated(DateUtils.addMinutes(new Date(), -i));
            message.setSource("unit test");
            getTemplate().save(message);

            processed = new Processed();
            processed.setId(UUID.randomUUID().toString());
            processed.setMessage(message);
            processed.setClientId(id);
            processed.setCreated(new Date());

            repository.save(processed);
        }
    }

    @Test
    public void load() {
        create();

        Optional<Processed> findById = repository.findById(processed.getId());
        assertTrue(findById.isPresent());
        Processed loaded = findById.get();
        assertNotNull(loaded);
        assertEquals(processed.getId(), loaded.getId());
        assertEquals(processed.getMessage(), loaded.getMessage());
        assertEquals(processed.getClientId(), loaded.getClientId());
        assertEquals(processed.getCreated(), loaded.getCreated());
    }

    @Test
    public void findByClientId() {
        createData("test1", 100);
        createData("test2", 200);

        List<Processed> byMessageId = repository.findByClientId("test1");
        assertNotNull(byMessageId);
        assertEquals(100, byMessageId.size());
    }

    @Test
    public void findByClientIdNotFound() {
        createData("test1", 100);
        createData("test2", 200);

        List<Processed> byMessageId = repository.findByClientId("notFound");
        assertNotNull(byMessageId);
        assertEquals(0, byMessageId.size());
    }

}
