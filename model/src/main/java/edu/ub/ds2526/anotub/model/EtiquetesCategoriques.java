package edu.ub.ds2526.anotub.model;

public abstract class EtiquetesCategoriques {

    // Enum de tipus d'etiquetes categòriques:
    public enum TipusEtiquetesCategoriques {
        GLOBAL_DOCUMENT,
        GLOBAL_IMATGE,
        LOCALITZACIO_OBJECTE
    }

    // Atributs privats:
    private final String nom;
    private final TipusEtiquetesCategoriques tipus;

    // Constructor:
    public EtiquetesCategoriques(String nom, TipusEtiquetesCategoriques tipus) {
        this.nom = nom;
        this.tipus = tipus;
    }

    // Getters:
    public String getNom() {
        return nom;
    }

    public TipusEtiquetesCategoriques getTipus() {
        return tipus;
    }

}
