package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ClientePJRequestDto {

    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
//    private List<Conta> contas;
}
