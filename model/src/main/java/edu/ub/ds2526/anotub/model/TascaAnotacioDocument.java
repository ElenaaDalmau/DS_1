package edu.ub.ds2526.anotub.model;

public class TascaAnotacioDocument extends TascaAnotacio<Document>{

    // Constructor
    public TascaAnotacioDocument(String titol,
                                 String instrucions,
                                 String expertesaRequerida,
                                 float remuneracioPerDada,
                                 java.time.LocalDate dataLimit,
                                 java.util.List<EtiquetesCategoriques.TipusEtiquetesCategoriques> etiquetesPermeses) {

        super(titol, instrucions, expertesaRequerida, remuneracioPerDada, dataLimit, etiquetesPermeses);
        this.setDadesPendents(new java.util.ArrayList<>());
        this.setDadesFetes(new java.util.ArrayList<>());
    }
}
