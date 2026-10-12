package edu.ub.ds2526.anotub.model;

import java.time.LocalDate;
import java.util.List;

public abstract class TascaAnotacio<T> {

    public enum EstatsTasca {
        DISPONIBLE,
        ACABADA,
        NO_DISPONIBLE
    }

    // Atributs privats
    private final String titol;
    // private final Empresa empresa; Suposada class Empresa.
    private final String instrucions;
    private final String expertesaRequerida;
    private float remuneracio;
    private final float remuneracioPerDada;
    private final LocalDate dataLimit;
    private List<T> dadesFetes;
    private List<T> dadesPendents;
    private Anotadora anotadoraAsignada;
    private EstatsTasca estatTasca;
    private final List<EtiquetesCategoriques.TipusEtiquetesCategoriques> etiquetesPermeses;



    // Costructor
    public TascaAnotacio(String titol, String instrucions, String expertesaRequerida, float remuneracioPerDada,
                         LocalDate dataLimit, List<EtiquetesCategoriques.TipusEtiquetesCategoriques> etiquetesPermeses) {
            this.titol = titol;
            this.instrucions = instrucions;
            this.expertesaRequerida = expertesaRequerida;
            this.remuneracioPerDada = remuneracioPerDada;
            this.remuneracio = 0.0f;
            this.dataLimit = dataLimit;
            this.anotadoraAsignada = null;
            this.estatTasca = EstatsTasca.DISPONIBLE;
            this.etiquetesPermeses = etiquetesPermeses;
        }

    // Getters i Setters
    public String getTitol() {
        return titol;
    }

    public String getInstrucions() {
        return instrucions;
    }

    public String getExpertesaRequerida() {
        return expertesaRequerida;
    }

    public float getRemuneracio() {
        return remuneracio;
    }

    public void setRemuneracio(float remuneracio) {
        this.remuneracio = remuneracio;
    }

    public LocalDate getDataLimit() {
        return dataLimit;
    }

    public float getRemuneracioPerDada() {
        return remuneracioPerDada;
    }

    public List<T> getDadesPendents() {
        return dadesPendents;
    }

    public void setDadesPendents(List<T> dadesPendents) {
        this.dadesPendents = dadesPendents;
    }

    public List<T> getDadesFetes() {
        return dadesFetes;
    }

    public void setDadesFetes(List<T> dadesFetes) {
        this.dadesFetes = dadesFetes;
    }

    public Anotadora getAnotadoraAsignada() {
        return anotadoraAsignada;
    }

    public void setAnotadoraAsignada(Anotadora anotadoraAsignada) {
        this.anotadoraAsignada = anotadoraAsignada;
    }

    public EstatsTasca getEstatTasca() {
        return estatTasca;
    }

    public void setEstatTasca(EstatsTasca estatTasca) {
        this.estatTasca = estatTasca;
    }

    public List<EtiquetesCategoriques.TipusEtiquetesCategoriques> getEtiquetesPermeses() {
        return etiquetesPermeses;
    }

    /**
     * Mètode que assigna una anotadora a la tasca i canvia l'estat de la tasca a NO_DISPONIBLE.
     * @param anotadoraAsignada L'anotadora que s'assigna a la tasca.
     */
    public void assignarTasca(Anotadora anotadoraAsignada){
        setAnotadoraAsignada(anotadoraAsignada);
        setEstatTasca(EstatsTasca.NO_DISPONIBLE);
    }

    /**
     * Mètode que avalua si la tasca està acabada, canviant el enum EstatsTasca.
     */
    public void avaluarEstatTasca() {
        if (dadesPendents.isEmpty()) {
            setEstatTasca(EstatsTasca.ACABADA);
        }
    }

    /**
     * Mètode que comprova si la tasca està disponible
     * @return true si la tasca està disponible, fals en cas contrari.
     */
    public boolean isTascaDisponible(){
        return estatTasca == EstatsTasca.DISPONIBLE;
    }

    /**
     * Mètode que canvia l'estat d'una dada a feta i actualitza la remuneració de la tasca.
     */
    public void ferDada() {
    }

    /**
     * To String de la tasca d'anotació.
     * @return String amb la informació de la tasca.
     */
    @Override
    public String toString() {
        return "TascaAnotacio{" +
                "titol=" + titol  +
                ", instrucions=" + instrucions +
                ", expertesaRequerida=" + expertesaRequerida +
                ", remuneracio=" + remuneracio +
                ", remuneracioPerDada=" + remuneracioPerDada +
                ", dataLimit=" + dataLimit +
                ", dadesFetes=" + dadesFetes.size() +
                ", dadesPendents=" + dadesPendents.size() +
                ", anotadoraAsignada=" + (anotadoraAsignada != null ? anotadoraAsignada.getNomUsuari() : "Cap") +
                ", estatTasca=" + estatTasca.toString() +
                ", etiquetesPermeses=" + etiquetesPermeses.toString() +
                ", tipusTasca=" + this.getClass().getSimpleName() +
                '}';
    }
}
