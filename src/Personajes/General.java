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
import java.util.NoSuchElementException;

public class General extends Nazi {

    //Constructor por defecto
    public General() {
        super();
    }

    //Constructor parametrizado
    public General(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);
    }

    //Método para requisar las obras de los aliados que esten en la celda
    public void requisarObras(PrintWriter pw) {
        int fila = Utilidad.calcularFila(getIdCelda());
        int columna = Utilidad.calcularColumna(getIdCelda());
        Celda celda = Mapa.getInstancia().retornarCelda(fila, columna);
        BoinaVerde bv;
        Espia es;
        Zapador za;
        Militar militar;

        for (int i = 0; i < celda.getlMilitares().size(); i++) {

            militar = celda.getlMilitares().get(i);
            if (militar instanceof Aliado) {

                if (militar instanceof BoinaVerde) {
                    bv = (BoinaVerde) celda.getlMilitares().get(i);
                    reObrasBv(bv, celda, pw);
                } else {
                    if (militar instanceof Espia) {
                        es = (Espia) celda.getlMilitares().get(i);
                        if (!es.isDisfrazado()) {
                            reObraEspia(es, celda, pw);
                        }

                    } else {
                        if (militar instanceof Zapador) {
                            za = (Zapador) celda.getlMilitares().get(i);
                            reObraZapador(za, celda, pw);
                        }

                    }
                }

            }

        }
    }

    private void reObraZapador(Zapador za, Celda celda, PrintWriter pw) {
        Obra obra;
        obra = za.getObra();
        if (obra != null) {
            celda.insertarObra(obra);
            za.setObra(null);
            za.escribirObraRequisada(obra, pw);

        }

    }

    private void reObraEspia(Espia es, Celda celda, PrintWriter pw) {
        Obra obra;
        obra = es.getObra();
        if (obra != null) {
            celda.insertarObra(obra);
            es.setObra(null);
            es.escribirObraRequisada(obra, pw);
        }
    }

    private void reObrasBv(BoinaVerde bv, Celda celda, PrintWriter pw) {

        Iterator<Obra> it = bv.getObras().iterator();
        Obra obra;
        while (it.hasNext()) {
            obra = it.next();
            try {
                celda.insertarObra(obra);
                bv.escribirObraRequisada(obra, pw);
                bv.getObras().remove(obra);
            } catch (NoSuchElementException e) {
                //No es necesario

            }

        }
    }

    @Override
    public void realizarAccion(PrintWriter pw) {
        int turnoActual, turnoDespues;
        if (getTurno() == Mapa.getInstancia().getTurno()) {
            mover(pw);
            capturarAliados(pw);
            requisarObras(pw);
            //Aumentar un turno al general
            turnoActual = getTurno();
            turnoDespues = turnoActual + 1;
            setTurno(turnoDespues);
        }
    }

}
