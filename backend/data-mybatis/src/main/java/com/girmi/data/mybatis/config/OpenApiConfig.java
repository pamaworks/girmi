package com.girmi.data.mybatis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "/data-mybatis", description = "Default Server url")
        },
        info = @Info(
                title = "Data Mybatis",
                description = "Data Mybatis Query",
                version = "0.0.1"
        )
)
@Configuration
public class OpenApiConfig {
}
