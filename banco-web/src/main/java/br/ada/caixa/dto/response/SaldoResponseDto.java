package br.ada.caixa.dto.response;

import java.math.BigDecimal;

public class SaldoResponseDto {

    private String numeroConta;
    private BigDecimal saldo;

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
