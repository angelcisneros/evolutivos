/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.sigmidal;

import static java.lang.Math.random;
import lombok.Getter;
import lombok.Setter;
import static mx.ipn.escom.evolutivos.sigmidal.Sigmoidal.funcionSigmoide;

/**
 *
 * @author angel
 */
public class Neurona {

    private double activacion;
    private double errorNodo;
    public double[] entradas;
    public double[] pesos;    

    public Neurona(int entrada) {
        this.entradas = new double[entrada];
        this.pesos = new double[entrada];
    }

    public void activar() {
        double suma = 0;
        for (int i = 0; i < this.entradas.length; i++) {
            suma += entradas[i] * pesos[i];
        }
        activacion = funcionSigmoide(suma);
    }  
    public void verEntradas(){
        System.out.println("Entradas*****************************************");
        for(double e: entradas){
            System.out.println(e);
        }
    }
    public void verPesos(){
        System.out.println("Pesos********************************************");
        for(double e: pesos){
            System.out.println(e);
        }
    }
    public void verActivacion(){
        System.out.println("Activacion*****************************************");
        System.out.println(activacion);
        
    }   
    public void inicializarPesos(){
        for(int i = 0; i < pesos.length; i++){
            pesos[i] = random();
        }
    }

    public double getActivacion() {
        return activacion;
    }

    public void setActivacion(double activacion) {
        this.activacion = activacion;
    }

    public double getErrorNodo() {
        return errorNodo;
    }

    public void setErrorNodo(double errorNodo) {
        this.errorNodo = errorNodo;
    }
    
    
    
}
