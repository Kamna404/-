package edu.sbs.cs.view.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Lost and Found Platform API",
        description = "API documentation for Lost and Found Platform",
        version = "1.0"
    )
)
public class SwaggerConfig {
}
