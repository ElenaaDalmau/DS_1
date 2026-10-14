package stepdefinitions;

import edu.ub.ds2526.anotub.model.EtiquetesCategoriques;
import edu.ub.ds2526.anotub.model.TascaAnotacio;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import state.WorldState;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class AssignarTascaDefs {

    private final WorldState state;

    public AssignarTascaDefs(WorldState state) {
        this.state = state;
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Given("anotador identificat {string} {string} i hi ha una tasca {string} disponible d'expertesa {string}")
    public void anotadorIdentificatIHiHaUnaTascaDisponible(String nom, String cognom, String tasca, String expertesa) {
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
                List.of(expertesa));

        // Hem de crear una tasca per tal de testejar, i seguirem la mateixa lògica que abans:
        state.controlador.eliminarTasca(tasca);
        state.controlador.afegirTascaAnotacioImatge(
                tasca,
                "Anotar les diferencies de les imatges",
                expertesa,
                100.0f,
                LocalDate.parse("2030-12-31"),
                List.of(EtiquetesCategoriques.TipusEtiquetesCategoriques.GLOBAL_IMATGE,
                        EtiquetesCategoriques.TipusEtiquetesCategoriques.LOCALITZACIO_OBJECTE));

    }

    @When("anotador {string} selecciona la tasca {string} disponible i demana assignar-se-la")
    public void anotadorSeleccionaLaTascaDisponibleIDemanaAssignarSeLa(String nom, String tasca) {
        state.controlador.assignarTascaAAnotadora(tasca, nom);
    }

    @Then("el sistema li assigna la tasca {string} a l'anotador {string}")
    public void elSistemaAssignaLaTascaALAnotador(String tasca, String nom) {
        assertTrue(state.controlador.isUsuariAsignatATasca(tasca, nom));
    }

    @And("la tasca {string} deixa de estar disponible per a la resta d anotadors")
    public void laTascaDeixaDeEstarDisponiblePerALaRestaDAnotadors(String tasca) {
        assertTrue(state.controlador.mateixEstatTasca(TascaAnotacio.EstatsTasca.ASSIGNADA, tasca));
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Given("no hi ha tasques disponibles per a l'anotador {string} {string}")
    public void noHiHaTasquesDisponiblesPerALAnotador(String nom, String cognom) {
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
                List.of("Expertesa sense tasques"));

    }

    @When("l'anotador {string} consulta les tasques disponibles")
    public void lAnotadorConsultaLesTasquesDisponibles(String nom) {
        // Write code here that turns the phrase above into concrete actions
        String resultat = state.controlador.trobarTasquesDisponibles("Expertesa sense tasques");
        state.setResultat(resultat);
    }

    @Then("el sistema no mostra cap tasca disponible")
    public void elSistemaNoMostraCapTascaDisponible(String tasca, String usuari){
        // Write code here that turns the phrase above into concrete actions
        assertEquals(state.getResultat(), "NO hi ha tasques disponibles");
    }

    @Given("hi ha una tasca {string} completada i tenim un usuari {string} {string}")
    public void hiHaUnaTascaCompletadaITenimUnUsuari(String tasca, String nom, String cognom) {
        // Write code here that turns the phrase above into concrete actions
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
                List.of("Biologia"));

        // Hem de crear una tasca per tal de testejar, i seguirem la mateixa lògica que abans:
        state.controlador.eliminarTasca(tasca);
        state.controlador.afegirTascaAnotacioImatge(
                tasca,
                "Anotar les diferencies de les imatges",
                "Biologia",
                100.0f,
                LocalDate.parse("2030-12-31"),
                List.of(EtiquetesCategoriques.TipusEtiquetesCategoriques.GLOBAL_IMATGE,
                        EtiquetesCategoriques.TipusEtiquetesCategoriques.LOCALITZACIO_OBJECTE));
    // Canviem l'estat de la tasca a COMPLETADA:
        state.controlador.canviarEstatTasca(TascaAnotacio.EstatsTasca.COMPLETA, tasca);

    }

    @When("l'anotador {string} intenta assignar-se la tasca {string} completada")
    public void lAnotadorIntentaAssignarSeLaTascaCompletada(String nom, String tasca) {
        // Write code here that turns the phrase above into concrete actions
        state.controlador.assignarTascaAAnotadora(tasca, nom);
    }

    @Then("el sistema no li permet assignar-se la tasca {string} completada")
    public void elSistemaNoLiPermetAssignarSeLaTascaCompletada(String tasca) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(state.controlador.mateixEstatTasca(TascaAnotacio.EstatsTasca.COMPLETA, tasca));
    }
}
