package me.wirries.smartdatastore.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wirries.smartdatastore.service.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the REST controller for the api of the SmartDataService.
 * It's provides the access for the login.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
@Api(value = "REST API")
@RestController
@RequestMapping("/api")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final UserRepository userRepository;

    /**
     * Constructor with AutoWiring dependencies.
     *
     * @param userRepository Repository for user.
     */
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * List all users from the database.
     *
     * @return List of {@link UserResponse}
     */
    @ApiOperation(
            value = "Get all users",
            notes = "Return all users from the database. Role ADMIN is needed."
    )
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponse> listUsers() {
        LOGGER.debug("load all users from the database");
        // return userRepository.findAll();
        // TODO implement the response and transform
        return new ArrayList<>();
    }

    /**
     * Create a new user or updates an existing user.
     *
     * @param user {@link UserRequest}
     * @return Status of the creation / update
     */
    @ApiOperation(
            value = "Create or update a user",
            notes = "Creates a new user in the database, if the user not exists. Otherwise the user is updated." +
                    "Role ADMIN is needed."
    )
    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrudResponse<UserResponse> createOrUpdateUser(UserRequest user) {
        LOGGER.debug("Create / update from the request {}", user);
        // TODO implement the response and transform
        return null;
    }

    /**
     * Delete an existing user.
     *
     * @param id id of the user
     * @return Status of the deletion
     */
    @ApiOperation(
            value = "Delete an existing user",
            notes = "Delete an existing user in the database. Role ADMIN is needed."
    )
    @DeleteMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrudResponse<UserResponse> deleteUser(String id) {
        LOGGER.debug("Delete the user {}", id);
        // TODO implement the response and transform
        return null;
    }

    /**
     * List all Camel routes from the database.
     *
     * @return List of {@link RouteResponse}
     */
    @ApiOperation(
            value = "Get all routes",
            notes = "Return all users from the database. Role ADMIN is needed."
    )
    @GetMapping("/route")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RouteResponse> listRoutes() {
        LOGGER.debug("load all camel routes from the database");
        // return userRepository.findAll();
        // TODO implement the response and transform
        return new ArrayList<>();
    }

    /**
     * Create a new route or updates an existing route.
     *
     * @param route {@link RouteRequest}
     * @return Status of the creation / update
     */
    @ApiOperation(
            value = "Create or update a route",
            notes = "Creates a new route in the database, if the route not exists. Otherwise the route is updated." +
                    "Role ADMIN is needed."
    )
    @PostMapping("/route")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrudResponse<UserResponse> createOrUpdateRoute(RouteRequest route) {
        LOGGER.debug("Create / update from the request {}", route);
        // TODO implement the response and transform
        return null;
    }

    /**
     * Delete an existing Camel route.
     *
     * @param id id of the route
     * @return Status of the deletion
     */
    @ApiOperation(
            value = "Delete an existing route",
            notes = "Delete an existing route in the database. Role ADMIN is needed."
    )
    @DeleteMapping("/route")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrudResponse<RouteResponse> deleteRoute(String id) {
        LOGGER.debug("Delete the route {}", id);
        // TODO implement the response and transform
        return null;
    }

    /**
     * Start an existing Camel route.
     *
     * @param id id of the route
     * @return Status of the start
     */
    @ApiOperation(
            value = "Start an existing route",
            notes = "Start an existing route in the system. Role ADMIN is needed."
    )
    @GetMapping("/route/start/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StatusResponse<RouteResponse> startRoute(@PathVariable("id") String id) {
        LOGGER.debug("Start the route {}", id);
        // TODO implement the response and transform
        return null;
    }

    /**
     * Stop an existing Camel route.
     *
     * @param id id of the route
     * @return Status of the stop
     */
    @ApiOperation(
            value = "Stop an existing route",
            notes = "Stop an existing route in the system. Role ADMIN is needed."
    )
    @GetMapping("/route/stop/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StatusResponse<RouteResponse> stopRoute(@PathVariable("id") String id) {
        LOGGER.debug("Stop the route {}", id);
        // TODO implement the response and transform
        return null;
    }

    /**
     * List all messageId from the database.
     *
     * @return List of {@link MessageIdResponse}
     */
    @ApiOperation(
            value = "Get all messageIds",
            notes = "Return all messageIds from the database. Role ADMIN is needed."
    )
    @GetMapping("/messageId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MessageIdResponse> listMessageIds() {
        LOGGER.debug("load all messageIds from the database");
        // return userRepository.findAll();
        // TODO implement the response and transform
        return new ArrayList<>();
    }

    /**
     * Create a new messageId or updates an existing messageId.
     *
     * @param route {@link MessageIdRequest}
     * @return Status of the creation / update
     */
    @ApiOperation(
            value = "Create or update a messageId",
            notes = "Creates a new messageId in the database, if the messageId not exists. " +
                    "Otherwise the messageId is updated. Role ADMIN is needed."
    )
    @PostMapping("/messageId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrudResponse<MessageIdResponse> createOrUpdateMessageId(MessageIdRequest route) {
        LOGGER.debug("Create / update from the request {}", route);
        // TODO implement the response and transform
        return null;
    }

    /**
     * Delete an existing messageId and remove all messages from this type.
     *
     * @param id id of the messageId
     * @return Status of the deletion
     */
    @ApiOperation(
            value = "Delete an existing messageId",
            notes = "Delete an existing messageId in the database and remove all messages for this messageId." +
                    "Role ADMIN is needed."
    )
    @DeleteMapping("/messageId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrudResponse<MessageIdResponse> deleteMessageId(String id) {
        LOGGER.debug("Delete the messageId {}", id);
        // TODO implement the response and transform
        return null;
    }

}
