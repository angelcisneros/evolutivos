/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.sigmidal;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 *
 * @author angel
 */
public class Sigmoidal {

    public static final double p = 1;
    
    public static double funcionSigmoide(double a){
        return (1/(1 + pow(E, -a/p)));
    }
    
}
