/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Constantes;
import Generico.Mapa;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Militar {

    //Atributos
    private String nombre;
    private int turno;
    private int idCelda;
    private ArrayList<Character> vRuta;
    private char marca;

    //Constructor por defecto
    public Militar() {
        nombre = "";
        turno = 0;
        idCelda = 0;
        vRuta = new ArrayList();
        this.marca = ' ';
    }

    //Constructor parametrizado
    public Militar(String nombre, int turno, int idCelda, char marca) {
        this.nombre = nombre;
        this.turno = turno;
        this.idCelda = idCelda;
        this.vRuta = new ArrayList();
        this.marca = marca;
    }

    // Metodos get y set
    public char getMarca() {
        return marca;
    }

    public void setMarca(char marca) {
        this.marca = marca;
    }

    public void setNombre(String nom) {
        nombre = nom;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    public int getIdCelda() {
        return idCelda;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getTurno() {
        return turno;
    }

    public ArrayList<Character> getvRuta() {
        return vRuta;
    }

    public void setvRuta(ArrayList<Character> vRuta) {
        this.vRuta = vRuta;
    }

    //Método que nos permite rellenar la lista ruta
    public void cargarMovimientos(char[] vRuta) {
        int i;
        for (i = 0; i < vRuta.length; i++) {
            this.vRuta.add(vRuta[i]);
        }
    }

    //Método que nos permite retornar la primera celda de la ruta
    public char getPrimerMovimiento() {
        return vRuta.get(0);
    }

    //Método que nos permite mostrar la ruta
    public void mostrarRuta() {
        int i;
        for (i = 0; i < vRuta.size(); i++) {
            System.out.print(Constantes.ABRIRCORCHETE + vRuta.get(i) + Constantes.CERRARCORCHETE);
        }
    }

    //Método para borrar el primer movimiento de la ruta
    public void borrarPrimerMovimiento() {
        this.vRuta.remove(0);
    }

    //Método que nos permite insertar un movimiento en la ruta
    public void insertarMovimiento(char movimiento) {
        this.vRuta.add(movimiento);

    }

    //Método que retorna true si la lista esta vacía.
    public boolean rutaVacia() {

        return this.vRuta.isEmpty();
    }

    //Método para calcular la siguiente celda según la ruta del militar
    public int calcularSiguienteIdCelda() {
        int sigIdCelda = 0;
        char primerMovi = getPrimerMovimiento();
        switch (primerMovi) {
            case Constantes.ESTE:
                sigIdCelda = idCelda + 1;
                break;
            case Constantes.OESTE:
                sigIdCelda = idCelda - 1;
                break;
            case Constantes.NORTE:
                sigIdCelda = idCelda - Mapa.getInstancia().getColumnas();
                break;
            case Constantes.SUR:
                sigIdCelda = idCelda + Mapa.getInstancia().getColumnas();
                break;

        }

        return sigIdCelda;
    }

    //Método abstracto para que los aliados y los nazis realicen acción
    public abstract void realizarAccion(PrintWriter pw);

    //Método abstracto que indica que los nazis y aliados se puedan mover
    public void mover(PrintWriter pw) {
        int celdaOrigen = getIdCelda();
        int celdaDestino = calcularSiguienteIdCelda();

        //Comprobamos si la lista de la ruta no esta vacia.
        if (!rutaVacia()) {
            //Comprobamos que hay camino
            if (Mapa.getInstancia().hayCamino(celdaOrigen, celdaDestino)) {
                //Borramos al militar de la celda donde se encuentra
                Mapa.getInstancia().borrarMilitar(getNombre(), celdaOrigen);
                //Actualizamos su idCelda
                setIdCelda(celdaDestino);
                //Insertamos el militar en su celdaDestino
                Mapa.getInstancia().insertarMilitar(this);

            } else {
                //Mensaje a mostrar si el militar no puede avanzar
                System.out.println(getNombre() + Constantes.NOPUEDO + getPrimerMovimiento() + Constantes.CIERREPAREN);
                pw.println(getNombre() + Constantes.NOPUEDO + getPrimerMovimiento() + Constantes.CIERREPAREN);

            }
        }
        borrarPrimerMovimiento();

    }

}
