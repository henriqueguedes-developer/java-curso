package com.exemplo.config;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.ws.rs.core.Application;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SwaggerConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(OpenApiResource.class).collect(Collectors.toSet());
    }

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Pessoas")
                        .version("1.0")
                        .description("API RESTful para gerenciamento de pessoas")
                        .termsOfService("http://teste.comterms")
                        .contact(new Contact().name("Desenvolvedor").email("dev@esxemplo.com"))
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0")));
    }
}