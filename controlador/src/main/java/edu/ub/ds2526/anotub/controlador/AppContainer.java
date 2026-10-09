package edu.ub.ds2526.anotub.controlador;

public class AppContainer {
  private Controlador controlador;

  public AppContainer() {
        this.controlador = new Controlador();
    }

    public Controlador getControlador() {
        return controlador;
    }
}
