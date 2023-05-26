package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoConcat(){
        String jpql = "select c.nome, concat('Categoria: '  + c.nome) from Categoria c";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
        lista.forEach(c -> System.out.println(c[0] + " " + c[1]));
    }

    @Test
    public void aplicarFuncaoLength(){
        String jpql = "select c.nome, length(c.nome) from Categoria c";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
        lista.forEach(c -> System.out.println(c[0] + " " + c[1]));
    }

    @Test
    public void aplicarFuncaoSubString(){
        String jpql = "select c.nome, substring(c.nome,1 ,2) from Categoria c";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
        lista.forEach(c -> System.out.println(c[0] + " " + c[1]));
    }

    @Test
    public void aplicarFuncaoData(){
        String jpql = "select current_date, current_time, current_timestamp from Pedido p";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
        lista.forEach(r -> System.out.println(r[0] + " | " + r[1] + " | " + r[2]));
    }
}
