package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.List;

public class OperadoresLogicosTest extends EntityManagerTest {

    @Test
    public void usarOperadores(){
            String jpql = "select p from Pedido p where p.total > 100 and p.status = 'AGUARDANDO' and p.cliente.id = 1";
        TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
