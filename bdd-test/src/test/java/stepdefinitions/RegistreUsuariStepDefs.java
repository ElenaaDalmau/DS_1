package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import state.WorldState;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RegistreUsuariStepDefs {
  private final WorldState state;

  public RegistreUsuariStepDefs() {
    this.state = new WorldState();
  }

  public RegistreUsuariStepDefs(WorldState state) {
    this.state = state;
  }

  @Given("l'usuari {string} no està registrat")
  public void l_usuari_no_està_registrat(String nomUsuari) {
    // No fer res o, alternativament, assegurar-se que l'usuari no està registrat al sistema
    state.getControlador().eliminarUsuari(nomUsuari);
  }

  @Given("l'usuari {string} ja està registrat")
  public void l_usuari_ja_està_registrat(String nomUsuari) {
    state.getControlador().eliminarUsuari(nomUsuari);
    state.getControlador().registrarUsuari(
        nomUsuari,
        "Password123",
        "John",
        "Doe",
        "ESP",
        "ABC123456",
        LocalDate.parse("2030-12-31"),
        "ES9121000418450200051332",
        "CAIXESBBXXX",
        "john@example.com",
        "600123123",
        List.of("Java", "Bases de dades")
    );
  }

  @Given("l'usuari {string} està registrat amb la contrasenya {string}")
  public void l_usuari_està_registrat_amb_la_contrasenya(String nomUsuari, String contrasenya) {
    state.getControlador().eliminarUsuari(nomUsuari);
    state.getControlador().registrarUsuari(
        nomUsuari,
        contrasenya,
        "John",
        "Doe",
        "ESP",
        "ABC123456",
        LocalDate.parse("2030-12-31"),
        "ES9121000418450200051332",
        "CAIXESBBXXX",
        "john@example.com",
        "600123123",
        List.of("Java", "Bases de dades")
    );
  }

  @When("un usuari demana registrar-se amb les dades següents:")
  public void un_usuari_demana_registrar_se_amb_les_dades_següents(DataTable dataTable) {

    List<Map<String, String>> rows =
        dataTable.asMaps(String.class, String.class);

    Map<String, String> row = rows.get(0);

    String nomUsuari = row.get("nomUsuari");
    String contrasenya = row.get("contrasenya");

    String passaportNom = row.get("passaportNom");
    String passaportCognoms = row.get("passaportCognoms");
    String passaportPais = row.get("passaportPais");
    String passaportNumero = row.get("passaportNumero");
    LocalDate passaportCaducitat =
        (row.get("passaportCaducitat") == null || row.get("passaportCaducitat").isBlank())
            ? null
            : LocalDate.parse(row.get("passaportCaducitat"));

    String compteIban = row.get("compteIban");
    String compteSwift = row.get("compteSwift");

    String email = row.get("email");
    String telefon = row.get("telèfon");

    String expertesesText = row.get("experteses");
    List<String> experteses = (expertesesText == null || expertesesText.isBlank())
        ? List.of()
        : Arrays.stream(expertesesText.split(";"))
            .map(String::trim)
            .toList();

    String resultat = state.getControlador().registrarUsuari(
        nomUsuari,
        contrasenya,
        passaportNom,
        passaportCognoms,
        passaportPais,
        passaportNumero,
        passaportCaducitat,
        compteIban,
        compteSwift,
        email,
        telefon,
        experteses
    );

    state.setResultat(resultat);
  }

  @Then("l'usuari {string} està registrat")
  public void l_usuari_està_registrat(String nomUsuari) {
    assert(this.state.getControlador().estaRegistrat(nomUsuari));
  }
}
