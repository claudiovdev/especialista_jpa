package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubqueriesTest extends EntityManagerTest {

    @Test
    public void pesquisarSubqueries(){


//        String jpql = "select p from Produto p " +
//                " where p.preco = (select max(preco) from Produto)";

//        String jpql = "select p from Pedido p " +
//                "where p.total > (select avg(total) from Pedido)";

        String jpql = "select c from Cliente c where " +
                " 100 < (select sum(p.total) from c.pedidos p)";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> resultList = query.getResultList();
        Assert.assertFalse(resultList.isEmpty());
        resultList.forEach(obj -> System.out.println("Id: " + obj.getId() + " Nome: " + obj.getNome()));
    }
}
