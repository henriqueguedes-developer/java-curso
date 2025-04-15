// src/main/java/com/exemplo/config/SwaggerConfig.java
package com.exemplo.config;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

public class SwaggerConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(OpenApiResource.class);
        return resources;
    }
}
