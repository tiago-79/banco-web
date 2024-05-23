package br.ada.caixa.dto.response;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientePJResponseDto {
    private String cnpj;
    private String nome;
    private String nomeFantasia;
    private String razaoSocial;
    private LocalDate dataCadastro;
    private StatusClienteEnum status;
    private List<ContaResponseDto> contas;
}
