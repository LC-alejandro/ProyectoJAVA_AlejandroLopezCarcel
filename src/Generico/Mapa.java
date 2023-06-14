/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import Personajes.Aliado;
import Personajes.Militar;
import Personajes.Obra;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Mapa {

    private static Mapa miMapa;  //Atributo del patrón Singleton
    private int[][] adyacencia;  //Matriz de adyacencia

    /**
     * ****** Incluye aquí el resto de atributos *************
     *
     */
    private int turno;
    private Celda[][] matriz;
    private int numAliadosPunto;
    private int numAliadosMapa;
    private int filas;
    private int columnas;
    private ArrayList<Militar> personajes;

    /* Constructor clase Mapa
       Será privado, ya que estamos utilizando el patrón Singleton (así obligamos 
       a utilizar el método getInstancia, asegurando de esta manera que exista 
       una única instancia */
    private Mapa() {
        /* Incluye aquí el código del Constructor */
        numAliadosPunto = 0;
        numAliadosMapa = 0;
        filas = 0;
        columnas = 0;
        personajes = new ArrayList<>();

    }

    //Método para crear la instancia del patrón singleton
    public static Mapa getInstancia() {
        if (miMapa == null) {
            miMapa = new Mapa();
        }
        return miMapa;
    }

    /* Crea el mapa del tamaño adecuado, según el parámetro recibido */
    public void crearMapa(String tamMapa) {
        if (tamMapa.equalsIgnoreCase(Constantes.TRESPORTRES)) {
            this.filas = Constantes.TRES;
            this.columnas = Constantes.TRES;
        } else {
            if (tamMapa.equalsIgnoreCase(Constantes.CINCOPORCINCO)) {
                this.filas = Constantes.CINCO;
                this.columnas = Constantes.CINCO;
            } else {
                this.filas = Constantes.OCHO;
                this.columnas = Constantes.OCHO;
            }
        }

        this.matriz = new Celda[this.filas][this.columnas];

        int cont = 0;
        //Inicializamos celdas
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                this.matriz[i][j] = new Celda(cont);
                cont++;
            }
        }

        //Cargamos punto de encuentro
        this.matriz[this.filas - 1][this.columnas - 1].setPuntoDeEncuentro(true);

        this.adyacencia = new int[this.filas * this.filas][this.columnas * this.columnas];
        //Rellenamos matriz de adyacencia con 0s
        for (int i = 0; i < this.adyacencia.length; i++) {
            for (int j = 0; j < this.adyacencia[i].length; j++) {
                this.adyacencia[i][j] = 0;
            }
        }

        //Actualizamos las celdas que tienen 1s dependiendo del mapa
        if (tamMapa.equalsIgnoreCase(Constantes.TRESPORTRES)) {
            crearMapa3x3();
        } else {
            if (tamMapa.equalsIgnoreCase(Constantes.CINCOPORCINCO)) {
                crearMapa5x5();
            } else {
                crearMapa8x8();
            }
        }
    }

    /* "Abre" los caminos en la madtriz de adyacencia del Mapa 3x3 */
    private void crearMapa3x3() {
        //Actualizamos las celdas que tienen 1s
        this.adyacencia[0][1] = 1;
        this.adyacencia[0][3] = 1;

        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;
        this.adyacencia[1][4] = 1;

        this.adyacencia[2][1] = 1;

        this.adyacencia[3][0] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][6] = 1;

        this.adyacencia[4][1] = 1;
        this.adyacencia[4][3] = 1;
        this.adyacencia[4][7] = 1;

        this.adyacencia[6][3] = 1;
        this.adyacencia[6][7] = 1;

        this.adyacencia[7][4] = 1;
        this.adyacencia[7][6] = 1;
        this.adyacencia[7][8] = 1;

        this.adyacencia[8][7] = 1;
    }

    /* "Abre" los caminos en la madtriz de adyacencia del Mapa 5x5 */
    private void crearMapa5x5() {
        //Actualizamos las celdas que tienen 1s
        this.adyacencia[0][1] = 1;

        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;

        this.adyacencia[2][1] = 1;
        this.adyacencia[2][3] = 1;
        this.adyacencia[2][7] = 1;

        this.adyacencia[3][2] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][8] = 1;

        this.adyacencia[4][3] = 1;
        this.adyacencia[4][9] = 1;

        this.adyacencia[7][2] = 1;
        this.adyacencia[7][8] = 1;
        this.adyacencia[7][12] = 1;

        this.adyacencia[8][3] = 1;
        this.adyacencia[8][7] = 1;
        this.adyacencia[8][9] = 1;

        this.adyacencia[9][4] = 1;
        this.adyacencia[9][8] = 1;

        this.adyacencia[10][11] = 1;
        this.adyacencia[10][15] = 1;

        this.adyacencia[11][10] = 1;
        this.adyacencia[11][12] = 1;

        this.adyacencia[12][7] = 1;
        this.adyacencia[12][11] = 1;
        this.adyacencia[12][17] = 1;

        this.adyacencia[15][10] = 1;
        this.adyacencia[15][20] = 1;

        this.adyacencia[17][12] = 1;
        this.adyacencia[17][22] = 1;

        this.adyacencia[20][15] = 1;

        this.adyacencia[22][17] = 1;
        this.adyacencia[22][23] = 1;

        this.adyacencia[23][22] = 1;
        this.adyacencia[23][24] = 1;

        this.adyacencia[24][23] = 1;
    }

    /* "Abre" los caminos en la madtriz de adyacencia del Mapa 8x8 */
    private void crearMapa8x8() {
        //Actualizamos las celdas que tienen 1s
        this.adyacencia[0][1] = 1;
        this.adyacencia[0][8] = 1;

        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;

        this.adyacencia[2][1] = 1;
        this.adyacencia[2][3] = 1;
        this.adyacencia[2][10] = 1;

        this.adyacencia[3][2] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][11] = 1;

        this.adyacencia[4][3] = 1;
        this.adyacencia[4][5] = 1;
        this.adyacencia[4][12] = 1;

        this.adyacencia[5][4] = 1;
        this.adyacencia[5][6] = 1;

        this.adyacencia[6][5] = 1;
        this.adyacencia[6][7] = 1;

        this.adyacencia[7][6] = 1;

        this.adyacencia[8][0] = 1;
        this.adyacencia[8][16] = 1;

        this.adyacencia[10][2] = 1;
        this.adyacencia[10][11] = 1;
        this.adyacencia[10][18] = 1;

        this.adyacencia[11][3] = 1;
        this.adyacencia[11][10] = 1;
        this.adyacencia[11][12] = 1;
        this.adyacencia[11][19] = 1;

        this.adyacencia[12][4] = 1;
        this.adyacencia[12][11] = 1;
        this.adyacencia[12][20] = 1;

        this.adyacencia[16][8] = 1;
        this.adyacencia[16][24] = 1;

        this.adyacencia[18][10] = 1;
        this.adyacencia[18][19] = 1;
        this.adyacencia[18][26] = 1;

        this.adyacencia[19][11] = 1;
        this.adyacencia[19][18] = 1;
        this.adyacencia[19][20] = 1;
        this.adyacencia[19][27] = 1;

        this.adyacencia[20][12] = 1;
        this.adyacencia[20][19] = 1;
        this.adyacencia[20][21] = 1;
        this.adyacencia[20][28] = 1;

        this.adyacencia[21][20] = 1;
        this.adyacencia[21][22] = 1;
        this.adyacencia[21][29] = 1;

        this.adyacencia[22][21] = 1;
        this.adyacencia[22][23] = 1;
        this.adyacencia[22][30] = 1;

        this.adyacencia[23][22] = 1;
        this.adyacencia[23][31] = 1;

        this.adyacencia[24][16] = 1;
        this.adyacencia[24][25] = 1;
        this.adyacencia[24][32] = 1;

        this.adyacencia[25][24] = 1;
        this.adyacencia[25][26] = 1;
        this.adyacencia[25][33] = 1;

        this.adyacencia[26][18] = 1;
        this.adyacencia[26][25] = 1;
        this.adyacencia[26][27] = 1;

        this.adyacencia[27][19] = 1;
        this.adyacencia[27][26] = 1;
        this.adyacencia[27][28] = 1;
        this.adyacencia[27][35] = 1;

        this.adyacencia[28][20] = 1;
        this.adyacencia[28][27] = 1;
        this.adyacencia[28][29] = 1;

        this.adyacencia[29][21] = 1;
        this.adyacencia[29][28] = 1;
        this.adyacencia[29][30] = 1;

        this.adyacencia[30][22] = 1;
        this.adyacencia[30][29] = 1;
        this.adyacencia[30][31] = 1;

        this.adyacencia[31][23] = 1;
        this.adyacencia[31][30] = 1;
        this.adyacencia[31][39] = 1;

        this.adyacencia[32][24] = 1;
        this.adyacencia[32][33] = 1;
        this.adyacencia[32][40] = 1;

        this.adyacencia[33][25] = 1;
        this.adyacencia[33][32] = 1;

        this.adyacencia[35][27] = 1;
        this.adyacencia[35][43] = 1;

        this.adyacencia[39][31] = 1;
        this.adyacencia[39][47] = 1;

        this.adyacencia[40][32] = 1;
        this.adyacencia[40][48] = 1;

        this.adyacencia[43][35] = 1;
        this.adyacencia[43][51] = 1;

        this.adyacencia[46][47] = 1;
        this.adyacencia[46][54] = 1;

        this.adyacencia[47][39] = 1;
        this.adyacencia[47][46] = 1;
        this.adyacencia[47][55] = 1;

        this.adyacencia[48][40] = 1;
        this.adyacencia[48][49] = 1;
        this.adyacencia[48][56] = 1;

        this.adyacencia[49][48] = 1;
        this.adyacencia[49][50] = 1;
        this.adyacencia[49][57] = 1;

        this.adyacencia[50][49] = 1;
        this.adyacencia[50][51] = 1;

        this.adyacencia[51][43] = 1;
        this.adyacencia[51][50] = 1;
        this.adyacencia[51][52] = 1;
        this.adyacencia[51][59] = 1;

        this.adyacencia[52][51] = 1;
        this.adyacencia[52][53] = 1;
        this.adyacencia[52][60] = 1;

        this.adyacencia[53][52] = 1;
        this.adyacencia[53][54] = 1;
        this.adyacencia[53][61] = 1;

        this.adyacencia[54][46] = 1;
        this.adyacencia[54][53] = 1;
        this.adyacencia[54][55] = 1;
        this.adyacencia[54][62] = 1;

        this.adyacencia[55][47] = 1;
        this.adyacencia[55][54] = 1;
        this.adyacencia[55][63] = 1;

        this.adyacencia[56][48] = 1;
        this.adyacencia[56][57] = 1;

        this.adyacencia[57][49] = 1;
        this.adyacencia[57][56] = 1;

        this.adyacencia[59][51] = 1;
        this.adyacencia[59][60] = 1;

        this.adyacencia[60][52] = 1;
        this.adyacencia[60][59] = 1;
        this.adyacencia[60][61] = 1;

        this.adyacencia[61][53] = 1;
        this.adyacencia[61][60] = 1;
        this.adyacencia[61][62] = 1;

        this.adyacencia[62][54] = 1;
        this.adyacencia[62][61] = 1;
        this.adyacencia[62][63] = 1;

        this.adyacencia[63][55] = 1;
        this.adyacencia[63][62] = 1;
    }

    //Carga el difraz del Espía en el Mapa. Se cargará en la celda indicada por parámetros 
    public void cargarDisfrazEnMapa(int idCelda) {
        int fila = Utilidad.calcularFila(idCelda);
        int columna = Utilidad.calcularColumna(idCelda);
        //Cargamos el disfraz en la celda [fila, columna]
        this.matriz[fila][columna].setDisfraz(true);
    }

    /**
     * ******** Incluye aquí el resto de métodos ************
     */
    //Métodos getter y setter
    public static Mapa getMiMapa() {
        return miMapa;
    }

    public void setAdyacencia(int[][] adyacencia) {
        this.adyacencia = adyacencia;
    }

    public int[][] getAdyacencia() {
        return adyacencia;
    }

    public int getTurno() {
        return turno;
    }

    public Celda[][] getMatriz() {
        return matriz;
    }

    public static void setMiMapa(Mapa miMapa) {
        Mapa.miMapa = miMapa;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setMatriz(Celda[][] matriz) {
        this.matriz = matriz;
    }

    public int getNumAliadosPunto() {
        return numAliadosPunto;
    }

    public int getNumAliadosMapa() {
        return numAliadosMapa;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public ArrayList<Militar> getPersonajes() {
        return personajes;
    }

    public void setNumAliadosPunto(int numAliadosPunto) {
        this.numAliadosPunto = numAliadosPunto;
    }

    public void setNumAliadosMapa(int numAliadosMapa) {
        this.numAliadosMapa = numAliadosMapa;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setPersonajes(ArrayList<Militar> personajes) {
        this.personajes = personajes;
    }

    /**
     * ******** Incluye aquí el resto de métodos ************
     */
    public void aumentarNumAliadosPunto() {
        this.numAliadosPunto++;
    }

    //Insertar Militar en la lista de personajes
    public void insertarPersonaje(Militar militar) {

        personajes.add(militar);
        if (militar instanceof Aliado) {
            numAliadosMapa++;

        }

    }

    // Retornar el tamaño de la lista de los personajes
    public int getTamanoPers() {
        return personajes.size();

    }

    //Retornar Militar en la posición
    public Militar getMilitar(int posicion) {

        Militar militar = personajes.get(posicion);
        return militar;

    }

    //Metodo para insertar una obra
    public void insertarObra(Obra obra, int IdCelda) {
        int fila = Utilidad.calcularFila(IdCelda);
        int columna = Utilidad.calcularColumna(IdCelda);
        this.matriz[fila][columna].insertarObra(obra);

    }

    //Metodo para borrar una obra
    public void borrarObra(Obra obra) {
        int fila = Utilidad.calcularFila(obra.getCeldaActual());
        int columna = Utilidad.calcularColumna(obra.getCeldaActual());
        this.matriz[fila][columna].borrarObra(obra);

    }

    //Metodo para borrar militar
    public void borrarMilitar(String nombre, int IdCelda) {
        int fila = Utilidad.calcularFila(IdCelda);
        int columna = Utilidad.calcularColumna(IdCelda);
        this.matriz[fila][columna].borrarMilitar(nombre);

    }

    //Metodo para insertar militar
    public void insertarMilitar(Militar militar) {
        int fila = Utilidad.calcularFila(militar.getIdCelda());
        int columna = Utilidad.calcularColumna(militar.getIdCelda());
        this.matriz[fila][columna].insertarMilitar(militar);

    }

    //Metodo para retornar la celda
    public Celda retornarCelda(int fila, int columna) {
        Celda objCelda;

        objCelda = this.matriz[fila][columna];

        return objCelda;
    }

    //Método que comprueba si se puede avanzar o no a una celda destino
    public boolean hayCamino(int celdaOrigen, int celdaDestino) {
        boolean sePuede = false;
        int celda = 0;

        try {
            celda = this.adyacencia[celdaOrigen][celdaDestino];
            if (celda == 1) {
                sePuede = true;

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            sePuede = false;
        }

        return sePuede;
    }

    /**
     * ************************************************************************************
     */
    /**
     * ************************************************************************************
     */
    /**
     * **************************** MÉTODOS PARA PINTAR MAPA
     * ******************************
     */
    /**
     * ************************************************************************************
     */
    /**
     * ************************************************************************************
     */

    /* Retorna true si la celda NO es accesible (hay un edificio, un árbol, ...). 
       El método es privado porque sólo se utiliza en la clase Mapa */
    private boolean celdaNoAccesible(int idCelda) {
        boolean noAccesible = true;
        int j = 0;
        while ((j < (filas * filas)) && (noAccesible)) {
            if (this.adyacencia[idCelda][j] == 1) {
                noAccesible = false;
            }
            j++;
        }
        return noAccesible;
    }

    /* Pinta la primera línea del mapa. El método es privado porque sólo se utiliza 
       en la clase Mapa */
    private void pintarLineaSuperior(int i, PrintWriter pw) {
        int celdaOrigen, celdaDestino, j;
        if (i == 0) {
            //Línea superior. Empezamos dejando un hueco para dejas un primer hueco para pintar la línea izquierda
            pw.print(" ");
            System.out.print(" ");
            for (j = 0; j < this.matriz[0].length; j++) {
                pw.print("------- ");
                System.out.print("------- ");
            }
        } else {
            pw.print("|");
            System.out.print("|");
            for (j = 0; j < this.matriz[i].length - 1; j++) {
                //Calculamos celdaOrigen y celdaDestino
                celdaOrigen = (filas * i) + j;
                celdaDestino = celdaOrigen - filas;
                if (hayCamino(celdaOrigen, celdaDestino)) {
                    pw.print("        ");
                    System.out.print("        ");
                } else {
                    pw.print("------- ");
                    System.out.print("------- ");
                }
            }
            //Calculamos celdaOrigen y celdaDestino
            celdaOrigen = (filas * i) + j;
            celdaDestino = celdaOrigen - filas;
            if (hayCamino(celdaOrigen, celdaDestino)) {
                pw.print("       |");
                System.out.print("       |");
            } else {
                pw.print("-------|");
                System.out.print("-------|");

            }
        }
        //Ponemos el cursor en la siguiente línea
        pw.print("\n");
        System.out.print("\n");
    }

    /* Pinta línea inferior del mapa. El método es privado porque sólo se utiliza 
       en la clase Mapa */
    private void pintarLineaInferior(PrintWriter pw) {
        //Línea inferior. Empezamos dejando un hueco para dejas un primer hueco para pintar la línea izquierda
        pw.print(" ");
        System.out.print(" ");
        for (int j = 0; j < this.matriz[0].length; j++) {
            pw.print("------- ");
            System.out.print("------- ");
        }
        //Ponemos el cursor en la siguiente línea
        pw.print("\n");
        System.out.print("\n");
    }

    /* Pinta la columna 0 de la matriz. El método es privado porque sólo se utiliza 
       en la clase Mapa */
    private void pintarColumnaCero(int celdaOrigen, int celdaDestino, int i, int j, int x, PrintWriter pw) {
        if (celdaNoAccesible(celdaOrigen)) {
            pw.print("|///////|");
            System.out.print("|///////|");
        } else {
            if (hayCamino(celdaOrigen, celdaDestino)) {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("|        ");
                    System.out.print("|        ");
                } else {  //Si estamos en la línea central
                    pw.print("|   " + this.matriz[i][j].toString() + "    ");
                    System.out.print("|   " + this.matriz[i][j].toString() + "    ");
                }
            } else {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("|       |");
                    System.out.print("|       |");
                } else {  //Si estamos en la línea central
                    pw.print("|   " + this.matriz[i][j].toString() + "   |");
                    System.out.print("|   " + this.matriz[i][j].toString() + "   |");
                }
            }
        }

    }

    /* Pinta las columnas de la matriz (excepto la columna 0). El método es privado 
       porque sólo se utiliza en la clase Mapa */
    private void pintarRestoColumnas(int celdaOrigen, int celdaDestino, int i, int j, int x, PrintWriter pw) {
        if (celdaNoAccesible(celdaOrigen)) {
            pw.print("///////|");
            System.out.print("///////|");
        } else {
            if (hayCamino(celdaOrigen, celdaDestino)) {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("        ");
                    System.out.print("        ");
                } else {  //Si estamos en la línea central
                    pw.print("   " + this.matriz[i][j].toString() + "    ");
                    System.out.print("   " + this.matriz[i][j].toString() + "    ");
                }
            } else {
                //Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print("       |");
                    System.out.print("       |");
                } else {  //Si estamos en la línea central
                    pw.print("   " + this.matriz[i][j].toString() + "   |");
                    System.out.print("   " + this.matriz[i][j].toString() + "   |");
                }
            }
        }
    }

    /* Pinta la fila "i" de la matriz. El método es privado porque sólo se utiliza 
       en la clase Mapa */
    private void pintarFila(int i, PrintWriter pw) {
        int j, x;
        int celdaOrigen, celdaDestino;
        for (x = 0; x < 3; x++) {  //Cada celda la dividimos en 3 líneas
            for (j = 0; j < this.matriz[i].length; j++) {  //Columnas
                //Calculamos celdaOrigen y celdaDestino
                celdaOrigen = (filas * i) + j;
                celdaDestino = celdaOrigen + 1;

                //Si estamos en la primera columna 
                if (j == 0) {
                    pintarColumnaCero(celdaOrigen, celdaDestino, i, j, x, pw);
                } else {  //Si estamos en cualquier columna del medio
                    pintarRestoColumnas(celdaOrigen, celdaDestino, i, j, x, pw);
                }
            }
            //Ponemos el cursor en la siguiente línea
            pw.print("\n");
            System.out.print("\n");
        }
    }

    /* Muestra el mapa al usuario */
    public void pintarMatriz(PrintWriter pw) {
        int i;
        for (i = 0; i < this.matriz.length; i++) {  //Filas
            pintarLineaSuperior(i, pw);
            pintarFila(i, pw);

        }
        pintarLineaInferior(pw);
    }

}
