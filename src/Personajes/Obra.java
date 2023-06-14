/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

public class Obra {

    //Atributos
    private String nombre;
    private String autor;
    private int codigo;
    private int celdaActual;

    //Constructor por defecto
    public Obra() {
        nombre = "";
        autor = "";
        codigo = 0;
        celdaActual = 0;
    }

    //Constructor parametrizado
    public Obra(int codigo, int celdaActual, String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
        this.codigo = codigo;
        this.celdaActual = celdaActual;

    }

    // Metodos get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setCodigo(int cod) {
        codigo = cod;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCeldaActual(int celda) {
        celdaActual = celda;
    }

    public int getCeldaActual() {
        return celdaActual;
    }

}
