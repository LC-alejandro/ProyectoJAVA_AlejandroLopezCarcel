/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Celda;
import Generico.Constantes;
import Generico.Mapa;
import Generico.Utilidad;
import java.io.PrintWriter;

public abstract class Aliado extends Militar {

    //Atributos
    private boolean capturado;
    private boolean enPuntoEncuentro;

    //Constructor por defecto
    public Aliado() {
        super();
        capturado = false;
        this.enPuntoEncuentro = false;
    }

    //Constructor parametrizado
    public Aliado(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);
        this.capturado = false;
        this.enPuntoEncuentro = false;
    }

    // Metodos get y set
    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    public boolean isCapturado() {
        return capturado;
    }

    public boolean isEnPuntoEncuentro() {
        return enPuntoEncuentro;
    }

    public void setEnPuntoEncuentro(boolean enPuntoEncuentro) {
        this.enPuntoEncuentro = enPuntoEncuentro;
    }

    //Método para saber si hay algún nazi en la proxima celda a la que nos tiene que mover
    public boolean asegurarPerimetro() {
        boolean aPerimetro = false;
        int sigCelda = calcularSiguienteIdCelda();
        int fila = Utilidad.calcularFila(sigCelda);
        int columna = Utilidad.calcularColumna(sigCelda);
        Celda celda;
        try {
            celda = Mapa.getInstancia().retornarCelda(fila, columna);
            if (celda.buscarNazi()) {
                aPerimetro = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            aPerimetro = false;
        }

        return aPerimetro;
    }

    //Método para averiguar si esta en el punto de encuentro
    public boolean estaEnPuntoEncuentro() {

        int idCeldaActual = getIdCelda();

        int fila = Utilidad.calcularFila(idCeldaActual);
        int columna = Utilidad.calcularColumna(idCeldaActual);

        Celda celda = Mapa.getInstancia().retornarCelda(fila, columna);
        if (celda.isPuntoDeEncuentro() == true) {

            if (enPuntoEncuentro == false) {
                Mapa.getInstancia().aumentarNumAliadosPunto();
                enPuntoEncuentro = true;
            }

        }

        return enPuntoEncuentro;
    }

    //Método para escribir las obras que han sido requisadas
    public void escribirObraRequisada(Obra obra, PrintWriter pw) {
        System.out.println(getNombre() + Constantes.DOSPUNTOS + obra.getNombre() + Constantes.REQUISADA);
        pw.println(getNombre() + Constantes.DOSPUNTOS + obra.getNombre() + Constantes.REQUISADA);

    }

    //Método abstracto para realizar acción
    public abstract void realizarAccion(PrintWriter pw);

    //Método abstracto para recoger obra
    public abstract void recogerObra(PrintWriter pw);

}
