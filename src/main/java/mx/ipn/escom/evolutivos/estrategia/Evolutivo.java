package mx.ipn.escom.evolutivos.estrategia;

import java.awt.image.*;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.*;
import java.util.*;
import java.net.*;

/*
 **************************************************************************
 **
 **    Proyecto Reconocimiento de patrones - Ejemplo de algoritmo evolutivo VI
 **
 **************************************************************************
 **    Copyright (C) 2011 Samuel Graván Pérez
 **
 **    This program is free software; you can redistribute it and/or modify
 **    it under the terms of the GNU General Public License as published by
 **    the Free Software Foundation; either version 2 of the License, or
 **    (at your option) any later version.
 **
 **    This program is distributed in the hope that it will be useful,
 **    but WITHOUT ANY WARRANTY; without even the implied warranty of
 **    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 **    GNU General Public License for more details.
 **
 **    You should have received a copy of the GNU General Public License
 **    along with this program; if not, write to the Free Software
 **    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 **************************************************************************
 **
 **    Versión del programa v1.12
 **
 *************************************************************************/
public class Evolutivo extends Applet implements Runnable {

    Image m_image_a = null;
    Image m_image_e = null;
    Image m_image_i = null;
    Image m_image_o = null;
    Image m_image_u = null;
    Image m_image_b = null;

    Image m_image_ocr = null;

    Object m_pixelData_a = null;
    Object m_pixelData_e = null;
    Object m_pixelData_i = null;
    Object m_pixelData_o = null;
    Object m_pixelData_u = null;
    Object m_pixelData_b = null;

    Object m_pixelData_ocr = null;

    private String m_imageFilename;
    private Thread m_thread = null;

    private MyGifPixelGrabber m_gifPixelGrabber_a = null;
    private MyGifPixelGrabber m_gifPixelGrabber_e = null;
    private MyGifPixelGrabber m_gifPixelGrabber_i = null;
    private MyGifPixelGrabber m_gifPixelGrabber_o = null;
    private MyGifPixelGrabber m_gifPixelGrabber_u = null;
    private MyGifPixelGrabber m_gifPixelGrabber_b = null;

    private MyGifPixelGrabber m_gifPixelGrabber_ocr = null;

    private int a[][] = new int[8][5];
    private int e[][] = new int[8][4];
    private int i[][] = new int[8][3];
    private int o[][] = new int[8][5];
    private int u[][] = new int[8][6];
    private int b[][] = new int[8][1];

    private int ocr[][];

    Checkbox c1;
    Button b1, b2, b3, b4, b5, b6;
    TextField tf2, tf1;
    TextArea t1, t2;
    Label l2, l4;
    Thread t = null;
    boolean empezado = false;
    boolean entrenando = false;

    private long g = 0;

    Poblacion P = new Poblacion(60);

    Individuo mejor = new Individuo();

    private void cargarImagenes() {
        m_imageFilename = "a.gif";

        m_image_a = getImage(getCodeBase(), m_imageFilename);

        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(m_image_a, 0);

        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
        }

        mediaTracker.removeImage(m_image_a);
        m_gifPixelGrabber_a = new MyGifPixelGrabber(m_image_a);
        m_gifPixelGrabber_a.grabPixels();
        m_pixelData_a = m_gifPixelGrabber_a.getPixels();

        m_imageFilename = "e.gif";

