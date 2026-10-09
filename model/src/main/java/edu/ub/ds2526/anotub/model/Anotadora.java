package edu.ub.ds2526.anotub.model;

import java.time.LocalDate;
import java.util.List;

public class Anotadora {
  private final String nomUsuari;
  private final String contrasenya;
  private final String passaportNom;
  private final String passaportCognoms;
  private final String passaportPais;
  private final String passaportNumero;
  private final LocalDate passaportCaducitat;
  private final String compteIban;
  private final String compteSwift;
  private final String email;
  private final String telefon;
  private final List<String> experteses;

  public Anotadora(
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
    this.nomUsuari = nomUsuari;
    this.contrasenya = contrasenya;
    this.passaportNom = passaportNom;
    this.passaportCognoms = passaportCognoms;
    this.passaportPais = passaportPais;
    this.passaportNumero = passaportNumero;
    this.passaportCaducitat = passaportCaducitat;
    this.compteIban = compteIban;
    this.compteSwift = compteSwift;
    this.email = email;
    this.telefon = telefon;
    this.experteses = experteses;
  }

  public String getNomUsuari() {
    return nomUsuari;
  }

  public String getContrasenya() {
    return contrasenya;
  }

  public String getPassaportNom() {
    return passaportNom;
  }

  public String getPassaportCognoms() {
    return passaportCognoms;
  }

  public String getPassaportPais() {
    return passaportPais;
  }

  public String getPassaportNumero() {
    return passaportNumero;
  }

  public LocalDate getPassaportCaducitat() {
    return passaportCaducitat;
  }

  public String getCompteIban() {
    return compteIban;
  }

  public String getCompteSwift() {
    return compteSwift;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefon() {
    return telefon;
  }

  public List<String> getExperteses() {
    return experteses;
  }
}
