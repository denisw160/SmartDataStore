package me.wirries.smartdatastore.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;
import java.util.Collections;

/**
 * This is the configuration of Swagger UI.
 *
 * @author denisw
 * @version 1.0
 * @since 08.09.19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${app.version}")
    private String version;

    /**
     * Creates the bean for the documentation of the REST api.
     * The api can open under http://../swagger-ui.html
     *
     * @return see {@link Docket}
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Principal.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**/*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Create the info for the api.
     *
     * @return see {@link ApiInfo}
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "SmartDataStore-Service",
                "The REST API for managing the SmartDataStore Service.",
                version,
                null,
                null,
                null,
                null,
                Collections.emptyList());
    }

}
