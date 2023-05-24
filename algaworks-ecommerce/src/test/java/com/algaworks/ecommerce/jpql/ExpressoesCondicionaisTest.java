package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void usarExpressaoCondicionalLike(){
        String jpql = "select c from Cliente c where c.nome like concat(:nome, '%')";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("nome", "Fernando");

        List<Object[]> resultList = query.getResultList();
        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void usarNull(){
        String jpql = "select p from Produto p where p.foto is null";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> resultList = query.getResultList();
        Assert.assertFalse(resultList.isEmpty());
    }


    @Test
    public void usarEmpty(){
        String jpql = "select p from Produto p where p.categorias is empty";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = query.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarMaiorMenor(){
        String jpql = "select p from Produto p where p.preco >= :precoInicial and p.preco <= :precoFinal";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("precoInicial", new BigDecimal(400));
        query.setParameter("precoFinal", new BigDecimal(1500));
        List<Produto> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarMaiorMenorComData(){
        String jpql = "select p from Produto p where p.dataCriacao > :data";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("data", LocalDateTime.now().minusDays(2));

        List<Produto> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarBetween(){
        String jpql = "select p from Produto p where p.preco between :precoInicial and :precoFinal";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("precoInicial", new BigDecimal(400));
        query.setParameter("precoFinal", new BigDecimal(1500));
        List<Produto> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarDiferente(){
        String jpql = "select p from Produto p where p.preco <> 100";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = query.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
