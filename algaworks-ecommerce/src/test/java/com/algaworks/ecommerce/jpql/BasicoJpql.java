package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManangerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BasicoJpql extends EntityManangerTest {

    @Test
    public void selecionarAtributoParaRetorno(){
        String jpql = "select p.nome from Produto p";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql,String.class);
        List<String> resultList = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(resultList.get(0).getClass()));

        String jpqlCliente = "select c from Cliente c";

        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> clienteResultList = typedQueryCliente.getResultList();
        Assert.assertTrue(Cliente.class.equals(clienteResultList.get(0).getClass()));
    }

    @Test
    public void buscarPorIdentificador() {
        // entityManager.find(Pedido.class, 1)

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido);

//        List<Pedido> lista = typedQuery.getResultList();
//        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void diferencaEntreQueries(){
        String jpql = "select p from Pedido p where p.id = 1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedido1 = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido1);

        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        Assert.assertNotNull(pedido2);
    }
}