        m_image_e = getImage(getCodeBase(), m_imageFilename);
        mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(m_image_e, 0);

        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
        }

        mediaTracker.removeImage(m_image_e);
        m_gifPixelGrabber_e = new MyGifPixelGrabber(m_image_e);
        m_gifPixelGrabber_e.grabPixels();
        m_pixelData_e = m_gifPixelGrabber_e.getPixels();

        m_imageFilename = "i.gif";

        m_image_i = getImage(getCodeBase(), m_imageFilename);
        mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(m_image_i, 0);

        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
        }

        mediaTracker.removeImage(m_image_i);
        m_gifPixelGrabber_i = new MyGifPixelGrabber(m_image_i);
        m_gifPixelGrabber_i.grabPixels();
        m_pixelData_i = m_gifPixelGrabber_i.getPixels();

        m_imageFilename = "o.gif";

        m_image_o = getImage(getCodeBase(), m_imageFilename);
        mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(m_image_o, 0);

        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
        }

        mediaTracker.removeImage(m_image_o);
        m_gifPixelGrabber_o = new MyGifPixelGrabber(m_image_o);
        m_gifPixelGrabber_o.grabPixels();
        m_pixelData_o = m_gifPixelGrabber_o.getPixels();

        m_imageFilename = "u.gif";

        m_image_u = getImage(getCodeBase(), m_imageFilename);
        mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(m_image_u, 0);

        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
        }

        mediaTracker.removeImage(m_image_u);
        m_gifPixelGrabber_u = new MyGifPixelGrabber(m_image_u);
        m_gifPixelGrabber_u.grabPixels();
        m_pixelData_u = m_gifPixelGrabber_u.getPixels();
    }

    public void init() {
        this.setBackground(new Color(235, 224, 199));

        Panel panelCentro = new Panel();

        panelCentro.add(new Label("Proyecto OCR v1.12"));

        panelCentro.add(new Label("URL imagen a procesar"));
        tf1 = new TextField("http://", 17);

        l2 = new Label("Máx.generaciones");
        tf2 = new TextField("1000", 2);

        panelCentro.add(tf1);
        panelCentro.add(l2);
        panelCentro.add(tf2);

        b2 = new Button("Entrenar");
        b4 = new Button("Cancelar");
        b1 = new Button("Probar");
        b5 = new Button("Limpiar");
        b6 = new Button("Pasar OCR");

        panelCentro.add(b2);
        panelCentro.add(b4);
        panelCentro.add(b1);
        panelCentro.add(b5);
        panelCentro.add(b6);

        this.add("North", panelCentro);

        Panel panelInfo = new Panel();

        panelInfo.setLayout(new GridLayout(2, 1));

        t1 = new TextArea("", 6, 115);
        t1.setEditable(false);

        t2 = new TextArea("", 6, 115);
        t2.setEditable(false);

        panelInfo.add(t1);
        panelInfo.add(t2);

        this.add("Center", panelInfo);

        empezado = false;

        cargarImagenes();

        if (m_gifPixelGrabber_a.isIndexed()) {
            byte[] pixelData = (byte[]) m_pixelData_a;

            for (int i = 0; m_gifPixelGrabber_a.isIndexed() && i < m_gifPixelGrabber_a.getHeight(); i++) {
                for (int j = 0; j < m_gifPixelGrabber_a.getWidth(); j++) {
                    a[i][j] = (int) pixelData[i * m_gifPixelGrabber_a.getWidth() + j];

                    if (a[i][j] > 0) {
                        a[i][j] = 1;
                    }
                }
            }
        }

        if (m_gifPixelGrabber_e.isIndexed()) {
            byte[] pixelData = (byte[]) m_pixelData_e;

            for (int i = 0; m_gifPixelGrabber_e.isIndexed() && i < m_gifPixelGrabber_e.getHeight(); i++) {
                for (int j = 0; j < m_gifPixelGrabber_e.getWidth(); j++) {
                    e[i][j] = (int) pixelData[i * m_gifPixelGrabber_e.getWidth() + j];

                    if (e[i][j] > 0) {
                        e[i][j] = 1;
                    }
                }
            }
        }

        if (m_gifPixelGrabber_i.isIndexed()) {
            byte[] pixelData = (byte[]) m_pixelData_i;

            for (int i = 0; m_gifPixelGrabber_i.isIndexed() && i < m_gifPixelGrabber_i.getHeight(); i++) {
                for (int j = 0; j < m_gifPixelGrabber_i.getWidth(); j++) {
                    this.i[i][j] = (int) pixelData[i * m_gifPixelGrabber_i.getWidth() + j];

                    if (this.i[i][j] > 0) {
                        this.i[i][j] = 1;
                    }
                }
            }
        }

        if (m_gifPixelGrabber_o.isIndexed()) {
            byte[] pixelData = (byte[]) m_pixelData_o;

            for (int i = 0; m_gifPixelGrabber_o.isIndexed() && i < m_gifPixelGrabber_o.getHeight(); i++) {
                for (int j = 0; j < m_gifPixelGrabber_o.getWidth(); j++) {
                    o[i][j] = (int) pixelData[i * m_gifPixelGrabber_o.getWidth() + j];

                    if (o[i][j] > 0) {
                        o[i][j] = 1;
                    }
                }
            }
        }

        if (m_gifPixelGrabber_u.isIndexed()) {
            byte[] pixelData = (byte[]) m_pixelData_u;

            for (int i = 0; m_gifPixelGrabber_u.isIndexed() && i < m_gifPixelGrabber_u.getHeight(); i++) {
                for (int j = 0; j < m_gifPixelGrabber_u.getWidth(); j++) {
                    u[i][j] = (int) pixelData[i * m_gifPixelGrabber_u.getWidth() + j];

                    if (u[i][j] > 0) {
                        u[i][j] = 1;
                    }
                }
            }
        }
    }

    public boolean action(Event evt, Object obj) {

        if (evt.target.equals(b1)) {
            try {
                long c = Long.parseLong(tf2.getText().trim());

                if (c < 0) {
                    throw new Exception();
                }

            } catch (Exception e) {
                t1.setText("¡El número de generaciones debe ser entero y mayor o igual que cero!\n" + t1.getText());
                return true;
            }
        }

        if (evt.target.equals(b5)) {
            g = 0;
            t1.setText("");
            t2.setText("");
            entrenando = false;
            empezado = false;
            mejor = new Individuo();
            if (t != null) {
                t.stop();
                t = null;
            }
        } else if (evt.target.equals(b6)) {
            if (!empezado) {
                t1.setText("");
                t2.setText("");
                empezado = true;
                entrenando = true;
                if (t == null) {
                    t = new Thread(this);
                    t.start();
                } else {
                    t.stop();
                    t = null;
                    t = new Thread(this);
                    t.start();
                }
            }
        } else if (evt.target.equals(b1)) {
            if (!empezado) {
                t1.setText("");
                t2.setText("");
                empezado = true;
                entrenando = false;
                if (t == null) {
                    t = new Thread(this);
                    t.start();
                } else {
                    t.stop();
                    t = null;
                    t = new Thread(this);
                    t.start();
                }
            }
        } else if (evt.target.equals(b2)) {
            if (!empezado) {
                t1.setText("");
                t2.setText("");
                empezado = false;
                entrenando = true;
                if (t == null) {
                    t = new Thread(this);
                    t.start();
                } else {
                    t.stop();
                    t = null;
                    t = new Thread(this);
                    t.start();
                }
            }
        } else if (evt.target.equals(b4)) {
            if (empezado || entrenando) {
                empezado = false;
                entrenando = false;
                t.stop();
                t1.setText("Se canceló el proceso...\n" + t1.getText());
                t = null;
            }
        }

        return true;
    }

    private void entrenar() {
        entrenando = true;

        if (t == null) {
            t = new Thread(this);
            t.start();
        } else {
            t.stop();
            t = null;
            t = new Thread(this);
            t.start();
        }
    }

    private void cargarOCR() {
        t1.setText("Cargamos la imagen a procesar por el OCR...\n");
        t2.setText("");

        try {
            m_image_ocr = getImage(new URL(tf1.getText()));
            MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(m_image_ocr, 23);

            try {
                mediaTracker.waitForID(23);
            } catch (InterruptedException e) {
                t1.setText(t1.getText() + "Se interrumpe la carga de la imagen...\n");
            }

            m_gifPixelGrabber_ocr = new MyGifPixelGrabber(m_image_ocr);
            m_gifPixelGrabber_ocr.grabPixels();
            m_pixelData_ocr = m_gifPixelGrabber_ocr.getPixels();

            if (m_gifPixelGrabber_ocr.isIndexed()) {
                byte[] pixelData = (byte[]) m_pixelData_ocr;

                int alto = m_gifPixelGrabber_ocr.getHeight();
                int ancho = m_gifPixelGrabber_ocr.getWidth();

                ocr = new int[alto][ancho];

                for (int i = 0; m_gifPixelGrabber_ocr.isIndexed() && i < m_gifPixelGrabber_ocr.getHeight(); i++) {
                    for (int j = 0; j < m_gifPixelGrabber_ocr.getWidth(); j++) {
                        ocr[i][j] = (int) pixelData[i * m_gifPixelGrabber_ocr.getWidth() + j];

                        if (ocr[i][j] >= 0) {
                            ocr[i][j] = 1;
                        } else {
                            ocr[i][j] = 0;
                        }
                    }
                }
            }

            t1.setText(t1.getText() + "Probamos el OCR con el mejor entrenado...f=" + mejor.getF() + " edad:" + mejor.getEdad() + "\n");

            m_image_ocr = getImageFromArray(ocr, m_gifPixelGrabber_ocr.getWidth(), m_gifPixelGrabber_ocr.getHeight());

            repaint();

            int j = 0;
            String s = "";
            for (int i = 0; i < m_gifPixelGrabber_ocr.getHeight(); i++, j = 0) {
                int blank = 0;

                StringBuilder letraAux = new StringBuilder();

                for (j = 0; j < m_gifPixelGrabber_ocr.getWidth();) {
                    int sw = 1;

                    for (int k1 = 0; k1 < 8 && i + k1 < m_gifPixelGrabber_ocr.getHeight(); k1++) {
                        for (int k2 = 0; k2 < 1 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                            if (ocr[i + k1][j + k2] == 1) {
                                sw = 0;
                            }
                        }
                    }

                    int cont = 0;

                    for (int k1 = 0; k1 < 5 && i + k1 < m_gifPixelGrabber_ocr.getHeight(); k1++) {
                        for (int k2 = 0; k2 < 3 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                            if (ocr[i + k1][j + k2] == 1) {
                                cont++;
                            }
                        }
                    }

                    int sw2 = 0;

                    if (i + 8 < m_gifPixelGrabber_ocr.getHeight()) {
                        for (int k2 = 0; k2 < 5 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                            if (ocr[i + 8][j + k2] == 1) {
                                sw2++;
                            }
                        }
                    }

                    if (i + 9 < m_gifPixelGrabber_ocr.getHeight()) {
                        for (int k2 = 0; k2 < 5 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                            if (ocr[i + 9][j + k2] == 1) {
                                sw2++;
                            }
                        }
                    }

                    int sw4 = 0;

                    if (i + 7 < m_gifPixelGrabber_ocr.getHeight()) {
                        for (int k2 = 0; k2 < 5 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                            if (ocr[i + 7][j + k2] == 1) {
                                sw4 = 1;
                            }
                        }
                    }

                    int sw3 = 1;

                    if (i - 1 > 0) {
                        for (int k2 = 0; k2 < 5 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                            if (ocr[i - 1][j + k2] == 1) {
                                sw3 = 0;
                            }
                        }
                    }

                    int sw6 = 0;

                    for (int k2 = 0; k2 < 3 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                        if (ocr[i][j + k2] == 1) {
                            sw6++;
                        }
                    }

                    if (sw == 0 && sw2 < 2 && sw3 == 1 && sw4 == 1 && cont > 1 && sw6 < 2) {
                        int ocr_aux[][] = new int[8][3];
                        int ocr_aux_5[][] = new int[8][5];

                        for (int k1 = 0; k1 < 8 && i + k1 < m_gifPixelGrabber_ocr.getHeight(); k1++) {
                            for (int k2 = 0; k2 < 3 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                                ocr_aux[k1][k2] = ocr[i + k1][j + k2];
                            }
                        }

                        for (int k1 = 0; k1 < 8 && i + k1 < m_gifPixelGrabber_ocr.getHeight(); k1++) {
                            for (int k2 = 0; k2 < 5 && j + k2 < m_gifPixelGrabber_ocr.getWidth(); k2++) {
                                ocr_aux_5[k1][k2] = ocr[i + k1][j + k2];
                            }
                        }

                        m_image_e = getImageFromArray(ocr_aux, 3, 8);
                        m_image_a = getImageFromArray(ocr_aux_5, 5, 8);

                        repaint();

                        StringBuilder letra = new StringBuilder();

                        float pa = RN.evaluarOCR(ocr_aux, mejor.getIndividuo(), letra);

                        if (pa > 0.5f) {
                            s += letra;
                            letraAux = letra;

                            if (letra.indexOf("i") >= 0) {
                                j += 3;
                            } else if (letra.indexOf("e") >= 0) {
                                j += 4;
                            } else if (letra.indexOf("u") >= 0) {
                                if (ocr[i + 6][j] == 0) {
                                    j += 6;
                                } else {
                                    j += 5;
                                }
                            } else {
                                j += 5;
                            }

                            t2.setText(s);
                        } else {
                            j++;
                        }
                    } else if (sw == 1) {
                        if (letraAux.indexOf("e") == -1 && letraAux.indexOf("o") == -1) {
                            s += " ";
                        }

                        letraAux.replace(0, 1, " ");

                        j++;
                    } else {
                        j++;
                    }
                }

                s += "\n";
            }

            t2.setText(s);
        } catch (Exception e) {
            t1.setText(t1.getText() + "Error al leer la image...\n");
        }

        entrenando = false;
        empezado = false;
    }

    public void run() {
        if (empezado && entrenando) {
            cargarOCR();
        } else if (empezado) {
            probarCPU();
        } else if (entrenando) {
            entrenarCPU();
        }
    }

    public void paint(Graphics g) {
        repaint();
    }

    private void probarCPU() {
        t1.setText("Probamos el mejor entrenado...f=" + mejor.getF() + " edad:" + mejor.getEdad());

        Random r = new Random();

        int aa[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                aa[i][j] = a[i][j];
            }
        }

        int va = RN.evaluar(aa, mejor.getIndividuo());

        if (va == 0) {
            t1.setText(t1.getText() + "\nLe presentamos una a, y él dice: a ¡Acertó!");
        } else {
            t1.setText(t1.getText() + "\nLe presentamos una a, y él dice: " + ((va == 1) ? "e" : (va == 2) ? "i" : (va == 3) ? "o" : (va == 4) ? "u" : "") + " ¡Falló!");
        }

        int ee[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                ee[i][j] = e[i][j];
            }
        }

        va = RN.evaluar(ee, mejor.getIndividuo());

        if (va == 1) {
            t1.setText(t1.getText() + "\nLe presentamos una e, y él dice: e ¡Acertó!");
        } else {
            t1.setText(t1.getText() + "\nLe presentamos una e, y él dice: " + ((va == 0) ? "a" : (va == 2) ? "i" : (va == 3) ? "o" : (va == 4) ? "u" : "") + " ¡Falló!");
        }

        int ii[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                ii[i][j] = this.i[i][j];
            }
        }

        for (int l = 0; l < r.nextInt(3); l++) {
            int i = r.nextInt(8);
            int j = r.nextInt(3);

            if (r.nextInt(100) + 1 <= 50) {
                ii[i][j] = 1;
            } else {
                ii[i][j] = 0;
            }
        }

        va = RN.evaluar(ii, mejor.getIndividuo());

        if (va == 2) {
            t1.setText(t1.getText() + "\nLe presentamos una i, y él dice: i ¡Acertó!");
        } else {
            t1.setText(t1.getText() + "\nLe presentamos una i, y él dice: " + ((va == 0) ? "a" : (va == 1) ? "e" : (va == 3) ? "o" : (va == 4) ? "u" : "") + " ¡Falló!");
        }

        int oo[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                oo[i][j] = o[i][j];
            }
        }

        va = RN.evaluar(oo, mejor.getIndividuo());

        if (va == 3) {
            t1.setText(t1.getText() + "\nLe presentamos una o, y él dice: o ¡Acertó!");
        } else {
            t1.setText(t1.getText() + "\nLe presentamos una o, y él dice: " + ((va == 0) ? "a" : (va == 1) ? "e" : (va == 2) ? "i" : (va == 4) ? "u" : "") + " ¡Falló!");
        }

        int uu[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                uu[i][j] = u[i][j + 1];
            }
        }

        va = RN.evaluar(uu, mejor.getIndividuo());

        if (va == 4) {
            t1.setText(t1.getText() + "\nLe presentamos una u, y él dice: u ¡Acertó!");
        } else {
            t1.setText(t1.getText() + "\nLe presentamos una u, y él dice: " + ((va == 0) ? "a" : (va == 1) ? "e" : (va == 2) ? "i" : (va == 3) ? "o" : "") + " ¡Falló!");
        }

        empezado = false;
    }

    private void entrenarCPU() {
        long c = Long.parseLong(tf2.getText().trim());
        Random r = new Random();

        while (!fParada(g, c, P)) {
            float media = mejor.getA() / mejor.getEdad();
            t1.setText("Generación..." + g + ", mejor f=" + mejor.getF() + " edad:" + mejor.getEdad() + " media:" + media + "\n" + t1.getText());

            Poblacion R = mutacion(P);

            java.util.List<Individuo> individuos_a = new ArrayList<Individuo>(R.getIndividuos());

            individuos_a.addAll(P.getIndividuos());

            java.util.List<Individuo> resultados = new ArrayList<Individuo>();

            int it = individuos_a.size();

            for (int k = 0; k < it; k++) {
                Individuo a = individuos_a.get(k);

                int pa = 0;

                for (int q = 0; q < 20; q++) {
                    pa += probarRoundSinRuido(a);
                }

                a.setA(((a.getA() != -100) ? a.getA() : 0) + pa);
                a.setF(pa);
                a.setEdad(a.getEdad() + 1);

                resultados.add(a);
            }

            R.setPoblacion(resultados);
            P.setPoblacion(seleccion(R));

            g++;
        }

        float media = mejor.getA() / mejor.getEdad();
        t1.setText("Fin entrenamiento, con el mejor f=" + mejor.getF() + " edad:" + mejor.getEdad() + " media:" + media + "\n" + t1.getText());
    }

    public static Image getImageFromArray(int[][] pixels2D, int width, int height) {

        int pixels[] = new int[width * height];
        int k = 0;

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (pixels2D[j][i] != 0) {
                    pixels[j * width + i] = Color.BLACK.getRGB();
                } else {
                    pixels[j * width + i] = Color.WHITE.getRGB();
                }
            }
        }

        BufferedImage pixelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixelImage.setRGB(0, 0, width, height, pixels, 0, width);

        return pixelImage;
    }

    private int probarRoundSinRuido(Individuo ind) {
        int res = 0;
        Random r = new Random();

        int aa[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                aa[i][j] = a[i][j];
            }
        }

        int va = RN.evaluar(aa, ind.getIndividuo());

        if (va == 0) {
            res++;
        } else {
            res--;
        }

        int ee[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                ee[i][j] = e[i][j];
            }
        }

        va = RN.evaluar(ee, ind.getIndividuo());

        if (va == 1) {
            res++;
        } else {
            res--;
        }

        int ii[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                ii[i][j] = this.i[i][j];
            }
        }

        for (int l = 0; l < r.nextInt(3); l++) {
            int i = r.nextInt(8);
            int j = r.nextInt(3);

            if (r.nextInt(100) + 1 <= 50) {
                ii[i][j] = 1;
            } else {
                ii[i][j] = 0;
            }
        }

        va = RN.evaluar(ii, ind.getIndividuo());

        if (va == 2) {
            res++;
        } else {
            res--;
        }

        int oo[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                oo[i][j] = o[i][j];
            }
        }

        va = RN.evaluar(oo, ind.getIndividuo());

        if (va == 3) {
            res++;
        } else {
            res--;
        }

        int uu[][] = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                uu[i][j] = u[i][j];
            }
        }

        va = RN.evaluar(uu, ind.getIndividuo());

        if (va == 4) {
            res++;
        } else {
            res--;
        }

        uu = new int[8][3];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                uu[i][j] = u[i][j + 1];
            }
        }

        va = RN.evaluar(uu, ind.getIndividuo());

        if (va == 4) {
            res++;
        } else {
            res--;
        }

        return res;
    }

    private java.util.List<Individuo> seleccion(Poblacion P) {
        java.util.List<Individuo> individuos = new ArrayList<Individuo>(P.getIndividuos());
        java.util.List<Individuo> resultados = new ArrayList<Individuo>();

        int s = individuos.size() - 1;

        Collections.sort(individuos, new ValorComparator());

        for (int j = s; j > s / 2; j--) {
            resultados.add(individuos.get(j));
        }

        mejor = null;
        mejor = new Individuo(individuos.get(s).getIndividuo(), individuos.get(s).getDi());
        mejor.setF(individuos.get(s).getF());
        mejor.setA(individuos.get(s).getA());
        mejor.setEdad(individuos.get(s).getEdad());

        float media = mejor.getA() / mejor.getEdad();

        return resultados;
    }

    private boolean fParada(long t, long c, Poblacion P) {
        if (empezado != false) {
            return true;
        }

        if (t > c && c != 0) {
            return true;
        }

        return false;
    }

    private Poblacion mutacion(Poblacion P) {
        Poblacion Q = new Poblacion();
        java.util.List<Individuo> individuos = new ArrayList<Individuo>(P.getIndividuos());
        Float e = 0.0001f;

        Random r = new Random();

        for (Individuo j : individuos) {
            java.util.List<Float> x = new ArrayList<Float>(j.getIndividuo());
            java.util.List<Float> d = new ArrayList<Float>(j.getDi());

            double ng = r.nextGaussian();
            for (int i = 0; i < x.size(); i++) {
                d.set(i, new Float(d.get(i) * Math.exp(0.0698f * ng + 0.034f * r.nextGaussian())));

                if (d.get(i) < e) {
                    d.set(i, e);
                }

                x.set(i, new Float(x.get(i) + d.get(i) * r.nextGaussian()));
            }

            Individuo n = new Individuo(x, d);

            Q.nuevoIndividuo(n);
        }

        return Q;
    }

    public void update(Graphics g) {
        g.drawImage(m_image_a, 100, 300, 100, 100, this);
        g.drawImage(m_image_e, 220, 300, 100, 100, this);
        g.drawImage(m_image_i, 340, 300, 100, 100, this);
        g.drawImage(m_image_o, 460, 300, 100, 100, this);
        g.drawImage(m_image_u, 580, 300, 100, 100, this);
        g.drawImage(m_image_ocr, 700, 270, 290, 290, this);
    }
}


