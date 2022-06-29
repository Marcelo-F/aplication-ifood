package com.marcelo.ifood.cadastro;

import com.marcelo.ifood.cadastro.dto.RestauranteDTO;
import com.marcelo.ifood.cadastro.dto.RestauranteMapper;
import com.marcelo.ifood.cadastro.infra.ConstraintViolationResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @GET
    public List<RestauranteDTO> restauranteList() {
        Stream<Restaurante> restaurantes = Restaurante.streamAll();
        return restaurantes.map(r ->
                restauranteMapper.toRestauranteDTO(r)).collect(Collectors.toList());
    }

    @POST
    @Transactional
    @APIResponse(responseCode = "400", content = @Content(schema=@Schema(allOf = ConstraintViolationResponse.class)))
    @APIResponse(responseCode = "201", description ="Caso o restaurante seja cadastrado com sucesso")
    public Response adicionar(RestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaurante(dto);
        restaurante.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, RestauranteDTO dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);

        if (restauranteOp.isEmpty()) {
            throw new NotFoundException();
        }

        Restaurante restaurante = restauranteOp.get();

        restaurante.nome = dto.nomeFantasia;
        restaurante.persist();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);

        restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        });
    }

    //PRATOS

    @GET
    @Path("{idRestaurante}/pratos")
  //  @Tag(name="prato")
    public List<Restaurante> pratoList(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }

        return Prato.list("restaurante", restauranteOp.get());
    }

    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
  //  @Tag(name="prato")

    public Response adicionar(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }
        Prato prato = new Prato();
        prato.nome = dto.nome;
        prato.descricao = dto.descricao;
        prato.preco = dto.preco;
        prato.restaurante = restauranteOp.get();
        prato.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
  //  @Tag(name="prato")
    public Response adicionar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id, Prato dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }
        Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        Prato prato = pratoOp.get();
        prato.preco = dto.preco;
        prato.persist();
        return Response.status(Response.Status.CREATED).build();
    }


    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
   // @Tag(name="prato")
    public void delete(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id, Prato dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        pratoOp.ifPresentOrElse(Prato::delete, () -> {

            throw new NotFoundException();
        });
    }

}