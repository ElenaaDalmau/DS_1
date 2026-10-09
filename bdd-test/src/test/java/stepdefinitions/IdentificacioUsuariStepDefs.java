package stepdefinitions;

import io.cucumber.java.en.When;
import state.WorldState;

public class IdentificacioUsuariStepDefs {
  private final WorldState state;

  public IdentificacioUsuariStepDefs() {
    this.state = new WorldState();
  }

  public IdentificacioUsuariStepDefs(WorldState state) {
    this.state = state;
  }

  @When("{string} intenta identificar-se amb la contrasenya {string}")
  public void intenta_identificar_se_amb_la_contrasenya(String nomUsuari, String contrasenya) {
    String resultat = state.getControlador().identificarUsuari(nomUsuari, contrasenya);
    state.setResultat(resultat);
  }
}
