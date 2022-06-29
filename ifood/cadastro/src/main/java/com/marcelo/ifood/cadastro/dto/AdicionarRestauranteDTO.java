package com.marcelo.ifood.cadastro.dto;

import com.marcelo.ifood.cadastro.Restaurante;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AdicionarRestauranteDTO {

    @NotEmpty
    @NotNull
    public String proprietario;

    @Pattern(regexp ="[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}")
    @NotNull
    public String cnpj;

    @Size(min =3, max =30)
    public String nomeFantasia;

    public LocalizacaoDto localizacao;


    public boolean isValid(ConstraintValidatorContext constraintValidatorContext){
        constraintValidatorContext.disableDefaultConstraintViolation();

        if(Restaurante.find("cnpj", cnpj).count() > 0){
            constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
                    .addPropertyNode("cnpj")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }



}
