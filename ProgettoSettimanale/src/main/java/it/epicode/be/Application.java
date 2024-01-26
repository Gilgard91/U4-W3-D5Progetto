package it.epicode.be;

import com.github.javafaker.Faker;
import it.epicode.be.dao.CatalogoDAO;
import it.epicode.be.dao.PrestitoDAO;
import it.epicode.be.dao.UtenteDAO;
import it.epicode.be.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    private static LocalDate randomLocalDate(Faker faker) {
        Date randomDate = faker.date().birthday();
        return randomDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(Locale.ITALY);
        CatalogoDAO catalogoDAO = new CatalogoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);
        Periodicita[] periodicita = Periodicita.values();
        Periodicita periodicitaRandom = periodicita[new Random().nextInt(periodicita.length)];
        LocalDate dataDiNascitaRandom = randomLocalDate(faker);


        // ******************** SALVATAGGIO LIBRI & RIVISTE ************************

        Libro libro1 = new Libro(faker.book().title(), faker.number().numberBetween(1980, 2023), faker.number().numberBetween(80, 300), faker.book().author(), faker.book().genre());
        Libro libro2 = new Libro(faker.book().title(), faker.number().numberBetween(1980, 2023), faker.number().numberBetween(80, 300), faker.book().author(), faker.book().genre());
//        catalogoDAO.save(libro1);
//        catalogoDAO.save(libro2);


        Rivista rivista1 = new Rivista(faker.book().title(), faker.number().numberBetween(2020, 2024), faker.number().numberBetween(15, 40), periodicitaRandom);
//        catalogoDAO.save(rivista1);

        // ******************** SALVATAGGIO UTENTI & PRESTITI ************************

        Utente utente1 = new Utente(faker.name().firstName(), faker.name().lastName(), dataDiNascitaRandom);
//        utenteDAO.save(utente1);

        Prestito prestito1 = new Prestito(utente1, libro1, LocalDate.now(), LocalDate.now().plusDays(30), null);
        Prestito prestito2 = new Prestito(utente1, rivista1, LocalDate.now(), LocalDate.now().plusDays(30), LocalDate.now().plusDays(50));
        Prestito prestito3 = new Prestito(utente1, libro2, LocalDate.now(), LocalDate.now().plusDays(30), LocalDate.now().plusDays(15));
//        prestitoDAO.save(prestito1);
//        prestitoDAO.save(prestito2);
//        prestitoDAO.save(prestito3);


        // ******************** METODI DI RICERCA ************************

        System.out.println("***RICERCA ELEMENTO PER ISBN***");
        catalogoDAO.getByISBN(30).forEach(System.out::println);
        System.out.println("***RICERCA ELEMENTO PER ANNO DI PUBBLICAZIONE***");
        catalogoDAO.getByAnnoDiPubblicazione(2021).forEach(System.out::println);
        System.out.println("***RICERCA ELEMENTO PER AUTORE***");
        catalogoDAO.getByAutore("Dr. Penelope Sanna").forEach(System.out::println);
        System.out.println("***RICERCA ELEMENTO PER TITOLO***");
        System.out.println(catalogoDAO.getByTitolo("When th"));
        System.out.println("***RICERCA PRESTITO PER UTENTE***");
        prestitoDAO.getPrestitoByUtente(45).forEach(System.out::println);
        System.out.println("***RICERCA PRESTITI SCADUTI O NON RESTITUITI***");
        prestitoDAO.getPrestitiScaduti().forEach(System.out::println);




        em.close();
        emf.close();
    }
}
