/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.sigmidal;

import static mx.ipn.escom.evolutivos.sigmidal.BackPropagation.BALANCE;

/**
 *
 * @author angel
 */
public class RedNeuronal {

    int nodosCapaK;
    int enterosCapaK;
    int nodosCapaJ;
    int enterosCapaJ;
    double tazaAprendizaje = 0.5;
    double[] entradas;
    Neurona[] neuronaK;
    Neurona[] neuronaJ;

    

    public RedNeuronal(int nodosCapaK, int enterosCapaK, int nodosCapaJ, int enterosCapaJ) {
        this.nodosCapaK = nodosCapaK;
        this.enterosCapaK = enterosCapaK;
        this.nodosCapaJ = nodosCapaJ;
        this.enterosCapaJ = enterosCapaJ;
    }

    public void crearRed() {
        neuronaJ = new Neurona[nodosCapaJ];
        neuronaK = new Neurona[nodosCapaK];
        for (int i = 0; i < nodosCapaJ; i++) {
            neuronaJ[i] = new Neurona(enterosCapaJ);
            neuronaJ[i].inicializarPesos();
        }
        for (int i = 0; i < nodosCapaK; i++) {
            neuronaK[i] = new Neurona(enterosCapaK);
            neuronaK[i].inicializarPesos();
        }
    }

    public void inicializarEntradasJAndActivar(double[] capa) {
        for (int i = 0; i < nodosCapaJ; i++) {
            System.arraycopy(capa, 0, neuronaJ[i].entradas, 0, capa.length);
            neuronaJ[i].activar();
        }
    }

    public void inicializarEntradasK(double[] capa) {
        for (int i = 0; i < nodosCapaK; i++) {
            for (int j = 0; j < nodosCapaJ; j++) {
                neuronaK[i].entradas[j] = neuronaJ[j].getActivacion();
            }
            neuronaK[i].entradas[nodosCapaJ - 1] = BALANCE;
        }
    }

    public double getActivacionK(int neura) {
        if (neuronaK[neura].getActivacion() >= .5) {
            return 1;
        }
        return 0;
    }

    public void errorCapaK(double aprender[][], int epoca) {
        for (int i = 0; i < nodosCapaK; i++) {
            neuronaK[i].setErrorNodo(
                    (aprender[i][epoca] - neuronaK[i].getActivacion())
                    * neuronaK[i].getActivacion()
                    * (1 - neuronaK[i].getActivacion()));
        }
    }

    public void pesosJK() {
        for (int i = 0; i < nodosCapaK; i++) {
            for (int j = 0; j < enterosCapaK; j++) {
                neuronaK[i].pesos[j] += (tazaAprendizaje * neuronaK[i].entradas[j]);
            }
        }
    }

    public void pesosIJ() {
        for (int i = 0; i < nodosCapaJ; i++) {
            for (int j = 0; j < enterosCapaJ; j++) {
                neuronaK[i].pesos[j] += tazaAprendizaje * neuronaJ[i].getErrorNodo() * neuronaJ[i].entradas[j];
            }
        }
    }

    public void errorCaoaJ() {
        double suma;
        double errorJ;
        for (int i = 0; i < nodosCapaJ; i++) {
            suma = 0;
            for (int j = 0; j < nodosCapaK; j++) {
                suma += (neuronaK[j].getErrorNodo() * neuronaK[j].pesos[i]);
            }
            errorJ = (neuronaJ[i].getActivacion() * (1 - neuronaJ[i].getActivacion()) * suma);
            neuronaJ[i].setErrorNodo(errorJ);
        }
    }
}
