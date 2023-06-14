/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import static Ficheros.Escritura.abrirFlujoFW;
import static Ficheros.Escritura.abrirFlujoPW;
import static Ficheros.Escritura.abrirFlujosEscrituraRegistro;
import static Ficheros.Escritura.cerrarFlujosEscritura;
import static Ficheros.Lectura.cargarFichero;
import Personajes.Militar;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrega3_LopezCarcel_Alejandro {

    //SIMULACIÓN COMMANDOS: BEHIND MONUMENTS MEN
    public static void simulacion() {
        Militar militar;
        int turno = 0;
        /**
         * ** Abrimos flujos para escritura de fichero simulación **
         */
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = abrirFlujoFW();
            pw = abrirFlujoPW(fw);
        } catch (FileNotFoundException e) {
            System.out.println(Constantes.NOENCUENTRA);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(Constantes.ERRORFICHERO);
            System.out.println(e.getMessage());
        }

        /**
         * ** Escribir en fichero simulación **
         */
        pw.println(Constantes.TURNO + turno);
        System.out.println(Constantes.TURNO + turno);
        Mapa.getInstancia().pintarMatriz(pw);

        while (Mapa.getInstancia().getNumAliadosMapa() != Mapa.getInstancia().getNumAliadosPunto()) {

            turno++;
            pw.println(Constantes.TURNO + turno);
            System.out.println(Constantes.TURNO + turno);
            Mapa.getInstancia().setTurno(turno);
            for (int i = 0; i < Mapa.getInstancia().getTamanoPers(); i++) {
                /**
                 * ** Incluye aquí el tratamiento del Militar **
                 */

                militar = Mapa.getInstancia().getMilitar(i);
                militar.realizarAccion(pw);
            }

            /**
             * ** Escribir en fichero simulación *
             */
            Mapa.getInstancia().pintarMatriz(pw);

        }


        /* ** Cerramos flujos para escritura de fichero simulación **/
        cerrarFlujosEscritura(fw, pw);
        // Escribimos el registro
        abrirFlujosEscrituraRegistro();

    }

    //Método para pedir la opción del menú
    public static int pedirOpc() {
        Scanner entrada = new Scanner(System.in);
        int opc = 0;
        boolean puede = false;
        do {

            try {

                opc = entrada.nextInt();
                puede = false;
            } catch (InputMismatchException e) {
                puede = true;
                entrada.nextLine();
                System.out.println(Constantes.ERRORMENU);
                System.out.println(Constantes.OPCION);
            }

        } while ((puede));
        return opc;
    }

    //Método para cagar los datos de los mapas
    public static boolean cargarDatos(int opc) throws FileNotFoundException {
        boolean fallo = false;
        switch (opc) {
            case 1:
                fallo = cargarFichero(Constantes.INICIO3X3);
                break;
            case 2:
                fallo = cargarFichero(Constantes.INICIO5X5);
                break;
            case 3:
                fallo = cargarFichero(Constantes.INICIO8X8);
                break;

        }

        return fallo;

    }

    //Método que nos muestra si la ejecución ha ido correctamente
    public static void todoBien(boolean fallo, int opc) {

        //Panel para indicar que todo ha ido correctamente
        if (!fallo) {
            switch (opc) {
                case 1:
                    System.out.println(Constantes.GUIONESSEGUIDOS);
                    System.out.println(Constantes.EJECUCION + Constantes.TRESPORTRES + Constantes.EXITO);
                    System.out.println(Constantes.GUIONESSEGUIDOS);
                    break;
                case 2:
                    System.out.println(Constantes.GUIONESSEGUIDOS);
                    System.out.println(Constantes.EJECUCION + Constantes.CINCOPORCINCO + Constantes.EXITO);
                    System.out.println(Constantes.GUIONESSEGUIDOS);
                    break;
                case 3:
                    System.out.println(Constantes.GUIONESSEGUIDOS);
                    System.out.println(Constantes.EJECUCION + Constantes.OCHOPOROCHO + Constantes.EXITO);
                    System.out.println(Constantes.GUIONESSEGUIDOS);
                    break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        /**
         * ****** Muestra aquí tu menú *************
         */
        boolean fallo = false;
        boolean repetir = false;
        System.out.println(Constantes.OPC1);
        System.out.println(Constantes.OPC2);
        System.out.println(Constantes.OPC3);
        System.out.println(Constantes.OPC4);

        do {
            repetir = false;
            System.out.println(Constantes.OPCION);
            int opc = pedirOpc();
            switch (opc) {
                case 1:
                case 2:
                case 3:
                    fallo = cargarDatos(opc);
                    if (!fallo) {
                        simulacion();
                        todoBien(fallo, opc);

                    } else {
                        System.out.println(Constantes.FALLOLECTURA);
                    }

                    break;
                case 4:
                    /**
                     * ****** Tratamiento opción 4 *************
                     */
                    System.out.println(Constantes.HASTALUEGO);
                    break;
                default:
                    repetir = true;
                /* ****** Tratamiento default *************/
            }
        } while (repetir);

    }

}
