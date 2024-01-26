package it.epicode.be.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
@NamedQuery(name = "get_prestiti_by_utente", query = "SELECT p FROM Prestito p WHERE p.utente.id = :id")
@NamedQuery(name = "get_prestiti_scaduti", query = "SELECT p FROM Prestito p " + "WHERE p.getDataRestituzioneEffettiva IS NULL OR p.getDataRestituzioneEffettiva > p.dataRestituzionePrevista")

public class Prestito {


    @Id
    @GeneratedValue
    @Column(name = "prestito_id")
    private long prestitoId;


    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @OneToOne
    @JoinColumn(name = "elemento_catalogo_id")
    private Catalogo catalogo;

    @Column(name = "data_inizio_prestito")
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista")
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate getDataRestituzioneEffettiva;

    public Prestito(){}

    public Prestito(Utente utente, Catalogo catalogo, LocalDate dataInizioPrestito, LocalDate dataRestituzionePrevista, LocalDate getDataRestituzioneEffettiva) {
        this.utente = utente;
        this.catalogo = catalogo;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.getDataRestituzioneEffettiva = getDataRestituzioneEffettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getGetDataRestituzioneEffettiva() {
        return getDataRestituzioneEffettiva;
    }

    public void setGetDataRestituzioneEffettiva(LocalDate getDataRestituzioneEffettiva) {
        this.getDataRestituzioneEffettiva = getDataRestituzioneEffettiva;
    }

    public long getPrestitoId() {
        return prestitoId;
    }

    public void setPrestitoId(long prestitoId) {
        this.prestitoId = prestitoId;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "prestitoId=" + prestitoId +
                ", utente=" + utente +
                ", catalogo=" + catalogo +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", getDataRestituzioneEffettiva=" + getDataRestituzioneEffettiva +
                '}';
    }
}
