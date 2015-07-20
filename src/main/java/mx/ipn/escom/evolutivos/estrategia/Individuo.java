/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.estrategia;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author angel
 */
public class Individuo {

    private java.util.List<Float> xi;
    private java.util.List<Float> di;

    private float f = -100;
    private int edad = 0;
    private float a = -100;

    Individuo() {
        Random r = new Random();

        xi = new ArrayList<Float>();
        di = new ArrayList<Float>();

        for (long j = 0; j < 290; j++) {
            float x = r.nextFloat() * 0.2f;

            xi.add(x);
            di.add(0.05f);
        }
    }

    Individuo(java.util.List<Float> xi, java.util.List<Float> di) {
        this.xi = xi;
        this.di = di;
    }

    Individuo(java.util.List<Float> w) {
        this.xi = w;
    }

    public java.util.List<Float> getIndividuo() {
        return this.xi;
    }

    public void setIndividuo(java.util.List<Float> w) {
        this.xi = w;
    }

    public java.util.List<Float> getDi() {
        return this.di;
    }

    public void setDi(java.util.List<Float> d) {
        this.di = d;
    }

    public float getF() {
        return this.f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public float getA() {
        return this.a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
