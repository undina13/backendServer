package com.undina.backendserver.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("backend-сервер на Spring для функционирования доски объявлений.")
                                .version("1.0.0")
                                .contact(
                                new Contact()
                                        .email("foxundina@gmail.com")
                                        .url("https://github.com/undina13")
                                        .name("Suponeva Irina")
                        )
                );
    }
}
