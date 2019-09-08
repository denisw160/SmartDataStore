package me.wirries.smartdatastore.service.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        read = new Permission("id", PermissionType.READ);
        write = new Permission("id", PermissionType.WRITE);
        readWrite = new Permission("id", PermissionType.READWRITE);
    }

    @Test
    public void isRead() {
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

}
