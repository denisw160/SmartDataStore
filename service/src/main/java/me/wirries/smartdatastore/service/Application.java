package me.wirries.smartdatastore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class of the application and starts the service.
 *
 * @author denisw
 * @version 1.0
 * @since 01.09.2019
 */
@SpringBootApplication
public class Application {

    // TODO Add WebServlet for running in Tomcat

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * Start the application.
     *
     * @param args arguments from the command line
     */
    public static void main(String[] args) {
        LOGGER.info("Starting the SmartDataStoreService ...");
        SpringApplication.run(Application.class, args);
    }

}
