package br.ada.caixa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@DiscriminatorValue("PJ")
public class ClientePJ extends Cliente {

    @NotNull
    @NotBlank
    private String cnpj;

    @NotNull
    @NotBlank
    private String nomeFantasia;

    @NotNull
    @NotBlank
    private String razaoSocial;

}
