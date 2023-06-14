/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import java.time.LocalDate;

public class Utilidad {
//Método para calcular la fila a partir del idCelda recibido

    public static int calcularFila(int idCelda) {
        int resultado;
        resultado = idCelda / Mapa.getInstancia().getFilas();
        return resultado;
    }
//Método para calcular la columna a partir del idCelda recibido

    public static int calcularColumna(int idCelda) {
        int resultado;
        resultado = idCelda % Mapa.getInstancia().getColumnas();
        return resultado;

    }

    //Método para crear un nombre para fichero
    public static String calcularNombreFichero(String base) {
        String nombre;
        Mapa m = Mapa.getInstancia();
        nombre = base + m.getFilas() + Constantes.X + m.getColumnas() + Constantes.BARRABAJA + LocalDate.now().getDayOfMonth() + LocalDate.now().getMonthValue() + Constantes.EXTENSION;

        return nombre;

    }

}
