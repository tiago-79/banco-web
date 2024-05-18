package br.ada.caixa.repository;

import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientePJRepository extends JpaRepository<ClientePJ, String> {

    List<ClientePJ> findAllByCnpjContainsIgnoreCase(String cnpj);
}
