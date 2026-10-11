package edu.ub.ds2526.anotub.model;

import java.util.List;

public class Imatge extends Dada {

    // Atributs privats:
    private final int amplada;
    private final int alcada;
    // Llista de llistes d'enters, representa 1 Pixel = [R, G, B, X, Y] (color i posició)
    private List<List<Integer>> pixels;


    public Imatge(String titol, TascaAnotacio tascaAsociada, int amplada, int alcada, List<List<Integer>> pixels) {
        super(titol, tascaAsociada);
        this.amplada = amplada;
        this.alcada = alcada;
        this.pixels = pixels;
    }

    // Getters i Setters:
    public List<List<Integer>> getPixels() {
        return pixels;
    }

    public void setPixels(List<List<Integer>> pixels) {
        this.pixels = pixels;
    }

    public int getAlcada() {
        return alcada;
    }

    public int getAmplada() {
        return amplada;
    }

}
