package br.ada.caixa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@DiscriminatorValue("PF")
public class ClientePF extends Cliente {

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

}
