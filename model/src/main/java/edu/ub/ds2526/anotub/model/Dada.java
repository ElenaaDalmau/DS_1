package edu.ub.ds2526.anotub.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Dada {

    // Atributs privats
    private final String titol;
    private final TascaAnotacio<?> tascaAsociada;
    private List<EtiquetesCategoriques> etiquetes;

    // Constructor:
    public Dada(String titol, TascaAnotacio<?> tascaAsociada) {
        this.titol = titol;
        this.tascaAsociada = tascaAsociada;
        this.etiquetes = new ArrayList<>();
    }

    // Getters i Setters:
    public String getTitol() {
        return titol;
    }

    public TascaAnotacio<?> getTascaAsociada() {
        return tascaAsociada;
    }

    public List<EtiquetesCategoriques> getEtiquetes() {
        return etiquetes;
    }

    public void setEtiquetes(List<EtiquetesCategoriques> etiquetes) {
        this.etiquetes = etiquetes;
    }

    public void addEtiqueta(EtiquetesCategoriques etiqueta) {
        this.etiquetes.add(etiqueta);
    }

    public void addEtiquetes(List<EtiquetesCategoriques> etiquetes) {
        this.etiquetes.addAll(etiquetes);
    }

}
