package br.ada.caixa.entity;

import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_pessoa", discriminatorType = DiscriminatorType.STRING, length = 10)
public class Cliente {

    @Id
    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusClienteEnum status;

    private String nome;
    private LocalDate dataCadastro;

    //private List<Conta> contas;

}
