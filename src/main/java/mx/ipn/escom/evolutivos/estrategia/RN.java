/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.estrategia;

/**
 *
 * @author angel
 */
public class RN {

    public static int evaluar(int[][] a, java.util.List<Float> wij) {
        int r = -1;
        float h;

        int k = 0;
        float hh = 0, ha = 0f, he = 0f, hi = 0f, ho = 0f, hu = 0f;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        ha = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        he = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        hi = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        ho = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        hu = tanh(hh);

        float max = -100f;

        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    if (ha > max) {
                        max = ha;
                        r = 0;
                    }
                    break;
                case 1:
                    if (he > max) {
                        max = he;
                        r = 1;
                    }
                    break;
                case 2:
                    if (hi > max) {
                        max = hi;
                        r = 2;
                    }
                    break;
                case 3:
                    if (ho > max) {
                        max = ho;
                        r = 3;
                    }
                    break;
                case 4:
                    if (hu > max) {
                        max = hu;
                        r = 4;
                    }
                    break;
            }
        }

        return r;
    }

    public static float evaluarOCR(int[][] a, java.util.List<Float> wij, StringBuilder letra) {
        int r = -1;
        float h;

        int k = 0;
        float hh = 0, ha = 0f, he = 0f, hi = 0f, ho = 0f, hu = 0f;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        ha = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        he = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        hi = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        ho = tanh(hh);

        hh = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                hh += wij.get(k) * a[i][j];
                k++;
            }
        }

        hh += wij.get(k);
        k++;

        hu = tanh(hh);

        float max = -1000f;

        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    if (ha > max) {
                        max = ha;
                        r = 0;
                        letra.replace(0, 1, "a");
                    }
                    break;
                case 1:
                    if (he > max) {
                        max = he;
                        r = 1;
                        letra.replace(0, 1, "e");
                    }
                    break;
                case 2:
                    if (hi > max) {
                        max = hi;
                        r = 2;
                        letra.replace(0, 1, "i");
                    }
                    break;
                case 3:
                    if (ho > max) {
                        max = ho;
                        r = 3;
                        letra.replace(0, 1, "o");
                    }
                    break;
                case 4:
                    if (hu > max) {
                        max = hu;
                        r = 4;
                        letra.replace(0, 1, "u");
                    }
                    break;
            }
        }

        return max;
    }

    public static float tanh(double u) {
        double a = Math.exp(u);
        double b = Math.exp(-u);

        double r = ((a - b) / (a + b));

        return new Float(r);
    }
}