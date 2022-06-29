package com.marcelo.ifood.mp;

import com.marcelo.ifood.cadastro.Restaurante;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class PratoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nome;

    public String descricao;

    @ManyToOne
    public Restaurante restaurante;

    public BigDecimal preco;


    public static Multi<PratoDto> findAll(PgPool pgPool){
       Uni<RowSet<Row>> preparedQuery = pgPool.preparedQuery("Select * from prato");
        preparedQuery.onItem().prodic(roSet -> Multi.createFrom()
                .items() ->{
            return null;
        })
    }
}
