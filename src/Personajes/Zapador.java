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
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Zapador extends Aliado {

    //Atributos
    private Obra obra;

    //Constructor por defecto
    public Zapador() {
        super();
        this.obra = null;
    }

    //Constructor parametrizado
    public Zapador(String nombre, int turno, int idCelda, char marca, Obra obra) {
        super(nombre, turno, idCelda, marca);
        this.obra = obra;
    }

    public Zapador(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);
        this.obra = null;
    }

    // Métodos get y set
    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    //Método para realizar la acción
    @Override
    public void realizarAccion(PrintWriter pw) {
        int turnoActual;
        int turnoDespues;
        if (getTurno() == Mapa.getInstancia().getTurno()) {
            if (!isCapturado()) {
                if (estaEnPuntoEncuentro()) {
                    System.out.println(getNombre() + Constantes.PUNTOENCUENTRO);
                    pw.println(getNombre() + Constantes.PUNTOENCUENTRO);

                } else {
                    if (!asegurarPerimetro()) {
                        mover(pw);
                        recogerObra(pw);
                    } else {
                        System.out.println(getNombre() + Constantes.PERIMETRO);
                        pw.println(getNombre() + Constantes.PERIMETRO);
                    }

                }
            } else {
                System.out.println(getNombre() + Constantes.CAPTURADO);
                pw.println(getNombre() + Constantes.CAPTURADO);
                setCapturado(false);

            }

            turnoActual = getTurno();
            turnoDespues = turnoActual + 1;
            setTurno(turnoDespues);
        }
    }

    //Método que nos indica quién esta cogiendo una obra
    @Override
    public void recogerObra(PrintWriter pw) {
        Obra valor;
        int fila = Utilidad.calcularFila(getIdCelda());
        int columna = Utilidad.calcularColumna(getIdCelda());
        Iterator<Obra> obras;
        Celda celda = Mapa.getInstancia().retornarCelda(fila, columna);

        if (this.obra == null) {

            obras = celda.getConjunto().iterator();
            try {
                valor = obras.next();
                setObra(valor);
                celda.borrarObra(valor);
                System.out.println(getNombre() + Constantes.DOSPUNTOS + valor.getNombre() + Constantes.RECUPERADO);
                pw.println(getNombre() + Constantes.DOSPUNTOS + valor.getNombre() + Constantes.RECUPERADO);
            } catch (NoSuchElementException e) {
                //No es necesario
            }
        } else {

            try {
                System.out.println(getNombre() + Constantes.IMPOSIBLERECUPERAR + celda.getConjunto().iterator().next().getNombre());
                pw.println(getNombre() + Constantes.IMPOSIBLERECUPERAR + celda.getConjunto().iterator().next().getNombre());
            } catch (NoSuchElementException e) {
                //No es necesario
            }
        }

    }

    //Método para mostrar la obra
    public void mostrarObra() {
        System.out.println(Constantes.YO + getNombre() + Constantes.RESCATADO + obra.getNombre() + Constantes.DE + obra.getAutor());
    }
}
