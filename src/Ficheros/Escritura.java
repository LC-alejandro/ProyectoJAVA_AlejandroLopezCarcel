/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheros;

import Generico.Constantes;
import Generico.Mapa;
import static Generico.Utilidad.calcularNombreFichero;
import Personajes.Aliado;
import Personajes.BoinaVerde;
import Personajes.Espia;
import Personajes.Militar;
import Personajes.Obra;
import Personajes.Zapador;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class Escritura {

    //Métodos para abrir flujos de escritura
    public static PrintWriter abrirFlujoPW(FileWriter fw) {
        PrintWriter pw = null;
        return pw = new PrintWriter(fw);

    }

    public static FileWriter abrirFlujoFW() throws IOException {
        FileWriter fw = null;

        return fw = new FileWriter(calcularNombreFichero(Constantes.SIMULACION));

    }

    //Método para cerrar flujo de lectura
    public static void cerrarFlujosEscritura(FileWriter fw, PrintWriter pw) {

        if (pw != null) {
            pw.close();
        }
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println(Constantes.ERRORFICHERO);
                System.out.println(e.getMessage());
            }

        }

    }

    //Método para generar un registro de los aliados
    public static void escribirRegistroLog(PrintWriter pw) {
        //Declaración de variables
        Mapa mapa = Mapa.getInstancia();
        Militar militar;
        Iterator<Obra> obrasBv;
        Obra valor;
        //Nos recorremos la lista de personajes
        for (int i = 0; i < mapa.getPersonajes().size(); i++) {
            militar = mapa.getPersonajes().get(i);
            //Comprobamos que el militar es Aliado
            if (militar instanceof Aliado) {
                //Comprobamos que el militar es Espia
                if (militar instanceof Espia) {
                    //Método para pintar al militar y le pasamos el nombre del objeto
                    pintarPersonaje(Constantes.ESPIA, pw, militar);
                    //Pintamos la obra del espia (              CODIGO                                  :                               NOMBRE                                  :                                         AUTOR                         )                                                  
                    pw.println(Constantes.ABRIRPAREN + ((Espia) militar).getObra().getCodigo() + Constantes.DOSPUNTOSSINESP + ((Espia) militar).getObra().getNombre() + Constantes.DOSPUNTOSSINESP + ((Espia) militar).getObra().getAutor() + Constantes.CIERREPAREN);

                } else {
                    //Comprobamos que el militar es Zapador
                    if (militar instanceof Zapador) {
                        //Método para pintar al militar y le pasamos el nombre del objeto
                        pintarPersonaje(Constantes.ZAPADOR, pw, militar);
                        //Pintamos la obra del zapador(              CODIGO                                  :                               NOMBRE                                  :                                         AUTOR                         )     
                        pw.println(Constantes.ABRIRPAREN + ((Zapador) militar).getObra().getCodigo() + Constantes.DOSPUNTOSSINESP + ((Zapador) militar).getObra().getNombre() + Constantes.DOSPUNTOSSINESP + ((Zapador) militar).getObra().getAutor() + Constantes.CIERREPAREN);

                    } else {
                        //Comprobamos que el militar es BoinaVerde
                        if (militar instanceof BoinaVerde) {
                            obrasBv = ((BoinaVerde) militar).getObras().iterator();

                            //Método para pintar al militar y le pasamos el nombre del objeto
                            pintarPersonaje(Constantes.BOINAVERDE, pw, militar);
                            //Recorremos el conjunto de obras
                            while (obrasBv.hasNext()) {
                                valor = obrasBv.next();
                                //Pintamos las obras del boinaverde(  CODIGO                :                       NOMBRE                      :                         AUTOR                         )    
                                pw.println(Constantes.ABRIRPAREN + valor.getCodigo() + Constantes.DOSPUNTOSSINESP + valor.getNombre() + Constantes.DOSPUNTOSSINESP + valor.getAutor() + Constantes.CIERREPAREN);

                            }

                        }
                    }

                }

            }

        }

    }

    //Método para pintar los militares(Se reutiliza)
    public static void pintarPersonaje(String nombre, PrintWriter pw, Militar militar) {
        //Pintamos el militar (             NOMOBJETO           :                  NOMBRE                       :                       MARCA                   )
        pw.println(Constantes.ABRIRPAREN + nombre + Constantes.DOSPUNTOSSINESP + militar.getNombre() + Constantes.DOSPUNTOSSINESP + militar.getMarca() + Constantes.CIERREPAREN);

    }

    //Método para abrir y cerrar los flujos de escritura del registro
    public static void abrirFlujosEscrituraRegistro() {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(calcularNombreFichero(Constantes.REGISTRO));
            pw = new PrintWriter(fw);
            //Método para escribir el registro
            escribirRegistroLog(pw);
        } catch (FileNotFoundException e) {
            System.out.println(Constantes.NOENCUENTRA);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(Constantes.ERRORFICHERO);
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.out.println(Constantes.ERRORFICHERO);
                    System.out.println(e.getMessage());
                }

            }

        }

    }

}
