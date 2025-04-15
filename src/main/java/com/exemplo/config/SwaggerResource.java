package com.exemplo.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;

@Path("/swagger")
public class SwaggerResource {

    @GET
    public Response redirectToUi() {
        return Response.temporaryRedirect(java.net.URI.create("/api/swagger/index.html")).build();
    }

    @GET
    @Path("/{path:.*}")
    public Response serveSwaggerUi(@PathParam("path") String path) {
        if (path == null || path.isEmpty() || path.equals("index.html")) {
            path = "index.html";

            // Carregar o HTML do Swagger UI e substituir a URL da OpenAPI
            InputStream is = getClass().getResourceAsStream("/META-INF/resources/webjars/swagger-ui/4.18.2/" + path);
            if (is == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            try {
                String html = new String(is.readAllBytes());
                html = html.replace("https://petstore.swagger.io/v2/swagger.json", "/api/openapi.json");
                return Response.ok(html).header("Content-Type", "text/html").build();
            } catch (Exception e) {
                return Response.serverError().entity(e.getMessage()).build();
            }
        }

        // Servir outros recursos estáticos do Swagger UI
        InputStream is = getClass().getResourceAsStream("/META-INF/resources/webjars/swagger-ui/4.18.2/" + path);
        if (is == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        String contentType = "text/plain";
        if (path.endsWith(".html"))
            contentType = "text/html";
        else if (path.endsWith(".css"))
            contentType = "text/css";
        else if (path.endsWith(".js"))
            contentType = "application/javascript";
        else if (path.endsWith(".png"))
            contentType = "image/png";

        return Response.ok(is).header("Content-Type", contentType).build();
    }
}