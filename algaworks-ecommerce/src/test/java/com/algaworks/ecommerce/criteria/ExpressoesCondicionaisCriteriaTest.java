package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;


public class ExpressoesCondicionaisCriteriaTest extends EntityManagerTest {

    @Test
    public void usarExpressaoCondicionalLike(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("nome"), "%a%"));

        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        List<Cliente> resultList = query.getResultList();
        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void usarIsNull(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(root.get("foto").isNull());
        TypedQuery<Produto> query = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = query.getResultList();

        Assert.assertFalse(produtos.isEmpty());
    }

    @Test
    public void usarIsNull2(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.isNull(root.get("foto")));
        TypedQuery<Produto> query = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = query.getResultList();

        Assert.assertFalse(produtos.isEmpty());
    }

    @Test
    public void usarMaiorMenor(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("preco"), 799));
        TypedQuery<Produto> query = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = query.getResultList();

        produtos.forEach(produto -> System.out.println(produto.getNome() + " " + "Preço " + produto.getPreco()));

        Assert.assertFalse(produtos.isEmpty());
    }

    @Test
    public void usarBetween(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.between(root.get("total"), new BigDecimal("499"), new BigDecimal("2398")));

        TypedQuery<Pedido> query = entityManager.createQuery(criteriaQuery);
        List<Pedido> pedidos = query.getResultList();

        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void usarDiferente(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.notEqual(root.get("total"), "499"));

        TypedQuery<Pedido> query = entityManager.createQuery(criteriaQuery);
        List<Pedido> pedidos = query.getResultList();

        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void usarOperadores(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("total"), "499"),
                criteriaBuilder.equal(root.get("status"), StatusPedido.PAGO));
        TypedQuery<Pedido> query = entityManager.createQuery(criteriaQuery);
        List<Pedido> pedidos = query.getResultList();
        var totalDePedidos = pedidos.size();
        System.out.println("Quantidade de pedidos para este resultado: " + totalDePedidos);

        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void usarOperadoresOr(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.or(
                criteriaBuilder.equal(root.get("status"), StatusPedido.PAGO),
                criteriaBuilder.equal(root.get("status"), StatusPedido.AGUARDANDO)
        ),
                criteriaBuilder.greaterThan(root.get("total"), "499"));
        TypedQuery<Pedido> query = entityManager.createQuery(criteriaQuery);
        List<Pedido> pedidos = query.getResultList();
        var totalDePedidos = pedidos.size();
        System.out.println("Quantidade de pedidos para este resultado: " + totalDePedidos);

        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void usarOperadorOrderBy(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.select(root);

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));

        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        List<Cliente> clientes = query.getResultList();
        clientes.forEach(cliente -> System.out.println("Nome: " + cliente.getNome()));

        Assert.assertFalse(clientes.isEmpty());

    }
}
