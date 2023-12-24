package com.girmi.data.jpa.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/data-jpa", description = "Default Server url")
        },
        info = @Info(
                title = "Data JPA",
                description = "Data JPA",
                version = "0.0.1"
        )
)
@Configuration
public class OpenApiConfig {
}
