package it.epicode.be.entities;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "catalogo")
@NamedQuery(name = "get_by_ISBN", query = "SELECT c FROM Catalogo c WHERE c.id = :isbn")
@NamedQuery(name = "get_by_anno_pubblicazione", query = "SELECT c FROM Catalogo c WHERE c.annoPubblicazione = :anno")
@NamedQuery(name = "get_by_titolo", query = "SELECT c FROM Catalogo c WHERE c.titolo LIKE :titolo")


public abstract class Catalogo {

    @Id
    @GeneratedValue
    @Column(name = "codice_ISBN")
    private long id;

    private String titolo;

    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;

    @Column(name = "numero_pagine")
    private int numeroPagine;

    public Catalogo(){}

    public Catalogo(String titolo, int annoPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
