package edu.ub.ds2526.anotub.model;

public class Document extends Dada{

    // Atribut Privat:
    private final String document;

    // Constructor:
    public Document(String titol, TascaAnotacio tascaAsociada, String document) {
        super(titol, tascaAsociada);
        this.document = document;
    }

    // Getters
    public String getDocument() {
        return document;
    }

}
