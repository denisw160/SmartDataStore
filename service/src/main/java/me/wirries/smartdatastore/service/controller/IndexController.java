package me.wirries.smartdatastore.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * This is the main controller for displaying the index site of
 * the service.
 *
 * @author denisw
 * @version 1.0
 * @since 21.09.19
 */
@Controller
public class IndexController {

    /**
     * Return a Redirect to the SwaggerApi.
     *
     * @param response Redirect
     */
    @GetMapping("/")
    public void indexRedirect(HttpServletResponse response) {
        response.setHeader("Location", "/swagger-ui.html");
        response.setStatus(HttpStatus.FOUND.value());
    }

    /**
     * Return a Redirect to the SwaggerApi.
     *
     * @param response Redirect
     */
    @GetMapping("/api")
    public void swaggerRedirect(HttpServletResponse response) {
        response.setHeader("Location", "/swagger-ui.html");
        response.setStatus(HttpStatus.FOUND.value());
    }

}
