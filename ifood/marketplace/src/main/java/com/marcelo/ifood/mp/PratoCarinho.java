package com.marcelo.ifood.mp;

import com.marcelo.ifood.cadastro.Restaurante;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class PratoCarinho {

    public String nome;

    public String prato;
}
