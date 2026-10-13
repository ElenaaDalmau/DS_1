Feature: USX - Completar una tasca assignada
  Com a anotador amb una tasca assignada
  Vull marcar una dada com anotada
  Per així jo poder completar la tasca quan estiguin totes les dades anotades

  Scenario: L'anotador té una tasca assignada i anota l'última dada com anotada
    Given l'anotador "pablo-cano" està identificat
    And té una tasca assignada
    And només li queda una dada per completar
    When l'anotador marca aquesta dada com anotada
    Then el sistema marca la dada com anotada
    And el sistema marca la tasca com a completada

  Scenario: L'anotador té una tasca assignada, anota una dada com anotada però no és l'ultima
    Given l'anotador "sergio-santos" està identificat
    And té una tasca assignada amb diverses dades per anotar
    When l'anotador marca una dada com anotada
    Then el sistema marca la dada com anotada
    And la tasca continua com a pendent de completar

  Scenario: L'anotador no té una tasca assignada i intenta consultar les dades
    Given l'anotador "sergio-santos" està identificat
    And no té una tasca assignada
    When l'anotador intenta consultar les dades disponibles de la tasca
    Then el sistema no li mostra cap dada pendent d'anotar.

  Scenario: L'anotador intenta completar una tasca que ja ha estat completada
    Given l'anotador "eduardo-funez" està identificat
    And té una tasca assignada sense dades pendents d'anotar
    When l'anotador intenta consultar les dades pendents d'anotar de la tasca
    Then el sistema no li mostra cap dada, ja que estan totes anotades i la tasca està completada