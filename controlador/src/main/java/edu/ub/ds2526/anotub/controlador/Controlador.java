package edu.ub.ds2526.anotub.controlador;

import edu.ub.ds2526.anotub.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Controlador {

  private final List<Anotadora> usuaries;
  private final List<TascaAnotacio<?>> tasques;

  public Controlador() {
    this.usuaries = new ArrayList<>();
    this.tasques = new ArrayList<>();
  }

  public Boolean eliminarUsuari(String string) {
    Anotadora anotadora = trobarUsuari(string);
    if (anotadora != null) {
      return usuaries.remove(anotadora);
    }
    return false;
  }

  public String registrarUsuari(
      String nomUsuari,
      String contrasenya,
      String passaportNom,
      String passaportCognoms,
      String passaportPais,
      String passaportNumero,
      LocalDate passaportCaducitat,
      String compteIban,
      String compteSwift,
      String email,
      String telefon,
      List<String> experteses
  ) {
    if (estaRegistrat(nomUsuari)) {
      return "No s'ha pogut registrar l'usuari. Ja existeix un usuari amb aquest nom.";
    }

    String atributFaltant = trobarAtributFaltant(
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

    if (atributFaltant != null) {
      return "L'usuari no s'ha pogut registrar perquè hi faltava l'atribut '" + atributFaltant + "'.";
    }

    if (!esContrasenyaSegura(contrasenya)) {
      return "L'usuari no s'ha pogut registrar perquè la contrasenya no és prou segura.";
    }

    Anotadora anotadora = new Anotadora(
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

    usuaries.add(anotadora);

    return "L'usuari s'ha registrat amb èxit.";
  }

  public Boolean estaRegistrat(String nomUsuari) {
    return trobarUsuari(nomUsuari) != null;
  }

  public String identificarUsuari(String nomUsuari, String contrasenya) {
    Anotadora anotadora = trobarUsuari(nomUsuari);

    if (anotadora == null) {
      return "No s'ha pogut identificar l'usuari. No existeix cap usuari amb aquest nom.";
    }

    if (!anotadora.getContrasenya().equals(contrasenya)) {
      return "No s'ha pogut identificar l'usuari. La contrasenya no és correcta.";
    }

    return "Identificació correcta.";
  }

  public String trobarTasquesDisponibles(String expertesa) {
    List<TascaAnotacio<?>> tasquesDisponibles = new ArrayList<>();

      for (TascaAnotacio<?> tasca : tasques) {
          if (tasca.getExpertesaRequerida().equals(expertesa) && tasca.isTascaDisponible()) {
              tasquesDisponibles.add(tasca);
          }
      }

      String resultat;
      if(tasquesDisponibles.isEmpty()) {
        resultat = "NO hi ha tasques disponibles";
      }
      else{
        resultat = tasquesDisponibles.toString();
      }

      return resultat;
  }

  public void canviarEstatTasca(TascaAnotacio.EstatsTasca estatTascaNou, String titolTasca) {
    TascaAnotacio<?> tasca = trobarTasca(titolTasca);
    tasca.setEstatTasca(estatTascaNou);
  }

  public void eliminarTasca(String titolTasca)  {
    TascaAnotacio<?> tasca = trobarTasca(titolTasca);
    tasques.remove(tasca);
  }

  public void afegirTascaAnotacioImatge(String titol,
                                   String instrucions,
                                   String expertesaRequerida,
                                   float remuneracioPerDada,
                                   LocalDate dataLimit,
                                   List<EtiquetesCategoriques.TipusEtiquetesCategoriques> etiquetesPermeses){
    tasques.add(new TascaAnotacioImatge(titol, instrucions, expertesaRequerida,
            remuneracioPerDada, dataLimit, etiquetesPermeses));
  }

  public void afegirTascaAnotacioDocument(String titol,
                                   String instrucions,
                                   String expertesaRequerida,
                                   float remuneracioPerDada,
                                   LocalDate dataLimit,
                                   List<EtiquetesCategoriques.TipusEtiquetesCategoriques> etiquetesPermeses){
    tasques.add(new TascaAnotacioDocument(titol, instrucions, expertesaRequerida,
            remuneracioPerDada, dataLimit, etiquetesPermeses));
  }




  /* Auxiliars */

  private Anotadora trobarUsuari(String nomUsuari) {
    return usuaries.stream()
        .filter(anotadora -> anotadora.getNomUsuari().equals(nomUsuari))
        .findFirst()
        .orElse(null);
  }

  private boolean esBuit(String valor) {
    return valor == null || valor.isBlank();
  }

  private boolean esContrasenyaSegura(String contrasenya) {
    return contrasenya.length() >= 8
        && contrasenya.chars().anyMatch(Character::isUpperCase)
        && contrasenya.chars().anyMatch(Character::isLowerCase)
        && contrasenya.chars().anyMatch(Character::isDigit);
  }

  private String trobarAtributFaltant(
      String nomUsuari,
      String contrasenya,
      String passaportNom,
      String passaportCognoms,
      String passaportPais,
      String passaportNumero,
      LocalDate passaportCaducitat,
      String compteIban,
      String compteSwift,
      String email,
      String telefon,
      List<String> experteses) {
    if (esBuit(nomUsuari)) {
      return "nomUsuari";
    }
    if (esBuit(contrasenya)) {
      return "contrasenya";
    }
    if (esBuit(passaportNom)) {
      return "passaportNom";
    }
    if (esBuit(passaportCognoms)) {
      return "passaportCognoms";
    }
    if (esBuit(passaportPais)) {
      return "passaportPais";
    }
    if (esBuit(passaportNumero)) {
      return "passaportNumero";
    }
    if (passaportCaducitat == null) {
      return "passaportCaducitat";
    }
    if (esBuit(compteIban)) {
      return "compteIban";
    }
    if (esBuit(compteSwift)) {
      return "compteSwift";
    }
    if (esBuit(email)) {
      return "email";
    }
    if (esBuit(telefon)) {
      return "telèfon";
    }
    if (experteses == null || experteses.isEmpty() || experteses.stream().anyMatch(this::esBuit)) {
      return "experteses";
    }
    return null;
  }

  private TascaAnotacio<?> trobarTasca(String titolTasca) {
    Iterator<TascaAnotacio<?>> iterator = tasques.iterator();
    TascaAnotacio<?> tasca = null;

    while (iterator.hasNext()) {
      TascaAnotacio<?> actual = iterator.next();
      if (actual.getTitol().equals(titolTasca)) {
        tasca = actual;
      }
    }

    return tasca;
  }

  public String assignarTascaAAnotadora(String titolTasca, String nomUsuari) {
    TascaAnotacio<?> tasca = trobarTasca(titolTasca);
    Anotadora anotadora = trobarUsuari(nomUsuari);

    if (tasca == null) {
      return "No s'ha pogut assignar la tasca.";
    }

    if (anotadora == null) {
      return "No s'ha pogut assignar la tasca.";
    }

    if (!tasca.getExpertesaRequerida().contains(tasca.getTitol())) {
      return "No s'ha pogut assignar la tasca. L'usuari no té l'expertesa requerida.";
    }

    tasca.setAnotadoraAsignada(anotadora);
    return "La tasca s'ha assignat correctament a l'usuari.";
  }
}
