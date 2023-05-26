package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoNativa(){
        String jpql = "select p from Pedido p where function('acima_media_faturamento', p.total) = 1";
        TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
        lista.forEach(obj -> System.out.println(obj));
    }
    @Test
    public void aplicarFuncaoColecao(){
        String jpql = "select size(p.itens) from Pedido p";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
        lista.forEach(size -> System.out.println(size));
    }
    @Test
    public void aplicarFuncaoNumero(){
        String jpql = "select abs(-10), mod(3,2), sqrt(9) from Pedido p";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
        lista.forEach(c -> System.out.println(c[0] + " " + c[1]));
    }
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
