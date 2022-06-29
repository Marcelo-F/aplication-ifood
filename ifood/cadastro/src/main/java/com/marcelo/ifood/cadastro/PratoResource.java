package com.marcelo.ifood.cadastro;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @GET
    public List<Prato>  pratoList(){
        return Prato.listAll();
    }

    @POST
    @Transactional
    public Response adicionar(Prato dto){
        dto.persist();
        return Response.status(Response.Status.CREATED).build();
    }

   @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id")Long id, Prato dto){
        Optional<Prato> pratoOptional = Prato.findByIdOptional(id);
        if(pratoOptional.isEmpty()){
            throw new NotFoundException();
        }

        Prato prato = pratoOptional.get();
        prato.nome = dto.nome;
        prato.persist();

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public  void delete(@PathParam("id") Long id){
        Optional<Prato> pratoOptional = Prato.findByIdOptional(id);

        pratoOptional.ifPresentOrElse(Prato::delete, () ->{
            throw new NotFoundException();
        });
    }



}
