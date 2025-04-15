package com.exemplo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/openapi.json")
public class OpenApiResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Pessoas")
                        .version("1.0")
                        .description("API RESTful para gerenciamento de pessoas")
                        .termsOfService("http://teste.com/terms")
                        .contact(new Contact().name("Desenvolvedor").email("dev@exemplo.com"))
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0")));
    }
}