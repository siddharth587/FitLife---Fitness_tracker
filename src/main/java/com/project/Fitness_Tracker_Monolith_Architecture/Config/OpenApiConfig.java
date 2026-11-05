package com.project.Fitness_Tracker_Monolith_Architecture.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customApI(){
        return new OpenAPI()
                .info(new Info().title("Fitness Tracking API")
                        .version("v1.0")
                        .description("Production Grade API's")
                        .contact(new Contact().name("Siddharth").email("DummyMail@example.com"))
                        .license(new License().name("Apache 2.0"))
                );
    }
}
