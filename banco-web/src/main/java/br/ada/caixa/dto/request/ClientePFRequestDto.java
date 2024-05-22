package br.ada.caixa.dto.request;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class ClientePFRequestDto {

    @NotNull
    @NotBlank
    private String cpf;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private LocalDate dataNascimento;
//    private List<Conta> contas;
}
