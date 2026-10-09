Feature: US3 - Tasques disponibles per a l'anotador
  Com a anotador amb expertesses
  Vull veure les tasques disponibles per a mi
  Per així poder seleccionar-ne un per anotar-ne dades

    Scenario: L'anotador amb expertesses té tasques disponibles
        Given l'anotadora "elena-dalmau" té les expertesa de "biologia"
        When l'anotador accedeix a la secció de tasques disponibles
        Then el sistema li mostra totes les tasques disponibles per la seva expertesa

    Scenario: L'anotador amb expertesses no té tasques disponibles per la seva expertesa
        Given l'anotador "eduardo-funez" té les expertesa de "Arquitectura"
        When l'anotador accedeix a la secció de tasques disponibles
        Then el sistema li mostra un missatge indicant que no hi ha tasques disponibles per la seva expertesa

    Scenario: L'anotador amb expertesses no té tasques disponibles, ja que està totes finalitzades
        Given l'anotador "jeser-guillen" té les expertesa de "Neurologia"
        When l'anotador accedeix a la secció de tasques disponibles
        Then el sistema li mostra un missatge de error que dirà “no hi ha tasques amb aquesta expertesa”