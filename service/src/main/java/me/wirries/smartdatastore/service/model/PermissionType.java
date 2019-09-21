package me.wirries.smartdatastore.service.model;

/**
 * This enum has the types of a {@link Permission}.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
public enum PermissionType {

    /**
     * Permission can only read.
     */
    READ,

    /**
     * Permission can only write.
     */
    WRITE,

    /**
     * Permission can read and write.
     */
    READWRITE

}
