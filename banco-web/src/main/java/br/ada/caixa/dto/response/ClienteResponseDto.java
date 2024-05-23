package br.ada.caixa.dto.response;

import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteResponseDto {
    private String documento;
    private StatusClienteEnum status;
    private String nome;
    private String tipo;
    private LocalDate dataCadastro;
    //private List<Conta> contas;
}
