package br.ada.caixa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("PF")
public class ClientePF extends Cliente {

    private String cpf;
    private LocalDate dataNascimento;

}
