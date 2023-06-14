/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Generico.Mapa;
import java.io.PrintWriter;

public class Soldado extends Nazi {

    //Constructor por defecto
    public Soldado() {
        super();
    }

    //Constructor parametrizado
    public Soldado(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);
    }

    //Método para realizar la accion
    @Override
    public void realizarAccion(PrintWriter pw) {
        int turnoActual, turnoDespues;
        if (getTurno() == Mapa.getInstancia().getTurno()) {
            mover(pw);
            capturarAliados(pw);
            //Aumenta un turno al soldado
            turnoActual = getTurno();
            turnoDespues = turnoActual + 1;
            setTurno(turnoDespues);
        }
    }

}
