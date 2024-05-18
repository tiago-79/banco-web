package br.ada.caixa.dto.response;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientePFResponseDto {
    private String cpf;
    private String nome;
    private LocalDate dataCadastro;
    private LocalDate dataNascimento;
    private StatusClienteEnum status;
    private List<Conta> contas;
}
