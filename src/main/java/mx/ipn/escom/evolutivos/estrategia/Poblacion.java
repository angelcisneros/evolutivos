/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.estrategia;

import java.util.ArrayList;

/**
 *
 * @author angel
 */
public class Poblacion {

    private java.util.List<Individuo> individuos;

    Poblacion() {
        individuos = new ArrayList<Individuo>();
    }

    Poblacion(long i) {
        individuos = new ArrayList<Individuo>();

        for (long j = 0; j < i; j++) {
            Individuo I = new Individuo();
            individuos.add(I);
        }
    }

    public java.util.List<Individuo> getIndividuos() {
        return this.individuos;
    }

    public void nuevoIndividuo(Individuo i) {
        this.individuos.add(i);
    }

    public void setPoblacion(java.util.List<Individuo> individuos) {
        this.individuos = individuos;
    }
}
