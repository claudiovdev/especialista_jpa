package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

public class ConsultasNativasTest extends EntityManagerTest {

    @Test
    public void executarSQL(){
        String sql = "select * from produto";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(produto -> System.out.println(
                String.format("Produto -> primeiroAtributo: %s, SegundoAtributo: %s", produto[0], produto[1])
        ));
    }

    @Test
    public void RetornandoEntidade(){
        String sql = "select id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto from produto";
        Query query = entityManager.createNativeQuery(sql, Produto.class);
        List<Produto> lista = query.getResultList();

        lista.stream().forEach(produto -> System.out.println(
                String.format("Produto -> primeiroAtributo: %s, SegundoAtributo: %s", produto.getId(), produto.getNome())
        ));
    }

    @Test
    public void consultaComParametros(){
        String sql = "select  id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto " +
                "from produto p where p.id = :id";
        Query query = entityManager.createNativeQuery(sql, Produto.class);
        query.setParameter("id", 1);

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(produto -> System.out.println(
                String.format("Produto -> primeiroAtributo: %s, SegundoAtributo: %s", produto.getId(), produto.getNome())
        ));
    }
}


