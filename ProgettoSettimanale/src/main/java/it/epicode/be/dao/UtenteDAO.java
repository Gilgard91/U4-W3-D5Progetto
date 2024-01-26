package it.epicode.be.dao;

import it.epicode.be.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UtenteDAO {

    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(utente);
            t.commit();
            System.out.println("Utente - " + utente.getNome() + " " + utente.getCognome() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(Utente utente) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.remove(utente);
            t.commit();
            System.out.println("Utente - " + utente.getNome() + " " + utente.getCognome() + " - rimosso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
