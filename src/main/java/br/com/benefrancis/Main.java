package br.com.benefrancis;

import br.com.benefrancis.domain.entity.Opcional;
import br.com.benefrancis.domain.entity.Pizzaria;
import br.com.benefrancis.domain.entity.Produto;
import br.com.benefrancis.domain.entity.Sabor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        var db = LocalDate.now().getDayOfWeek().equals(DayOfWeek.FRIDAY) ? "maria-db" : "fiap";

        EntityManagerFactory factory = Persistence.createEntityManagerFactory( db );
        EntityManager manager = factory.createEntityManager();

        var manjericao = new Sabor( null, "Manjericao", "Deliciosa pizza de manjericão que fora plantado pelos mais renomados agricultores do Brasil" );
        var frangoComCatupiri = new Sabor( null, "Frango com Catupiri", "O verdadeiro sabor do Catupiri Original faz toda a diferença nesta pizza" );

        Pizzaria pizzaria = new Pizzaria( null, "Dominus" );

       // var pizzaDeManjericao = new Produto( null, "Pizza", BigDecimal.valueOf( 59.99 ), manjericao );
       // var pizzaDeFrangoComCatupiri = new Produto( null, "Pizza", BigDecimal.valueOf( 79.99 ), frangoComCatupiri );


        var bordaDeCatupiri = Opcional.builder().nome("Borda de Catupiri").preco( 9.99 ).build();

        var bordaPaozinho = Opcional.builder().nome("Borda paozinho de calabresa e Catupiri").preco( 19.99 ).build();

        var cocaCola = Opcional.builder().nome("Coca de 2 Litros").preco( 19.99 ).build();



        Set<Opcional> opicionaisDaPrimeiraPizza = new LinkedHashSet<>();
        opicionaisDaPrimeiraPizza.add( bordaDeCatupiri );
        opicionaisDaPrimeiraPizza.add( cocaCola );

        Set<Opcional> opicionaisDaSegundaPizza = new LinkedHashSet<>();
        opicionaisDaSegundaPizza.add( bordaPaozinho );
        opicionaisDaSegundaPizza.add( cocaCola );


        var pizzaDeManjericao =  Produto.builder().nome("Pizza").sabor(manjericao).preco(BigDecimal.valueOf(59.99)).opcionais( opicionaisDaPrimeiraPizza ).build();

        var pizzaDeFrangoComCatupiri =  Produto.builder().nome("Pizza").sabor(frangoComCatupiri).preco(BigDecimal.valueOf(19.99)).opcionais(opicionaisDaSegundaPizza).build();

        manager.getTransaction().begin();

        manager.persist( pizzaria );

        manager.persist( pizzaDeManjericao );
        manager.persist( pizzaDeFrangoComCatupiri );


        manager.getTransaction().commit();

        System.out.println( "PIZZARIA: " + pizzaria );

        System.out.println( "PIZZA:  " + pizzaDeManjericao );

        System.out.println( "PIZZA:  " + pizzaDeFrangoComCatupiri );

        manager.close();
        factory.close();
    }
}