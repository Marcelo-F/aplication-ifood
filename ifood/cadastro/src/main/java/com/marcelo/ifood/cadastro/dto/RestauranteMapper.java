package com.marcelo.ifood.cadastro.dto;

import com.marcelo.ifood.cadastro.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="cdi")
public interface RestauranteMapper {

    @Mapping(target = "nome", source="nomeFantasia")
    Restaurante toRestaurante(RestauranteDTO dto);




    RestauranteDTO toRestauranteDTO(Restaurante restaurante);
}
