/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.sigmidal;

/**
 *
 * @author angel
 */
public class BackPropagation {

    public static final double BALANCE = -1;
    private static final int nodosJ = 5;
    private static final int nodosK = 4;
    private static final double CAPA_I[][] = {
        {1, 1, 1, 0, 1, 1, 1, BALANCE},
        {1, 0, 0, 0, 0, 0, 1, BALANCE},
        {1, 1, 0, 1, 1, 1, 0, BALANCE},
        {1, 1, 0, 1, 0, 1, 1, BALANCE},
        {1, 0, 1, 1, 0, 0, 1, BALANCE},
        {0, 1, 1, 1, 0, 1, 1, BALANCE},
        {0, 1, 1, 1, 1, 1, 1, BALANCE},
        {1, 1, 0, 0, 0, 0, 0, BALANCE},
        {1, 1, 1, 1, 1, 1, 1, BALANCE},
        {1, 1, 1, 1, 0, 1, 1, BALANCE}};
    private static final double aprender[][] = {
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
        {0, 0, 1, 1, 0, 0, 1, 1, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 1, 0, 1}
    };

    public static void main(String[] args) {

        int i = 0;
        int epoca;
        double error;
        int k = 0;
        boolean noIgual = false;
        RedNeuronal redNeuronal = new RedNeuronal(nodosK, 6, nodosJ, 8);
        
        while( i < CAPA_I.length && k < 6000){
            epoca = i;
            
            for(int c = 0; c < 7; c++){
                
            }
        }
        redNeuronal.crearRed();
//        redNeuronal.
    }
}
