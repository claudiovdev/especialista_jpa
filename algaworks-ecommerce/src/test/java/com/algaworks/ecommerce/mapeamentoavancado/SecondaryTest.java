package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManangerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class SecondaryTest extends EntityManangerTest {

    @Test
    public void salvarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Vinicius Silva");
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente verificarCliente = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(verificarCliente.getSexo());
    }
}
