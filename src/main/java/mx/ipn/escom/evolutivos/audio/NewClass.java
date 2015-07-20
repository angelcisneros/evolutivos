/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.audio;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mx.ipn.escom.evolutivos.audio.Tika.BANDA;
import static mx.ipn.escom.evolutivos.audio.Tika.FILE;
import org.openimaj.audio.SampleChunk;
import org.openimaj.audio.features.MFCC;
import org.openimaj.feature.DoubleFV;
import org.openimaj.video.xuggle.XuggleAudio;

/**
 *
 * @author angel
 */
public class NewClass {

    public static void main(String[] args) {
        List<double[]> matriz = new ArrayList<double[]>();
        try {
            matriz.add(vectores(FILE));
            matriz.add(vectores(BANDA));
            System.out.println("*** VECTOTES ***");
            for (double[] vs : matriz) {
                System.out.println(Arrays.toString(vs));
            }
        } catch (Exception ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static double[] vectores(String file) throws Exception {
        XuggleAudio xa = new XuggleAudio(new File(file));
        MFCC mfcc = new MFCC(xa);
        //DoubleFV dfv = mfcc.extractFeature(xa.nextSampleChunk());
        SampleChunk sc = mfcc.process(xa.nextSampleChunk());//xa.nextSampleChunk().getSampleBuffer()
        double[][] x = mfcc.calculateMFCC(sc.getSampleBuffer());
        for (double[] y : x) {
            System.out.println(Arrays.toString(y));
        }
        System.out.println(x[0].length);
        return mfcc.extractFeature(sc).normaliseFV().values;

    }
}
