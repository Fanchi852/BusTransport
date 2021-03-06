package com.example.BusTransport.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusTransportConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Bus Transport API Information")
                        .description("Ejemplo de API REST")
                        .contact(new Contact()
                                .name("Francisco Jesus Moya Olivares")
                                .email("fjmo852@gmail.com")
                                .url("https://datos.codeandcoke.com"))
                        .version("1.0"));
    }
}
