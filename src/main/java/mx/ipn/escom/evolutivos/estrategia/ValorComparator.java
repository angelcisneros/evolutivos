/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.estrategia;

import java.util.Comparator;



/**
 *
 * @author angel
 */
public 

class ValorComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Individuo a = (Individuo) o1;
        Individuo b = (Individuo) o2;

        float mediaA = a.getA() / a.getEdad();
        float mediaB = b.getA() / b.getEdad();

        int r = Float.compare(a.getF(), b.getF());

        if (r == 0) {
            r = Float.compare(a.getEdad(), b.getEdad());
        }

        if (r == 0) {
            r = Float.compare(mediaA, mediaB);
        }

        return r;
    }

  public boolean equals(Object o) {
    return this == o;
  }
}
