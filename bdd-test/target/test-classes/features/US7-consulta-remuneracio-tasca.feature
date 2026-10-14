Feature: US7 - Consulta de remuneració aconseguida amb Tasques Completades
  Com anotador-a
  Vull consultar la remuneració econòmica total que he acumulat per les meves tasques completades
  Per així jo portar un control dels meus pagaments en l'aplicació

  Scenario: Anotador-a consulta la remuneració d’una tasca
    Given anotador "eduardo" "funez" amb les tasques "Tasca Anotacio Pintura" i "Tasca Anotacio Dibuix" completades
    When consultar la remuneració de "Tasca Anotacio Pintura" que es de "70.0" €-dada, i ha fet "3" dades
    Then el sistema li mostra el total que serà de "210.0" €

  Scenario: Anotador/a consulta la remuneració total d’un conjunt de tasques acumulades
    Given anotador "eduardo" "funez" amb les tasques "Tasca Anotacio Pintura" i "Tasca Anotacio Dibuix" completades
    When consulta la remuneració total "Tasca Anotacio Pintura" i "Tasca Anotacio Dibuix" amb "4" dades fetes i "30.0" €-dada cascuna
    Then el sistema li mostra el total que es de "240.0"

  Scenario: Anotador/a consulta la remuneració de les tasques completades, amb una assignada
    Given anotador "eduardo" "funez" amb les tasques "Tasca Anotacio Pintura" completada i "Tasca Anotacio Dibuix" assignada
    When consulta la remuneració total "Tasca Anotacio Pintura" amb "2" dades fetes i "45.5" €-dada
    Then el sistema només lo mostra la tasca completa "Tasca Anotacio Pintura" i la remuneració "91" €