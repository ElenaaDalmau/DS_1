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


    @Given("hi ha una tasca disponible que no està assignada")
    public void hiHaUnaTascaDisponibleQueNoEstàAssignada(String expertesa) {
        // Write code here that turns the phrase above into concrete actions
        state.getControlador().trobarTasquesDisponibles(expertesa);

        // assertTrue(assert.getResultat().contains(titolTasca));
    }

    @When("l'anotador selecciona una tasca disponible i demana assignar-se-la")
    public void lAnotadorSeleccionaUnaTascaDisponibleIDemanaAssignarSeLa(String titolTasca, String nomUsuari) {
        // Write code here that turns the phrase above into concrete actions
        state.setResultat(state.getControlador().assignarTascaAAnotadora(titolTasca, nomUsuari));
    }

    @Then("el sistema li assigna la tasca a l'anotador")
    public void elSistemaLiAssignaLaTascaALAnotador(String titolTasca, String nomUsuari) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("La tasca s'ha assignat correctament", state.getResultat());
    }

//    @And("la tasca deixa de estar disponible per a la resta d anotadors")
//    public void laTascaDeixaDeEstarDisponiblePerALaRestaDAnotadors(TascaAnotacio.EstatsTasca estatTascaNou, String titolTasca) {
//        // Write code here that turns the phrase above into concrete actions
//        state.getControlador().canviarEstatTasca(estatTascaNou, titolTasca);
//    }

    @Given("no hi ha tasques disponibles per a l'anotador")
    public void noHiHaTasquesDisponiblesPerALAnotador() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("l'anotador consulta les tasques disponibles")
    public void lAnotadorConsultaLesTasquesDisponibles(String expertesa){
        // Write code here that turns the phrase above into concrete actions
        state.getControlador().trobarTasquesDisponibles(expertesa);
    }

    @Then("el sistema no mostra cap tasca disponible")
    public void elSistemaNoMostraCapTascaDisponible() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Given("hi ha una tasca completada")
    public void hiHaUnaTascaCompletada() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("l'anotador intenta assignar-se la tasca completada")
    public void lAnotadorIntentaAssignarSeLaTascaCompletada(String titolTasca, String nomUsuari) {
        // Write code here that turns the phrase above into concrete actions
        state.getControlador().assignarTascaAAnotadora(titolTasca, nomUsuari);
    }

    @Then("el sistema no li permet assignar-se la tasca completada")
    public void elSistemaNoPermetAssignarLiLaTascaCompletada() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("No s'ha pogut assignar la tasca. La tasca ja està completada.", state.getResultat());
    }

}
