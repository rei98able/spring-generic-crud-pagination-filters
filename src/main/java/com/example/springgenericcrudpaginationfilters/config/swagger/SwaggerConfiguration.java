package com.example.springgenericcrudpaginationfilters.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    /**
     * OpenApi Bean
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .info(springDocapiInfo())
                .security(List.of(new SecurityRequirement()));

    }

    /**
     * Swagger Header description
     *
     * @return Created Info
     */
    Info springDocapiInfo() {
        return new Info()
                .title("Spring CRUD Example")
                .description("Spring CRUD Example Endpoints")
                .version("1.0.0");
    }

}
