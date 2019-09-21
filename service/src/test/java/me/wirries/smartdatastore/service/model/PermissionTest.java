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
        read = Permission.createMessageIdPermission("id", PermissionType.READ);
        write = Permission.createMessageIdPermission("id", PermissionType.WRITE);
        readWrite = Permission.createMessageIdPermission("id", PermissionType.READWRITE);
    }

    @Test
    public void isRead() {
        assertEquals(ResourceType.WEB, read.getResource());

        assertTrue(read.isRead());
        assertFalse(write.isRead());
        assertTrue(readWrite.isRead());
    }

    @Test
    public void isWrite() {
        assertFalse(read.isWrite());
        assertTrue(write.isWrite());
        assertTrue(readWrite.isWrite());
    }

    @Test
    public void mqtt() {
        read = Permission.createMqttTopicPermission("topic", PermissionType.READ);
        write = Permission.createMqttTopicPermission("topic", PermissionType.WRITE);
        readWrite = Permission.createMqttTopicPermission("topic", PermissionType.READWRITE);

        assertEquals(ResourceType.MQTT, read.getResource());

        assertTrue(read.isRead());
        assertFalse(write.isRead());
        assertTrue(readWrite.isRead());

        assertFalse(read.isWrite());
        assertTrue(write.isWrite());
        assertTrue(readWrite.isWrite());
    }

}
