package br.ada.caixa.dto.request;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class ClientePFRequestDto {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
//    private List<Conta> contas;
}
