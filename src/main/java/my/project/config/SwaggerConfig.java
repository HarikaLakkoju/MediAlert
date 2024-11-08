package my.project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Medication Reminder API",
        version = "1.0",
        description = "API documentation for Medication Reminder System"
))
public class SwaggerConfig {
}
