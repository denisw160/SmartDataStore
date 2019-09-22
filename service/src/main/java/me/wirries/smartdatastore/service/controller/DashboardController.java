package me.wirries.smartdatastore.service.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the REST controller for the api of the SmartDataService.
 * It's provides the access to the information for the dashboard.
 *
 * @author denisw
 * @version 1.0
 * @since 22.09.19
 */
@Api(value = "REST API")
@RestController
@RequestMapping("/api")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);


    // TODO implement methods

}
