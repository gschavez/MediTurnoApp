package com.example.Mediturno.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RELACIONES EN JPA")
                        .version("1.0")
                        .description("API PARA RELACIONES")
                        .contact(new Contact()
                                .name("DESARROLLADOR DE API")
                                .email("prueba@prueba.com")));
    }
}
