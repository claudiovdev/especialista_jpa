package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class BasicoCriteriaTest extends EntityManagerTest {

    @Test
    public void projetarOResultadoTupleDto(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoDTO> criteriaQuery = criteriaBuilder.createQuery(ProdutoDTO.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(criteriaBuilder.construct(ProdutoDTO.class,root.get("id"), root.get("nome")));

        TypedQuery<ProdutoDTO> query = entityManager.createQuery(criteriaQuery);
        List<ProdutoDTO> produtoDTOList = query.getResultList();

        Assert.assertFalse(produtoDTOList.isEmpty());

        produtoDTOList.forEach(produtoDTO -> System.out.println("Id: " + produtoDTO.getId() + " Nome: " + produtoDTO.getNome()));
    }
    @Test
    public void projetarOResultadoTuple(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(criteriaBuilder.tuple(root.get("id").alias("id"), root.get("nome").alias("nome")));

        TypedQuery<Tuple> query = entityManager.createQuery(criteriaQuery);
        List<Tuple> lista = query.getResultList();

        Assert.assertFalse(lista.isEmpty());

        lista.forEach(t -> System.out.println("Id: " + t.get("id") + ",  Nome: " + t.get("nome")));
    }
    @Test
    public void projetarOResultado(){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
    Root<Produto> root = criteriaQuery.from(Produto.class);

    criteriaQuery.multiselect(root.get("id"), root.get("nome"));

    TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
    List<Object[]> lista = query.getResultList();

    Assert.assertFalse(lista.isEmpty());

    lista.forEach(produto -> System.out.println("Id: " + produto[0] + ",  Nome: " + produto[1]));
    }

    @Test
    public void retornandoTodosProdutos(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        TypedQuery<Produto> query = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = query.getResultList();

        Assert.assertFalse(produtos.isEmpty());
        System.out.println(produtos.size());

    }
    @Test
    public void retornandoUmBigDecimal(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root.get("total"));

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));
        TypedQuery<BigDecimal> query = entityManager.createQuery(criteriaQuery);
        BigDecimal total = query.getSingleResult();

        Assert.assertEquals(new BigDecimal("2398.00"), total);
    }

    @Test
    public void selecionarUmAtributoParaRetorno(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root.get("cliente"));

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);

        Cliente cliente = query.getSingleResult();

        Assert.assertEquals("Fernando Medeiros", cliente.getNome());
    }

    @Test
    public void buscarPorIdentificador(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        Pedido pedido = typedQuery.getSingleResult();

        Assert.assertNotNull(pedido);
    }
}
