/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CategoriaJpaRepository implements CategoriaRepository {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Categoria> findAll() {
        TypedQuery<Categoria> jpqlQuery = 
                em.createQuery("SELECT c FROM Categoria c", Categoria.class);
        List<Categoria> resultados = jpqlQuery.getResultList();
        return resultados;
    }

    @Override
    public Categoria findById(Integer id) {
        TypedQuery<Categoria> jpqlQuery = 
                em.createQuery("SELECT c FROM Categoria c WHERE c.id = :idCat", Categoria.class);
        jpqlQuery.setParameter("idCat", id);
        Categoria cat = jpqlQuery.getSingleResult();
        return cat;
    }

    @Transactional
    @Override
    public Categoria save(Categoria cat) {
        if (cat.getId() == null) {
            // Incluir nova categoria
            em.persist(cat);
        } else {
            // Atualizar categoria existente
            cat = em.merge(cat);
        }
        return cat;
    }
    
}
