package com.marcelo.ifood.cadastro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
public class LocalizacaoDto {


    public Double latitude;

    public Double longitude;
}
