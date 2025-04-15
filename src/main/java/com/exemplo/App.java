// src/main/java/com/exemplo/App.java
package com.exemplo;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.exemplo.config.SwaggerConfig;
import com.exemplo.config.OpenApiResource;
import com.exemplo.resource.SwaggerResource;

import java.io.IOException;
import java.net.URI;

public class App {
    // URI base do serviço REST
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        // Cria um ResourceConfig que escaneia o pacote com seus recursos REST
        final ResourceConfig rc = new ResourceConfig()
                .packages("com.exemplo.resource")
                .register(SwaggerResource.class)
                .register(SwaggerConfig.class)
                .register(OpenApiResource.class);

        // Cria e retorna o servidor Grizzly HTTP usando a configuração
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        // Inicia o servidor
        final HttpServer server = startServer();
        System.out.println(String.format("Servidor Jersey iniciado em %s", BASE_URI));
        System.out.println("Documentação Swagger disponível em " + BASE_URI + "swagger");
        System.out.println("Pressione Enter para encerrar...");
        System.in.read();
        server.shutdownNow();
    }
}