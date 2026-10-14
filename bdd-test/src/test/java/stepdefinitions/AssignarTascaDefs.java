package stepdefinitions;

import edu.ub.ds2526.anotub.model.TascaAnotacio;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import state.WorldState;

public class AssignarTascaDefs {

    private final WorldState state;

    public AssignarTascaDefs(WorldState state) {
        this.state = state;
    }

    @Given("hi ha una tasca disponible que no està assignada")
    public void hiHaUnaTascaDisponibleQueNoEstàAssignada(String expertesa) {
        // Write code here that turns the phrase above into concrete actions
        state.getControlador().trobarTasquesDisponibles(expertesa);

        assertTrue(assert.getResultat().contains(titolTasca));
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

    @And("la tasca deixa de estar disponible per a la resta d anotadors")
    public void laTascaDeixaDeEstarDisponiblePerALaRestaDAnotadors(TascaAnotacio.EstatsTasca estatTascaNou, String titolTasca) {
        // Write code here that turns the phrase above into concrete actions
        state.getControlador().canviarEstatTasca(estatTascaNou, titolTasca);
    }

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
