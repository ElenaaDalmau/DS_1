Feature: US5 - Anotador/a fa anotacions amb etiquetes categòriques permeses a una tasca
  Com a anotador/a amb experteses i una tasca a realitzar
  Vull assignar una o més etiquetes categòriques (de les permeses per la tasca) per anotar una dada
  Per així jo classificar la dada segons el requisits demanats

  Scenario: Anotador assigna una etiqueta categòrica global a una imatge
    Given anotador "jeser" "guillen" amb la "Tasca Anotacio Planeta" d'expertesa "Astronomia" amb "Imatge Kepler-1649c"
    When selecciona la dada "Imatge Kepler-1649c" per anotar la etiqueta global "Planeta Vermell"
    Then assigna la etiqueta global "Planeta Vermell" a "Imatge Kepler-1649c"


  Scenario: Anotador assigna diverses etiquetes categòriques a una imatge
    Given anotador "jeser" "guillen" amb la "Tasca Anotacio Pintura" d'expertesa "Arte" amb "Imatge Nit Estrellada"
    When selecciona la dada "Imatge Nit Estrellada" per anotar les etiquetes globals "Postimpressionisme" i "Oleo"
    Then assigna les etiquetes globals "Postimpressionisme" i "Oleo" a "Imatge Nit Estrellada" a "Tasca Anotacio Pintura"

  Scenario: Anotador intenta assignar una etiqueta no permesa a una dada
    Given anotador "jeser" "guillen" amb la "Tasca Anotacio Paisatge" d'expertesa "Fotografia" amb "Imatge Platja Tsushima"
    When selecciona la dada "Imatge Nit Estrellada" per anotar la etiqueta de localització "Porta Tori"
    Then intenta assignar "Porta Tori" a "Tasca Anotacio Paisatge" i el sistema respon amb "Tipus d'etiqueta no permesa"

  Scenario: Anotador intenta assignar una etiqueta no permesa, pero varies permeses
    Given anotador "jeser" "guillen" amb la "Tasca Anotacio Dibuix" d'expertesa "Dibuix Tecnic" amb "Imatge Axonometria"
    When selecciona "Imatge Axonometria" intenta anotar dues et. globals "Dibuix net" i "Pulcre" i una de localització "Us de goma"
    Then intenta assignar "Dibuix net", "Pulcre" i "Us de goma" a "Tasca Anotacio Dibuix",funciona i llença "Tipus d'etiqueta no permesa"