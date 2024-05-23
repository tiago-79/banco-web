package br.ada.caixa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private transient BigDecimal saldo;

    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
