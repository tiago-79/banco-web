package br.ada.caixa.repository;

import br.ada.caixa.entity.ClientePF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientePFRepository extends JpaRepository<ClientePF, String> {

    List<ClientePF> findAllByCpfContainsIgnoreCase(String cpf);
}
