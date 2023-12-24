package com.girmi.service.common.apis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Service Common",
                description = "Service Common",
                version = "0.0.1"
        )
)
@Configuration
public class OpenApiConfig {
}
