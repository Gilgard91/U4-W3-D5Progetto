package it.epicode.be.dao;

import it.epicode.be.entities.Catalogo;
import it.epicode.be.entities.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CatalogoDAO {
    private EntityManager em;

    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogo catalogo) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(catalogo);
            t.commit();
            System.out.println("Catalogo - " + catalogo.getTitolo() + " - creato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(Catalogo catalogo) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.remove(catalogo);
            t.commit();
            System.out.println("Catalogo - " + catalogo.getTitolo() + " - eliminato!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Catalogo> getByISBN(long isbn){
        TypedQuery<Catalogo> list = em.createNamedQuery("get_by_ISBN", Catalogo.class);
        list.setParameter("isbn", isbn);
        return list.getResultList();
    }

    public List<Catalogo> getByAnnoDiPubblicazione(int anno){
        TypedQuery<Catalogo> list = em.createNamedQuery("get_by_anno_pubblicazione", Catalogo.class);
        list.setParameter("anno", anno);
        return list.getResultList();
    }

    public List<Libro> getByAutore(String autore){
        TypedQuery<Libro> list = em.createNamedQuery("get_by_autore", Libro.class);
        list.setParameter("autore", autore);
        return list.getResultList();
    }

    public List<Catalogo> getByTitolo(String titolo){
        TypedQuery<Catalogo> list = em.createNamedQuery("get_by_titolo", Catalogo.class);
        list.setParameter("titolo", "%" + titolo + "%");
        return list.getResultList();
    }






    //    public Catalogo findByISBN(long id){
//        return em.find(Catalogo.class, id);
//    }

    //    public Catalogo findByAnnoPubblicazione(int annoPubblicazione){
//        return em.find(Catalogo.class, annoPubblicazione);
//    }
//
//    public Libro findByAutore(String autore){
//        return em.find(Libro.class, autore);
//    }
//
//    public Catalogo findByTitolo(String titolo){
//        return em.find(Catalogo.class, titolo);
//    }


}
