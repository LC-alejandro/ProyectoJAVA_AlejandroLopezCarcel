/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Celda;
import Generico.Mapa;
import Generico.Utilidad;
import java.io.PrintWriter;
import java.util.Iterator;

public abstract class Nazi extends Militar {

    //Constructor por defecto
    public Nazi() {
        super();
    }

    //Constructor parametrizado
    public Nazi(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);
    }

    //Método para capturar los aliados
    public void capturarAliados(PrintWriter pw) {
        Mapa M = Mapa.getInstancia();
        Militar sMilitar;
        int fila = Utilidad.calcularFila(getIdCelda());
        int columna = Utilidad.calcularColumna(getIdCelda());
        Celda celda = M.retornarCelda(fila, columna);
        Iterator<Militar> itMilitares = celda.getlMilitares().iterator();
        while (itMilitares.hasNext()) {
            sMilitar = itMilitares.next();
            if (sMilitar instanceof Espia) {
                if (((Espia) sMilitar).isDisfrazado()) {

                    ((Espia) sMilitar).escribirDisfraz(pw);

                } else {
                    ((Espia) sMilitar).setCapturado(true);

                }
            }
            if (sMilitar instanceof BoinaVerde) {

                ((BoinaVerde) sMilitar).setCapturado(true);

            }
            if (sMilitar instanceof Zapador) {
                ((Zapador) sMilitar).setCapturado(true);
            }

        }

    }

    //Método para mover a los nazis
    @Override
    public void mover(PrintWriter pw) {
        //Insertamos el primer movimiento al final de la ruta
        insertarMovimiento(getPrimerMovimiento());
        super.mover(pw);

    }

    //Método que nos permite realizar la acción
    public abstract void realizarAccion(PrintWriter pw);

}
