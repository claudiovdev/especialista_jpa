package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManangerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConsultandoRegistrosTest extends EntityManangerTest {


    @Test
    public void buscarPorIdentificador(){
        Produto produto = entityManager.find(Produto.class, 1);

        assertNotNull(produto);
        assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void atualizarReferencia(){
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Boneco");

        entityManager.refresh(produto);

        assertEquals("Kindle", produto.getNome());


    }
}
