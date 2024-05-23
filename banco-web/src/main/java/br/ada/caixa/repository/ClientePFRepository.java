package br.ada.caixa.repository;

import br.ada.caixa.entity.ClientePF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientePFRepository extends JpaRepository<ClientePF, String> {

    List<ClientePF> findAllByCpfContainsIgnoreCase(String cpf);

    // Didático
    @Query("select c from ClientePF c " +
        "where(:cpf is null or c.cpf like (%:cpf%) ) " +
        "and (:nome is null or upper(c.nome) like (%:nome%) ) ")
    List<ClientePF> pesquisar(@Param("cpf") String cpf, @Param("nome") String nome);

    // Utilizado em projetos
    @Query("select c from ClientePF c " +
            "where(:cpf is null or c.cpf like (%:cpf%) ) " +
            "and (:nome is null or upper(c.nome) like (%:nome%) ) ")
    Page<ClientePF> pesquisarPage(@Param("cpf") String cpf,
                                  @Param("nome") String nome,
                                  Pageable pageable);
}
