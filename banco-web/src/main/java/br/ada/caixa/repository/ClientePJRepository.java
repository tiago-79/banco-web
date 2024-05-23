package br.ada.caixa.repository;

import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientePJRepository extends JpaRepository<ClientePJ, String> {

    List<ClientePJ> findAllByCnpjContainsIgnoreCase(String cnpj);

    @Query("select c from ClientePJ c " +
            "where(:cnpj is null or c.cnpj like (%:cnpj%) ) " +
            "and (:nomeFantasia is null or upper(c.nomeFantasia) like (%:nomeFantasia%) ) ")
    List<ClientePJ> pesquisar(@Param("cnpj") String cnpj, @Param("nomeFantasia") String nomeFantasia);

}
