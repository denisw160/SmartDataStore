package me.wirries.smartdatastore.service.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.wirries.smartdatastore.service.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * This is the REST controller for the api of the SmartDataService.
 * It's provides the access to the sensor data.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.2018
 */
@Api(value = "REST API")
@RestController
@RequestMapping("/api")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private MessageService service;

    /**
     * Store the transferred message in the database. Role WEB is needed
     * and user must have write access to the messageId (see permission).
     *
     * @param principal current user
     * @param message   message and parameter for store
     * @return Result of store
     */
    @ApiOperation(
            value = "Store the message",
            notes = "Store the transferred message in the database. Role WEB is needed " +
                    "and user must have write access to the messageId (see permission)."
    )
    @PostMapping("/message")
    @PreAuthorize("hasRole('ROLE_WEB')")
    public MessageResponse store(Principal principal,
                                 @ApiParam("the payload and attributes of the message")
                                 @RequestParam MessageRequest message) {
        LOGGER.debug("Receive a message {}", message);
        // TODO implement the response and transform
        return null;
    }

    /**
     * Store the transferred message in the database. Role WEB is needed
     * and user must have write access to the messageId (see permission).
     *
     * @param principal current user
     * @param messageId id of the message type
     * @param message   message for store
     * @return Result of store
     */
    @ApiOperation(
            value = "Store the message",
            notes = "Store the transferred message in the database. Role WEB is needed " +
                    "and user must have write access to the messageId (see permission)."
    )
    @PostMapping("/message/{messageId}")
    @PreAuthorize("hasRole('ROLE_WEB')")
    public MessageResponse store(Principal principal,
                                 @ApiParam("id of the message type")
                                 @PathVariable("messageId") String messageId,
                                 @ApiParam("payload of the message")
                                 @RequestParam String message) {
        LOGGER.debug("Receive a message {} for messageId {}", message, messageId);
        // TODO implement the response and transform
        return null;
    }

    /**
     * Load stored messages from the database. Role WEB is needed
     * and user must have read access to the messageId (see permission).
     *
     * @param principal       current user
     * @param messageId       id of the message type
     * @param count           max records in result
     * @param order           see {@link OrderType}
     * @param onlyDefaultType load ony messages matched to the defaultType
     * @return a list of the messages
     */
    @ApiOperation(
            value = "Load the last n messages",
            notes = "Load stored messages from the database. Role WEB is needed " +
                    "and user must have read access to the messageId (see permission)."
    )
    @GetMapping("/message/{messageId}")
    @PreAuthorize("hasRole('ROLE_WEB')")
    public MessagesResponse load(Principal principal,
                                 @ApiParam("id of the message type")
                                 @PathVariable("messageId") String messageId,
                                 @ApiParam(value = "max records in result", example = "1000")
                                 @RequestParam(required = false, defaultValue = "1000") int count,
                                 @ApiParam("order of the records")
                                 @RequestParam(required = false, defaultValue = "ASC") OrderType order,
                                 @ApiParam("load ony messages matched to the defaultType")
                                 @RequestParam(required = false, defaultValue = "true") boolean onlyDefaultType) {
        LOGGER.debug("Load {} messages for messageId {}", count, messageId);
        // TODO implement the response and transform
        return null;
    }

    /**
     * @param principal       current user
     * @param messageId       id of the message type
     * @param count           max records in result
     * @param order           see {@link OrderType}
     * @param onlyDefaultType load ony messages matched to the defaultType
     * @return a list of the messages
     */
    @ApiOperation(
            value = "Load the last n unread messages",
            notes = "Load stored messages from the database. Role WEB is needed " +
                    "and user must have read access to the messageId (see permission)." +
                    "If the message is loaded, the message is marked as processed."
    )
    @GetMapping("/message/{messageId}/unread")
    @PreAuthorize("hasRole('ROLE_WEB')")
    @Transactional
    public MessagesResponse loadUnread(Principal principal,
                                       @ApiParam("id of the message type")
                                       @PathVariable("messageId") String messageId,
                                       @ApiParam(value = "max records in result", example = "1000")
                                       @RequestParam(required = false, defaultValue = "1000") int count,
                                       @ApiParam("order of the records")
                                       @RequestParam(required = false, defaultValue = "ASC") OrderType order,
                                       @ApiParam("load ony messages matched to the defaultType")
                                       @RequestParam(required = false, defaultValue = "true") boolean onlyDefaultType) {
        LOGGER.debug("Load {} unread messages for messageId {}", count, messageId);
        // TODO implement the response and transform
        return null;
    }

}
