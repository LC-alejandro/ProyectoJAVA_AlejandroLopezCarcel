/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheros;

import Generico.Constantes;
import Generico.Mapa;
import Personajes.BoinaVerde;
import Personajes.Espia;
import Personajes.General;
import Personajes.Militar;
import Personajes.Obra;
import Personajes.Soldado;
import Personajes.Zapador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Lectura {

    private static void leerFichero(BufferedReader br) throws IOException {
        String linea;
        //Leemos la primera linea
        linea = br.readLine();
        //Se comprueba si no esta a null
        while (linea != null) {
            //Se comprueba que no empiece con -- lo que indica un comentario
            if (!linea.startsWith(Constantes.DOSGUIONES)) {
                //Se manda la linea al metodo para crear el objeto
                cargarMapa(linea);
            }
            //Se lee la siguiente linea
            linea = br.readLine();

        }

    }

    //Método para saber el objeto que tenemos que crear.Recibe el nombre del objeto
    public static int queElemento(String linea) {
        int posicion = 0, i = 0;
        boolean enc = false;
        //Vector con todos los nombre de los objetos
        String[] vector = {Constantes.MAPA, Constantes.BOINAVERDE, Constantes.ZAPADOR, Constantes.ESPIA, Constantes.GENERAL, Constantes.SOLDADO, Constantes.OBRAARTE};
        while ((enc == false) && (i < vector.length)) {
            if (linea.equalsIgnoreCase(vector[i])) {
                posicion = i;
                enc = true;

            }
            i++;
        }

        return posicion;
    }

    public static void cargarMapa(String linea) {
        //Declaración de variables
        String ruta;
        Mapa M = Mapa.getInstancia();
        int pElemento;
        String nombreObjeto;
        int obraCod, obraCelda;
        //Creamos un Stringtokenizer al que le pasamos la linea leida y le ponemos la separación, este caso #
        StringTokenizer cadena = new StringTokenizer(linea, Constantes.SEPARACION);

        //Se coge el primer token que tiene almacenado el nombre del objeto y se lo pasamos al metodo queElemento
        nombreObjeto = cadena.nextToken();

        //Recibimos una posición, esta posicion nos dirá el objeto que tenemos que crear
        pElemento = queElemento(nombreObjeto);
        //En este switch se compruba la posición recibida del queElemento
        switch (pElemento) {

            case 0:

                //Insertamos el tamaño del mapa
                M.crearMapa(cadena.nextToken());
                //Insertamos el disfraz en mapa
                M.cargarDisfrazEnMapa(Integer.parseInt(cadena.nextToken()));
                break;
            case 1:
                //Creamos el militar boinaverde (NOMBRE                   TURNO                             CELDAACTUAL                             MARCA)                                
                Militar bv = new BoinaVerde(cadena.nextToken(), Integer.parseInt(cadena.nextToken()), Integer.parseInt(cadena.nextToken()), cadena.nextToken().charAt(0));

                //Almacenamos la ruta
                ruta = cadena.nextToken();

                //Metodo para insertar el militar y la ruta
                crearMilitar(bv, ruta);
                break;
            case 2:
                //Creamos el militar zapador    (NOMBRE                   TURNO                             CELDAACTUAL                             MARCA) 
                Militar zapador = new Zapador(cadena.nextToken(), Integer.parseInt(cadena.nextToken()), Integer.parseInt(cadena.nextToken()), cadena.nextToken().charAt(0));

                //Almacenamos la ruta
                ruta = cadena.nextToken();

                //Metodo para insertar el militar y la ruta
                crearMilitar(zapador, ruta);
                break;
            case 3:
                //Creamos el militar espia  (NOMBRE                   TURNO                             CELDAACTUAL                             MARCA) 
                Militar espia = new Espia(cadena.nextToken(), Integer.parseInt(cadena.nextToken()), Integer.parseInt(cadena.nextToken()), cadena.nextToken().charAt(0));

                //Almacenamos la ruta
                ruta = cadena.nextToken();

                //Metodo para insertar el militar y la ruta
                crearMilitar(espia, ruta);
                break;
            case 4:
                //Creamos el militar general    (NOMBRE                   TURNO                             CELDAACTUAL                             MARCA) 
                Militar general = new General(cadena.nextToken(), Integer.parseInt(cadena.nextToken()), Integer.parseInt(cadena.nextToken()), cadena.nextToken().charAt(0));

                //Almacenamos la ruta
                ruta = cadena.nextToken();

                //Metodo para insertar el militar y la ruta
                crearMilitar(general, ruta);
                break;
            case 5:
                //Creamos el militar soldado    (NOMBRE                   TURNO                             CELDAACTUAL                             MARCA) 
                Militar soldado = new Soldado(cadena.nextToken(), Integer.parseInt(cadena.nextToken()), Integer.parseInt(cadena.nextToken()), cadena.nextToken().charAt(0));
                ruta = cadena.nextToken();

                //Metodo para insertar el militar y la ruta
                crearMilitar(soldado, ruta);
                break;
            case 6:
                //Almacenamos el codigo de la obra
                obraCod = Integer.parseInt(cadena.nextToken());

                //Almacenamos la celda de la obra
                obraCelda = Integer.parseInt(cadena.nextToken());

                //Creamos el objeto obra(CODIGO  CELDA          NOMBRE           AUTOR       )
                Obra obra1 = new Obra(obraCod, obraCelda, cadena.nextToken(), cadena.nextToken());

                //Insertamos la obra en el mapa
                M.insertarObra(obra1, obraCelda);
                break;

        }

    }

    public static void crearMilitar(Militar militar, String ruta) {
        //Declaración de variables
        char[] rutaChar;
        Mapa M = Mapa.getInstancia();
        // Almacenamos en un vector de char la ruta que en este caso es un String y en el método cargarRuta la ruta  se divide en chars
        rutaChar = cargarRuta(ruta);
        //Cargamos la ruta del militar
        militar.cargarMovimientos(rutaChar);
        //Insertamos el militar en la lista de militares
        M.insertarMilitar(militar);
        //Insertamos el militar en la lista de personajes del mapa
        M.insertarPersonaje(militar);

    }

    //Método para separar una ruta en String en chars e introducirlos en un vector
    public static char[] cargarRuta(String rutaMilitar) {

        char[] rutaChar = new char[rutaMilitar.length()];
        for (int i = 0; i < rutaChar.length; i++) {
            rutaChar[i] = rutaMilitar.charAt(i);

        }
        return rutaChar;

    }

    //Método para abrir y cerrar flujos de lectura.Recibe el nombre del fichero que tiene que leer.
    public static boolean cargarFichero(String mapa) throws FileNotFoundException {
        boolean fallo = false;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(mapa);
            br = new BufferedReader(fr);
            leerFichero(br);
        } catch (FileNotFoundException e) {
            System.out.println(Constantes.NOENCUENTRA);
            System.out.println(e.getMessage());
            fallo = true;

        } catch (IOException e) {
            System.out.println(Constantes.ERRORLECTURA);
            System.out.println(e.getMessage());
            fallo = true;

        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    fallo = true;
                }
            }
            if (fr != null) {
                try {
                    fr.close();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    fallo = true;
                }
            }

        }
        //Nos retorna un booleano que nos dice si hay algún fallo
        return fallo;
    }
}
