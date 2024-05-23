package br.ada.caixa.entity;

import br.ada.caixa.entity.enums.StatusClienteEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_pessoa", discriminatorType = DiscriminatorType.STRING, length = 10)
public class Cliente {

    @Id
    @NotNull
    @NotBlank
    private String documento;

    @Enumerated(EnumType.STRING)
    private StatusClienteEnum status;

    @NotNull
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @NotNull
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Conta> contas;

}
