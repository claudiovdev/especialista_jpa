package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManangerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OperacoesComTransacaoTest extends EntityManangerTest {

    @Test
    public  void atualizarObjeto(){
        Produto produto = new Produto();

        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        assertEquals("Kindle Paperwhite", produto.getNome());
    }

    @Test
    public  void atualizarObjetoGerenciado(){
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2º Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoExistente = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoExistente);
        assertEquals("Kindle Paperwhite 2º Geração", produto.getNome());
    }

    @Test
    public void removendoObjeto(){

        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto verificaondoProduto = entityManager.find(Produto.class, 3);
        assertNull(verificaondoProduto);
    }


    @Test
    public void inserindoPrimeiroObjeto(){

        Produto produto = new Produto();

        produto.setNome("Caneta");
        produto.setDescricao("Caneta azul Bic");
        produto.setPreco(new BigDecimal(1.5));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoExistente = entityManager.find(Produto.class, produto.getId());

        assertNotNull(produtoExistente);

    }

    @Test
    public void inserindoObjetoComMerge(){

        Produto produto = new Produto();

        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        Produto produtoSalvo = entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());
        Assert.assertNotNull(produtoVerificacao);

    }

    @Test
    public void mostrarDiferencaPersisteMerge(){

        Produto produtoPersiste = new Produto();

        produtoPersiste.setNome("Smarphone One Plus");
        produtoPersiste.setDescricao("O melhor já produzido");
        produtoPersiste.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersiste);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoExistente = entityManager.find(Produto.class, produtoPersiste.getId());
        assertNotNull(produtoExistente);

        Produto produtoMerge = new Produto();

        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor já produzido");
        produtoMerge.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoMergeExistente = entityManager.find(Produto.class, produtoMerge.getId());

        assertNotNull(produtoMergeExistente);



    }

}
