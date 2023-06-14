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
import java.util.HashSet;
import java.util.Iterator;

public class BoinaVerde extends Aliado {

    private HashSet<Obra> obras;

    //Constructor por defecto
    public BoinaVerde() {
        super();
        obras = new HashSet<>();
    }

    //Constructor parametrizado
    public BoinaVerde(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);
        obras = new HashSet<>();
    }

    public HashSet<Obra> getObras() {
        return obras;
    }

    public void setObras(HashSet<Obra> obras) {
        this.obras = obras;
    }

    //Método para realizar la acción
    @Override
    public void realizarAccion(PrintWriter pw) {
        int turnoActual = getTurno();
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

            turnoDespues = turnoActual + 1;
            setTurno(turnoDespues);
        }
    }
    //Método que nos indica quien esta cogiendo una obra 

    @Override
    public void recogerObra(PrintWriter pw) {

        int fila = Utilidad.calcularFila(getIdCelda());
        int columna = Utilidad.calcularColumna(getIdCelda());
        Obra valor;
        Celda celda = Mapa.getInstancia().retornarCelda(fila, columna);
        //Iterador para recorrer el conjunto de la celda y para que el boinaverde rescate las obras.
        Iterator<Obra> obrasBv = celda.getConjunto().iterator();
        while (obrasBv.hasNext()) {
            valor = obrasBv.next();
            System.out.println(getNombre() + Constantes.DOSPUNTOS + valor.getNombre() + Constantes.RECUPERADO);
            pw.println(getNombre() + Constantes.DOSPUNTOS + valor.getNombre() + Constantes.RECUPERADO);
            obras.add(valor);
            celda.borrarObra(valor);

        }
    }
}
