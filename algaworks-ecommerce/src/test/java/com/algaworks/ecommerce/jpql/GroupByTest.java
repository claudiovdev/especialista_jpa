package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void agremoruparResultado(){
        //Quantidade de produtos por categoria
        //String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id ";

        //Total de Venda por mês
        //String jpql = "select concat(year(p.dataCriacao), function('monthname', p.dataCriacao)), sum(p.total) from Pedido p " +
        //        " group by year(p.dataCriacao), month(p.dataCriacao)";

        //Total de vendas por categoria
        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join pro.categorias c group by c.id";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = query.getResultList();

        resultList.forEach(r -> System.out.println(r[0] + ", " + r[1]));
    }

    @Test
    public void condicionarAgrupamentoComHaving(){
        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join pro.categorias c group by c.id " +
                "having sum(ip.precoProduto) > 100";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = query.getResultList();

        Assert.assertFalse(resultList.isEmpty());
        resultList.forEach(resultado -> System.out.println(resultado[0] + ", " + resultado[1] + ", "+ resultado[3]));
    }
}
