package com.marcelo.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="restaurante")
@Getter
@Setter
public class Restaurante extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String proprietario;

    public String cnpj;

    public String nome;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    public Localizacao localizacao;

    @CreationTimestamp
    public Date dataCriacao;

    @UpdateTimestamp
    public Date dataAtualizacao;

    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao getLocalizacao() {
        return localizacao;
    }


}
