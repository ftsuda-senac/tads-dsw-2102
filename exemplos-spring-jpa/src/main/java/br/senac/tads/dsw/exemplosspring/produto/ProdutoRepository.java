package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository
        extends JpaRepository<Produto, Long>{

    //List<Produto> findByCategoria(List<Integer> idsCat, int offset, int quantidade);
    Page<Produto> findDistinctByCategorias_IdIn(List<Integer> idsCat, Pageable page);
    
    List<Produto> findByNome(String nome);
    
    Page<Produto> findByNome(String nome, Pageable page);
    
    Page<Produto> findByNomeIgnoreCase(String nome, Pageable page);
    
    Page<Produto> findByNomeIgnoreCaseContaining(String nome, Pageable page);
    
    // USANDO JPQL COM Spring Data JPA
    @Query("SELECT p FROM Produto p WHERE lower(p.nome) LIKE lower('%'||:termoBusca||'%')")
    Page<Produto> buscarPorNomeJpql(@Param("termoBusca") String nome, Pageable page);
    
    // USANDO SQL NATIVO COM Spring Data JPA
    @Query(value = "SELECT * FROM PRODUTO WHERE lower(nome) LIKE lower('%'||:termoBusca||'%')",
            nativeQuery = true)
    Page<Produto> buscarPorNomeSqlNativo(@Param("termoBusca") String nome, Pageable page);

    //void deleteById(Long id);

}
