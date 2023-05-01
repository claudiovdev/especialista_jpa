package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManangerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class MapeandoEnumeracoesTest extends EntityManangerTest {

    @Test
    public void testarEnum(){
        Cliente cliente = new Cliente();
        cliente.setNome("Vinicius Magalhães");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente validandoCliente = entityManager.find(Cliente.class, 4);
        Assert.assertNotNull(validandoCliente);
    }
}
