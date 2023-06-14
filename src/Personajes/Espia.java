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

public class Espia extends Aliado {

    //Atributos
    private boolean disfrazado;
    private Obra obra;

    //Constructor por defecto
    public Espia() {
        super();
        disfrazado = false;
        obra = null;
    }

    //Constructor parametrizado
    public Espia(String nombre, int turno, int idCelda, char marca, Obra obra) {

        super(nombre, turno, idCelda, marca);
        this.disfrazado = false;
        this.obra = obra;

    }

    public Espia(String nombre, int turno, int idCelda, char marca) {

        super(nombre, turno, idCelda, marca);
        this.disfrazado = false;
        this.obra = null;

    }

    // Metodos get y set
    public void setDisfrazado(boolean disfrazado) {
        this.disfrazado = disfrazado;
    }

    public boolean isDisfrazado() {
        return disfrazado;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Obra getObra() {
        return obra;
    }

    //Método para mostrar quien a rescatado la  obra
    public void mostrarObra() {
        System.out.println(Constantes.YO + getNombre() + Constantes.RESCATADO + obra.getNombre() + Constantes.DE + obra.getAutor());
    }

    //Método para recoger el disfraz
    public void recogerDisfraz(PrintWriter pw) {
        int columna;
        int fila;
        Celda celdaDisfraz;
        columna = Utilidad.calcularColumna(getIdCelda());
        fila = Utilidad.calcularFila(getIdCelda());
        celdaDisfraz = Mapa.getInstancia().retornarCelda(fila, columna);
        if (celdaDisfraz.isDisfraz()) {
            disfrazado = celdaDisfraz.isDisfraz();
            celdaDisfraz.setDisfraz(false);
            System.out.println(getNombre() + Constantes.DISFRAZRECOGIDO);
            pw.println(getNombre() + Constantes.DISFRAZRECOGIDO);
        }
    }

    //Método que nos muestra quien realiza la acción
    @Override
    public void realizarAccion(PrintWriter pw) {
        int turnoActual, turnoDespues;
        if (getTurno() == Mapa.getInstancia().getTurno()) {
            if (!isCapturado()) {
                if (estaEnPuntoEncuentro()) {
                    System.out.println(getNombre() + Constantes.PUNTOENCUENTRO);
                    pw.println(getNombre() + Constantes.PUNTOENCUENTRO);

                } else {
                    if (!disfrazado) {
                        if (!asegurarPerimetro()) {
                            //TODO:Una sola llamada por acción
                            mover(pw);
                            recogerDisfraz(pw);
                            recogerObra(pw);
                        } else {
                            System.out.println(getNombre() + Constantes.PERIMETRO);
                            pw.println(getNombre() + Constantes.PERIMETRO);
                        }

                    } else {

                        mover(pw);
                        recogerObra(pw);

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

    //METODO PARA FICHERO .LOG DEL DIFRAZ CUANDO SURPA EFECTO
    public void escribirDisfraz(PrintWriter pw) {
        System.out.println(getNombre() + Constantes.ENGANADO);
        pw.println(getNombre() + Constantes.ENGANADO);

    }

    //Método que nos indica quien esta cogiendo una obra 
    @Override
    public void recogerObra(PrintWriter pw) {

        Obra valor;
        int fila = Utilidad.calcularFila(getIdCelda());
        int columna = Utilidad.calcularColumna(getIdCelda());
        Celda celda = Mapa.getInstancia().retornarCelda(fila, columna);
        Iterator<Obra> obras;
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

}
