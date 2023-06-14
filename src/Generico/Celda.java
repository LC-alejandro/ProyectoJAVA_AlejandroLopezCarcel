/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generico;

import Personajes.Aliado;
import Personajes.Militar;
import Personajes.Nazi;
import Personajes.Obra;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Celda {

    //Declaración de atributos
    private int idCelda;
    private ArrayList<Militar> lMilitares;
    private boolean disfraz;
    private boolean puntoDeEncuentro;
    private HashSet<Obra> conjunto;

    // Constructor por defecto
    public Celda() {
        this.idCelda = 0;
        this.lMilitares = new ArrayList();
        this.disfraz = false;
        this.puntoDeEncuentro = false;
        this.conjunto = new HashSet<>();

    }

    // Constructor parametrizado
    public Celda(int idCelda) {
        this.idCelda = idCelda;
        this.lMilitares = new ArrayList();
        this.disfraz = false;
        this.puntoDeEncuentro = false;
        this.conjunto = new HashSet<>();

    }

    //Métodos getter y setter
    public int getIdCelda() {
        return idCelda;
    }

    public ArrayList<Militar> getlMilitares() {
        return lMilitares;
    }

    public boolean isDisfraz() {
        return disfraz;
    }

    public boolean isPuntoDeEncuentro() {
        return puntoDeEncuentro;
    }

    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    public void setlMilitares(ArrayList<Militar> Militares) {
        this.lMilitares = Militares;
    }

    public void setDisfraz(boolean disfraz) {
        this.disfraz = disfraz;
    }

    public void setPuntoDeEncuentro(boolean puntoDeEncuentro) {
        this.puntoDeEncuentro = puntoDeEncuentro;
    }

    public HashSet<Obra> getConjunto() {
        return conjunto;
    }

    public void setConjunto(HashSet<Obra> conjunto) {
        this.conjunto = conjunto;
    }

    //Método para retornar un militar según su posición
    public Militar retornarMilitar(int posicion) {
        Militar militar = lMilitares.get(posicion);

        return militar;
    }

    //Método para retornar el tamaño de la lista de militares
    public int retornarTamano() {

        return lMilitares.size();
    }

    //Método para retornar una obra
    public Obra retornarObra() {
        Iterator<Obra> it = conjunto.iterator();
        Obra obra;
        try {
            obra = it.next();
            return obra;
        } catch (NoSuchElementException e) {
            obra = null;
            return obra;
        }

    }

    //Método para buscar un militar a partir de su nombre
    public Militar buscarMilitar(String nombre) {
        int i = 0;
        boolean encontrarMilitar = false;
        Militar militarEncontrado = lMilitares.get(0);

        while ((i < lMilitares.size()) && (encontrarMilitar == false)) {
            if (lMilitares.get(i).getNombre().equalsIgnoreCase(nombre)) {
                militarEncontrado = lMilitares.get(i);
                encontrarMilitar = true;

            }
            i++;
        }
        return militarEncontrado;
    }

    //Método para insertar una obra
    public void insertarObra(Obra obra) {
        conjunto.add(obra);
    }

    //Método para borrar obra
    public void borrarObra(Obra obra) {
        conjunto.remove(obra);

    }
    //Método para insertar un militar en la lista de militares

    public void insertarMilitar(Militar nuevoMilitar) {
        this.lMilitares.add(nuevoMilitar);

    }
    //Método para borrar un militar de la lista de militares

    public void borrarMilitar(String nombre) {
        int i = 0;
        boolean borrar = false;

        while ((i < lMilitares.size()) && (borrar == false)) {
            if (lMilitares.get(i).getNombre().equalsIgnoreCase(nombre)) {
                borrar = true;
                lMilitares.remove(i);
            }
            ++i;
        }

    }
    //Método para buscar un aliado en la celda

    public boolean buscarAliado() {
        boolean buscar = false;
        int i = 0;
        while ((i < lMilitares.size()) && (buscar == false)) {
            if (lMilitares.get(i) instanceof Aliado) {
                buscar = true;
            }
            i++;
        }
        return buscar;
    }
    //Método para buscar un nazi 

    public boolean buscarNazi() {
        boolean buscar = false;
        int i = 0;
        while ((i < lMilitares.size()) && (buscar == false)) {
            if (lMilitares.get(i) instanceof Nazi) {
                buscar = true;
            }
            i++;
        }
        return buscar;
    }

    @Override
    public String toString() {

        if (this.lMilitares.size() > 0) {
            if (this.lMilitares.size() == 1) {
                //valueOf convierte a String el char recibido por parámetro
                return String.valueOf(this.lMilitares.get(0).getMarca());
            } else {
                //valueOf convierte a String el int recibido por parámetro
                return String.valueOf(this.lMilitares.size());
            }
        } else {
            if (retornarObra() != null) {
                return Constantes.INTERROGACION;
            } else {
                return Constantes.CERO;
            }
        }
    }

}
