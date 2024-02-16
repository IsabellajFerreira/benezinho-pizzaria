package br.com.benefrancis;

import br.com.benefrancis.domain.entity.Pizzaria;
import br.com.benefrancis.domain.entity.Produto;
import br.com.benefrancis.domain.entity.Sabor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "maria-db" );

        EntityManager manager = factory.createEntityManager();


      var manjericao = new Sabor(null, "Manjericao", "Deliciosa pizza de manjericao que foi plantado pelos mais renomados agricultores do Brasil");

        var frangoComCatupiry = new Sabor(null, "Frango com Catupiry", "O verdadeiro sabor do Catupiry original faz toda a diferença nesta pizza");



        Pizzaria p1 = new Pizzaria(null, "Dominus");


        var pizzaDeManjericao = new Produto(null, "Pizza", BigDecimal.valueOf(59.99), manjericao);
        var pizzaDefrangoComCatupiry = new Produto(null, "Pizza", BigDecimal.valueOf(79.99), frangoComCatupiry);



        manager.getTransaction().begin();
        manager.persist(manjericao);
        manager.persist(frangoComCatupiry);
        manager.persist( p1 );
        manager.persist(pizzaDeManjericao);
        manager.persist(pizzaDefrangoComCatupiry);
        manager.getTransaction().commit();


        System.out.println("PIZZARIA: " + p1 );
        System.out.println("SABOR: " + manjericao );
        System.out.println("SABOR: " + frangoComCatupiry );
        manager.close();
        factory.close();


    }
}