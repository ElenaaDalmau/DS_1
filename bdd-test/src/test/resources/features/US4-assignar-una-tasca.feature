Feature: US4 - Assignar una tasca a un anotador
  Com a anotador
  Vull consultar les tasques disponibles i assignar-me'n una
  Per així jo poder començar a anotar-ne dades

  Scenario: L'anotador està registrat, hi ha tasques disponibles i se n'assigna una
    Given l'anotador "pablo-cano" està identificat al sistema
    And hi ha una tasca disponible que no està assignada
    When l'anotador "pablo-cano" selecciona una tasca disponible
    And s'assigna la tasca seleccionada
    Then el sistema li assigna la tasca a l'anotador
    And l'anotador pot començar a etiquetar les dades
    And la tasca deixa d'estar disponible per a la resta d'anotadors

  Scenario: L'anotador està registrat i la tasca està assignada a un altre anotador
    Given l'anotador "sergio-santos" esta identificat al sistema
    And la tasca està assignada a un altre anotador
    When l'anotador intenta assignar-se la tasca
    Then el sistema no li permet assignar-se la tasca
    And la tasca continua assignada a l'altre anotador

  Scenario: L'anotador està registrat i no hi ha tasques disponibles
    Given l'anotador "eduardo-funez" esta identificat al sistema
    And no hi ha tasques disponibles per a l'anotador
    When l'anotador consulta les tasques disponibles
    Then el sistema no mostra cap tasca disponible

  Scenario: L'anotador esta registrat i intenta assignar-se una tasca completada
    Given l'anotador "juan-vega" esta identificat al sistema
    And hi ha una tasca completada
    When l'anotador intenta assignar-se la tasca completada
    Then el sistema no permet assignar-li la tasca completada