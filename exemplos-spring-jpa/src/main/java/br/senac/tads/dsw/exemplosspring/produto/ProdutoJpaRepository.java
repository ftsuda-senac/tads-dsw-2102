/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.produto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ftsuda
 */
@Repository
public class ProdutoJpaRepository implements ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Produto> findAll(int offset, int quantidade) {
//        TypedQuery<Produto> jpqlQuery =
//                em.createQuery("SELECT p FROM Produto p", Produto.class)
//                        .setFirstResult(offset)
//                        .setMaxResults(quantidade);
        TypedQuery<Produto> jpqlQuery
                = em.createNamedQuery("Produto.findAll", Produto.class)
                        .setFirstResult(offset)
                        .setMaxResults(quantidade);
        List<Produto> resultados = jpqlQuery.getResultList();
        return resultados;
    }

    @Override
    public List<Produto> findByCategoria(List<Integer> idsCat, int offset, int quantidade) {
        TypedQuery<Produto> jpqlQuery
                = em.createNamedQuery("Produto.findAllByCategoriaIds", Produto.class)
                        .setParameter("idsCat", idsCat)
                        .setFirstResult(offset)
                        .setMaxResults(quantidade);
        return jpqlQuery.getResultList();
    }
    
    // OBS: FUNÇÃO PADRÃO QUE SÓ FUNCIONA COM open-in-view=TRUE NO ARQUIVO application.properties
    // SE CONFIGURAR open-in-view=false NO ARQUIVO application.properties, IRÁ OCORRER UM ERRO 
    @Override
    public Produto findById(Long id) {
        TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findById", Produto.class)
                .setParameter("idProd", id);
        return jpqlQuery.getSingleResult();
    }

    // OBS: PARA TESTAR, CONFIGURAR open-in-view=false NO ARQUIVO application.properties
    public Produto findByIdComFetch(Long id) {
        TypedQuery<Produto> jpqlQuery
                = em.createNamedQuery("Produto.findByIdComFetch", Produto.class)
                        .setParameter("idProd", id);
        Produto prod = jpqlQuery.getSingleResult();
        return prod;
    }

    // OBS: PARA TESTAR, CONFIGURAR open-in-view=false NO ARQUIVO application.properties
    public Produto findByIdComEntityGraph(Long id) {
        EntityGraph<?> entityGraph = em.createEntityGraph("Produto.graphCategoriasImagens");

        TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findById", Produto.class)
                .setParameter("idProd", id)
                .setHint("javax.persistence.loadgraph", entityGraph);
        return jpqlQuery.getSingleResult();
    }

    public Produto findByIdCriteriaApi(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = cb.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);
        criteriaQuery.where(cb.equal(root.get("id"), id));
        TypedQuery<Produto> query = em.createQuery(criteriaQuery);
        Produto p = query.getSingleResult();
        return p;
    }

    @Transactional
    @Override
    public Produto save(Produto p) {
        if (p.getId() == null) {
            // INCLUIR NOVO PRODUTO
            em.persist(p);
        } else {
            // ATUALIZAR PRODUTO EXISTENTE
            p = em.merge(p);
        }
        return p;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        // CONSULTA PARA OBJETO FICAR ASSOCIADO (ATTACHED) NO ENTITY MANAGER
        Produto p = em.find(Produto.class, id);
        em.remove(p);
    }

}
