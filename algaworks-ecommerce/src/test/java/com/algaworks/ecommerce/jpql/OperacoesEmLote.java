package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class OperacoesEmLote extends EntityManagerTest {

    @Test
    public void     inserirEmLote(){

        int limite = 10;
        InputStream in = OperacoesEmLote.class.getClassLoader()
                .getResourceAsStream("produtos/importar.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        entityManager.getTransaction().begin();
        int contadorDeInsercoes = 0;

        for(String linha: reader.lines().collect(Collectors.toList())){
            if(linha.isBlank()){
                continue;
            }
            String[] produtoColuna = linha.split(";");
            Produto produto = new Produto();
            produto.setNome(produtoColuna[0]);
            produto.setDescricao(produtoColuna[1]);
            produto.setPreco(new BigDecimal(produtoColuna[2]));
            produto.setDataCriacao(LocalDateTime.now());
            entityManager.persist(produto);

            if (++contadorDeInsercoes == limite){
                entityManager.flush();
                entityManager.clear();

                contadorDeInsercoes = 0;

                System.out.println("--------------");
            }
        }
        entityManager.getTransaction().commit();
    }
}
