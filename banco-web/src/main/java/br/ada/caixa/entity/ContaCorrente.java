package br.ada.caixa.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class ContaCorrente extends Conta {

}
