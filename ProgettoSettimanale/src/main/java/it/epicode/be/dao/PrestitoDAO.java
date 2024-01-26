package it.epicode.be.dao;

import it.epicode.be.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrestitoDAO {

    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(prestito);
            t.commit();
            System.out.println("Prestito - " + prestito.getPrestitoId() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(Prestito prestito) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.remove(prestito);
            t.commit();
            System.out.println("Prestito - " + prestito.getPrestitoId() + " - rimosso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Prestito> getPrestitoByUtente(long id){
        TypedQuery<Prestito> list = em.createNamedQuery("get_prestiti_by_utente", Prestito.class);
        list.setParameter("id", id);
        return list.getResultList();
    }

    public List<Prestito> getPrestitiScaduti(){
        TypedQuery<Prestito> list = em.createNamedQuery("get_prestiti_scaduti", Prestito.class);
        return list.getResultList();
    }

}
