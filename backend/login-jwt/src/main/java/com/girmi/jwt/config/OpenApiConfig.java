package com.girmi.jwt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/login-jwt", description = "Default Server url")
        },
        info = @Info(
                title = "JWT Login",
                description = "Login Application for JWT",
                version = "0.0.1"
        )
)
@Configuration
public class OpenApiConfig {
}