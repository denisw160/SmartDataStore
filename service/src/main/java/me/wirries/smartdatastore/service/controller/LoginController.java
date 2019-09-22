package me.wirries.smartdatastore.service.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * This is the REST controller for the api of the SmartDataService.
 * It's provides the access for the login.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.2018
 */
@Api(value = "REST API")
@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * Constructor for the controller.
     */
    public LoginController() {
        LOGGER.info("Api controller for login created");
    }

    /**
     * This simple method return the result of the login. If the the user is authenticated,
     * it return success=true.
     *
     * @param principal {@link Principal} of the current login
     * @return result of the login
     */
    @ApiOperation(
            value = "Perform the login",
            notes = "Return the state of the login. If the login ist success, the field success is true."
    )
    @GetMapping("/login")
    public LoginResponse login(Principal principal) {
        if (principal == null) {
            return new LoginResponse("notAuthenticated", false);
        }

        return new LoginResponse(principal.getName(), true);
    }

}
