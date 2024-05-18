package br.ada.caixa.dto.filter;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientePFFilterDto {
    private String cpf;


}
