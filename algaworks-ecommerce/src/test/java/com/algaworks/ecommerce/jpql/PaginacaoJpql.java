package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PaginacaoJpql extends EntityManagerTest {

    @Test
    public void paginarResultado(){
        String jpql = "select c from Categoria c order by c.nome ";
        TypedQuery<Categoria> query = entityManager.createQuery(jpql, Categoria.class);
        query.setFirstResult(0);
        query.setMaxResults(2);
        List<Categoria> lista = query.getResultList();

        Assert.assertFalse(lista.isEmpty());
        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
    }
}
