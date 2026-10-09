package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import state.WorldState;

public class TasquesDisponiblesAnotadorsStepDefs {

    private final WorldState state;

    public TasquesDisponiblesAnotadorsStepDefs() {
        this.state = new WorldState();
    }

    public TasquesDisponiblesAnotadorsStepDefs(WorldState state) {
        this.state = state;
    }

    @Given("l'anotador {string} amb expertesa {string}")
    public void l_anotador_amb_expertesa(String arg1, String arg2) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("l'anotador {string} consulta les tasques disponibles")
    public void l_anotador_consulta_les_tasques_disponibles(String arg1) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("l'anotador {string} veu les tasques disponibles")
    public void l_anotador_veu_les_tasques_disponibles(String arg1) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("el sistema la llista de tasques disponibles per a l'anotador {string} amb expertesa {string}")
    public void el_sistema_la_llista_de_tasques_disponibles_per_a_l_anotador_amb_expertesa(String arg1, String arg2) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("no hi ha tasques disponibles per a l'anotador {string} amb expertesa {string}")
    public void no_hi_ha_tasques_disponibles_per_a_l_anotador_amb_expertesa(String arg1, String arg2) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("no hi ha tasques disponibles per a l'anotador {string} estan totes finalitzades")
    public void no_hi_ha_tasques_disponibles_i_totes_finalitzades(String arg1, String arg2) {
        // Write code here that turns the phrase above into concrete actions
    }

}
