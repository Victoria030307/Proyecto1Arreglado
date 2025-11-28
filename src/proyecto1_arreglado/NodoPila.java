/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_arreglado;

/**
 *
 * @author victo
 */
/**
 * Representa un nodo en una **Pila** (Stack), utilizado para almacenar un
 * objeto de tipo {@code Vertice} dentro de la estructura de la pila (clase {@code Pila}).
 */
public class NodoPila {
    /** El dato almacenado en el nodo, que es un objeto {@code Vertice}. */
    Vertice dato;
    /** Referencia al siguiente nodo en la pila (el que está debajo). */
    NodoPila siguiente;
    
    /**
     * Crea una nueva instancia de {@code NodoPila} inicializando el dato con un vértice.
     * El puntero {@code siguiente} se establece inicialmente en {@code null}.
     *
     * @param dato El objeto {@code Vertice} que será almacenado en el nodo.
     */
    public NodoPila(Vertice dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}