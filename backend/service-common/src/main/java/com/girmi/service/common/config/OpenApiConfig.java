package com.girmi.service.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/service-common", description = "Default Server url")
        },
        info = @Info(
                title = "Service Common",
                description = "Service Common",
                version = "0.0.1"
        )
)
@Configuration
public class OpenApiConfig {
}