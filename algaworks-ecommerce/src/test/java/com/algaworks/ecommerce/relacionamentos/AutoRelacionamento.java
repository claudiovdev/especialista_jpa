package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManangerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoRelacionamento extends EntityManangerTest {

    @Test
    public void verificarRelacionamento(){

        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrônicos");

        Categoria categoria = new Categoria();
        categoria.setNome("Celulares");
        categoria.setCategoriaPaiId(categoriaPai);

        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria verificandoCategoria = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(verificandoCategoria.getCategoriaPaiId());

    }
}
