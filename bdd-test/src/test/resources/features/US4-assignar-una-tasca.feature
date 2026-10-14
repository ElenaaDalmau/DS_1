Feature: US4 - Assignar una tasca a un anotador
  Com a anotador
  Vull consultar les tasques disponibles i assignar-me'n una
  Per així jo poder començar a anotar-ne dades

  Scenario: Hi ha tasques disponibles i l'anotador se n'assigna una
    Given hi ha una tasca disponible que no està assignada
    When l'anotador selecciona una tasca disponible i demana assignar-se-la
    Then el sistema li assigna la tasca a l'anotador
    And la tasca deixa de estar disponible per a la resta d anotadors

  Scenario: No hi ha tasques disponibles
    Given no hi ha tasques disponibles per a l'anotador
    When l'anotador consulta les tasques disponibles
    Then el sistema no mostra cap tasca disponible

  Scenario: Hi ha una tasca completa i l'anotador intenta assignar-se-la
    Given hi ha una tasca completada
    When l'anotador intenta assignar-se la tasca completada
    Then el sistema no li permet assignar-se la tasca completada
