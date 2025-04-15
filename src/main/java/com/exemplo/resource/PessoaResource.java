package com.exemplo.resource;

import com.exemplo.model.Pessoa;
import com.exemplo.service.PessoaService;
import com.exemplo.repository.PessoaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/pessoas")
@Tag(name = "Pessoas", description = "Operações relacionadas a pessoas")
public class PessoaResource {
    private final PessoaService pessoaService;
    private final EntityManager entityManager;

    public PessoaResource() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("apiPU");
        this.entityManager = emf.createEntityManager();
        PessoaRepository repository = new PessoaRepository(entityManager);
        this.pessoaService = new PessoaService(repository);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Listar todas as pessoas", description = "Retorna uma lista com todas as pessoas cadastradas", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pessoa.class)))
    })
    public List<Pessoa> getPessoas() {
        return pessoaService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Buscar pessoa por ID", description = "Retorna uma pessoa específica pelo seu ID", responses = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public Response getPessoa(
            @Parameter(description = "ID da pessoa", required = true) @PathParam("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        if (pessoa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pessoa).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Criar nova pessoa", description = "Cria uma nova pessoa com os dados fornecidos", responses = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso")
    })
    public Response createPessoa(
            @Parameter(description = "Dados da nova pessoa", required = true) Pessoa pessoa) {
        pessoaService.save(pessoa);
        return Response.status(Response.Status.CREATED).entity(pessoa).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa existente", responses = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public Response updatePessoa(
            @Parameter(description = "ID da pessoa", required = true) @PathParam("id") Long id,
            @Parameter(description = "Novos dados da pessoa", required = true) Pessoa pessoa) {
        Pessoa existingPessoa = pessoaService.findById(id);
        if (existingPessoa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        pessoa.setId(id);
        pessoaService.update(pessoa);
        return Response.ok(pessoa).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir pessoa", description = "Remove uma pessoa existente pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public Response deletePessoa(
            @Parameter(description = "ID da pessoa", required = true) @PathParam("id") Long id) {
        Pessoa pessoa = pessoaService.findById(id);
        if (pessoa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        pessoaService.delete(pessoa);
        return Response.noContent().build();
    }
}