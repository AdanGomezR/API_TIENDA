package com.miempresa.api_tienda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Tienda - Gestión de Productos y Clientes")
                        .version("1.0.0")
                        .description("API REST para la gestión de productos y clientes de una tienda. " +
                                "Incluye operaciones CRUD completas con validaciones y manejo de errores.")
                        .contact(new Contact()
                                .name("Mi Empresa")
                                .email("contacto@miempresa.com")
                                .url("https://www.miempresa.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
