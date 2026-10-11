package edu.ub.ds2526.anotub.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TascaAnotacioImatge extends TascaAnotacio<Imatge>{

    // Constructor:
    public TascaAnotacioImatge(String titol,
                               String instrucions,
                               String expertesaRequerida,
                               float remuneracioPerDada,
                               LocalDate dataLimit,
                               List<EtiquetesCategoriques.TipusEtiquetesCategoriques> etiquetesPermeses) {

        super(titol, instrucions, expertesaRequerida, remuneracioPerDada, dataLimit, etiquetesPermeses);
        this.setDadesPendents(new ArrayList<>());
        this.setDadesFetes(new ArrayList<>());
    }
}
