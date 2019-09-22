package me.wirries.smartdatastore.service.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testcase for {@link Permission}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public class PermissionTest {

    private Permission read;
    private Permission write;
    private Permission readWrite;

    @Before
    public void setUp() {
        read = new Permission("id", ResourceType.MESSAGE_ID, PermissionType.READ);
        write = new Permission("id", ResourceType.MESSAGE_ID, PermissionType.WRITE);
        readWrite = new Permission("id", ResourceType.MESSAGE_ID, PermissionType.READWRITE);
    }

    @Test
    public void isRead() {
        assertEquals(ResourceType.MESSAGE_ID, read.getResource());

        assertTrue(read.canRead());
        assertFalse(write.canRead());
        assertTrue(readWrite.canRead());
    }

    @Test
    public void isWrite() {
        assertFalse(read.canWrite());
        assertTrue(write.canWrite());
        assertTrue(readWrite.canWrite());
    }

    @Test
    public void mqtt() {
        read = new Permission("topic", ResourceType.MQTT_TOPIC, PermissionType.READ);
        write = new Permission("topic", ResourceType.MQTT_TOPIC, PermissionType.WRITE);
        readWrite = new Permission("topic", ResourceType.MQTT_TOPIC, PermissionType.READWRITE);

        assertEquals(ResourceType.MQTT_TOPIC, read.getResource());

        assertTrue(read.canRead());
        assertFalse(write.canRead());
        assertTrue(readWrite.canRead());

        assertFalse(read.canWrite());
        assertTrue(write.canWrite());
        assertTrue(readWrite.canWrite());
    }

}
