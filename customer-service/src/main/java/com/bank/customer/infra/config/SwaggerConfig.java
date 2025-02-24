package com.bank.customer.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger API documentation.
 * <p>
 * This class sets up Swagger (OpenAPI 3.0) documentation for the **Customer Service**.
 * It provides a global API definition for better visibility and testing of customer-related endpoints.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures and provides a customized OpenAPI documentation instance for the Customer Service.
     *
     * @return An instance of {@link OpenAPI} with service-specific metadata.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Banking System API - Customer Service")
                        .version("1.0")
                        .description("API documentation for managing customer profiles"));
    }
}