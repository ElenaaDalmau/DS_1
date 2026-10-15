package stepdefinitions;

import edu.ub.ds2526.anotub.model.EtiquetesCategoriques;
import edu.ub.ds2526.anotub.model.TascaAnotacio;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import state.WorldState;

import edu.ub.ds2526.anotub.model.Dada;
import edu.ub.ds2526.anotub.model.Imatge;
import edu.ub.ds2526.anotub.model.TascaAnotacioImatge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CompletarTascaStepDefs {
    private final WorldState state;

    private Imatge dadaActual;
    private TascaAnotacio tascaActual;
    private List<Imatge> dadesDisponibles = new ArrayList<>();

    public CompletarTascaStepDefs() {
      this.state = new WorldState();
    }

    public CompletarTascaStepDefs(WorldState state) {
      this.state = state;
    }


    @Given("l'anotador {string} {string} té una tasca {string} assignada")
    public void lAnotadorTéUnaTascaAssignada(String nom, String cognom, String tasca) {
      // Eliminem primer l'usuari testejable, per si hem de canviar les dades internament pel suposat test:
      state.controlador.eliminarUsuari(nom);

      // Afegim l'usuari pel test:
      state.controlador.registrarUsuari(
              nom,
              "Password123",
              nom,
              cognom,
              "ESP",
              "ABC123456",
              LocalDate.parse("2030-12-31"),
              "ES9121000418450200051332",
              "CAIXESBBXXX",
              nom + "@example.com",
              "600123123",
              List.of("expertesa"));

      // Hem de crear una tasca per tal de testejar, i seguirem la mateixa lògica que abans:
      state.controlador.eliminarTasca(tasca);
      state.controlador.afegirTascaAnotacioImatge(
              tasca,
              "Anotar les diferencies de les imatges",
              "expertesa",
              100.0f,
              LocalDate.parse("2030-12-31"),
              List.of(EtiquetesCategoriques.TipusEtiquetesCategoriques.GLOBAL_IMATGE,
                      EtiquetesCategoriques.TipusEtiquetesCategoriques.LOCALITZACIO_OBJECTE));

      // Assignem la tasca a l'anotador
      state.controlador.assignarTascaAAnotadora(tasca, nom);

      tascaActual = (TascaAnotacioImatge) state.controlador.obtenirTasca(tasca);
    }

    @And("només li queda dada {string} per completar")
    public void nomésLiQuedaDadaPerCompletar(String dada) {
      // Creem una dada
      dadaActual = new Imatge(dada, tascaActual,100,100, new ArrayList<>());
      // Afegim la dada a la tasca de pendents
      tascaActual.setDadesPendents(new ArrayList<>(List.of(dadaActual)));
      // Creem la llista de dades fetes buida
      tascaActual.setDadesFetes(new ArrayList<>());
    }

    @When("l'anotador {string} marca aquesta dada {string} com anotada")
    public void lAnotadorMarcaAquestaDadaComAnotada(String nom, String dada) {
      // Busquem la dada entre les dades pendents de la tasca
        for(Imatge imatge : (List<Imatge>) tascaActual.getDadesPendents()) {
            if (imatge.getTitol().equals(dada)) {
                dadaActual = imatge;
                break;
            }
        }

        assertEquals(dada, dadaActual.getTitol());
      // Comprovem que l'usuari té la tasca assignada
      assertTrue(state.controlador.isUsuariAsignatATasca(tascaActual.getTitol(), nom), "L'anotador hauria d'estar assignat a la tasca");

      // Marquem la dada com a feta
      tascaActual.ferDada(dadaActual);
    }

    @Then("el sistema marca la dada {string} com anotada")
    public void elSistemaMarcaLaDadaComAnotada(String dada) {
      //assertNotNull(dadaActual);
      assertEquals(dada, dadaActual.getTitol());
      assertTrue(dadaActual.isAnotada());
    }

    @And("el sistema marca la tasca {string} com a completada")
    public void elSistemaMarcaLaTascaComACompletada(String tasca) {
      assertTrue(state.controlador.mateixEstatTasca(TascaAnotacio.EstatsTasca.COMPLETA, tasca),
              "La hauria d'estar marcada com a completada");
    }

    // ----------------------------------------------------------------------------------------------------------------
  @Given("l'anotador {string} {string} té una tasca {string} assignada amb diverses dades per anotar")
  public void lAnotadorTéUnaTascaAssignadaAmbDiversesDadesPerAnotar(String nom, String cognom, String tasca) {
        // Eliminem els usuaris i les tasques, i les tornem a registrar
        state.controlador.eliminarTasca(tasca);
        state.controlador.eliminarUsuari(nom);
        state.controlador.registrarUsuari(nom, "Password123",
                nom, cognom, "ESP", "ABC123456",
                LocalDate.parse("2030-12-31"), "ES9121000418450200051332",
                "CAIXESBBXXX", nom + "@example.com", "600123123",
                List.of("expertesa"));
        state.controlador.afegirTascaAnotacioImatge(tasca, "Anotar les diferencies de les imatges",
                "expertesa", 100.0f, LocalDate.parse("2030-12-31"),
                List.of(EtiquetesCategoriques.TipusEtiquetesCategoriques.GLOBAL_IMATGE,
                        EtiquetesCategoriques.TipusEtiquetesCategoriques.LOCALITZACIO_OBJECTE));

        // Assignem la tasca a l'anotador
        state.controlador.assignarTascaAAnotadora(tasca, nom);

        // Obtenim la tasca creada abans d'utilitzar-la
      tascaActual = (TascaAnotacioImatge) state.controlador.obtenirTasca(tasca);

        // Creem les dades pendents de la tasca
        List<Imatge> imatges = new ArrayList<>();
        imatges.add(new Imatge("D1", tascaActual, 100, 100, new ArrayList<>()));
        imatges.add(new Imatge("D2", tascaActual, 200, 200, new ArrayList<>()));
        imatges.add(new Imatge("D3", tascaActual, 300, 300, new ArrayList<>()));

        // Afegim les dades pendents a la llista, i creem la llista de dades fetes buida
        tascaActual.setDadesPendents(imatges);
        tascaActual.setDadesFetes(new ArrayList<>());
    }

  @And("la tasca {string} continua com a pendent de completar")
  public void laTascaContinuaComAPendentDeCompletar(String tasca) {
        // Controlem que la tasca no es buida
        assertFalse(tascaActual.getDadesPendents().isEmpty(),
                "La tasca hauria de tenir dades pendents");
        assertEquals(TascaAnotacio.EstatsTasca.ASSIGNADA, tascaActual.getEstatTasca(),
                "La tasca ha de continuar com a assignada");
  }

    @Given("l'anotador {string} {string} no té una tasca {string} assignada")
    public void lAnotadorNoTéUnaTascaAssignada(String nom, String cognom, String tasca) {
        // Eliminem els usuaris i les tasques, i les tornem a registrar
        state.controlador.eliminarTasca(tasca);
        state.controlador.eliminarUsuari(nom);
        state.controlador.registrarUsuari(nom, "Password123",
                nom, cognom, "ESP", "ABC123456",
                LocalDate.parse("2030-12-31"), "ES9121000418450200051332",
                "CAIXESBBXXX", nom + "@example.com", "600123123",
                List.of("expertesa"));
        state.controlador.afegirTascaAnotacioImatge(tasca, "Anotar les diferencies de les imatges",
        "expertesa", 100.0f, LocalDate.parse("2030-12-31"),
                List.of(EtiquetesCategoriques.TipusEtiquetesCategoriques.GLOBAL_IMATGE,
                        EtiquetesCategoriques.TipusEtiquetesCategoriques.LOCALITZACIO_OBJECTE));

        tascaActual = (TascaAnotacioImatge) state.controlador.obtenirTasca(tasca);
        // Creem les dades pendents de la tasca
        List<Imatge> imatges = new ArrayList<>();
        imatges.add(new Imatge("D1", tascaActual, 100, 100, new ArrayList<>()));
        imatges.add(new Imatge("D2", tascaActual, 200, 200, new ArrayList<>()));
        imatges.add(new Imatge("D3", tascaActual, 300, 300, new ArrayList<>()));

        // Afegim les dades pendents a la llista, i creem la llista de dades fetes buida
        tascaActual.setDadesPendents(imatges);
        tascaActual.setDadesFetes(new ArrayList<>());

        dadesDisponibles = new ArrayList<>();
    }

    @When("l'anotador {string} intenta consultar les dades disponibles de la tasca {string}")
    public void lAnotadorIntentaConsultarLesDadesDisponiblesDeLaTasca(String nom, String tasca) {
        // Intentar obtenir les dades disponibles de la tasca
        dadesDisponibles = state.controlador.consultarDadesDisponiblesDeTasca(tasca, nom);
    }

    @Then("el sistema no li mostra cap dada, ja que no té cap tasca assignada")
    public void elSistemaNoLiMostraCapDadaJaQueNoTéCapTascaAssignada() {
        assertTrue(dadesDisponibles.isEmpty(), "No hi ha dades disponibles per" +
                "a l'anotador sense tasca assignada");
    }


}
