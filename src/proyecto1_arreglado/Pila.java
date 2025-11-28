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
 * Implementa una estructura de datos de Pila (Stack) utilizando nodos enlazados
 * para almacenar objetos de tipo {@code Vertice}. Sigue el principio LIFO
 * (Last In, First Out).
 */
public class Pila {
    private NodoPila top;
    
    /**
     * Crea una pila vacía.
     */
    public Pila() {
        this.top = null;
    }
    
    /**
     * Inserta un elemento (vértice) en la cima de la pila (operación push).
     *
     * @param v El objeto {@code Vertice} a apilar.
     */
    public void push(Vertice v) {
        NodoPila nuevo = new NodoPila(v);
        nuevo.siguiente = top;
        top = nuevo;
    }
    
    /**
     * Elimina y devuelve el elemento en la cima de la pila (operación pop).
     *
     * @return El objeto {@code Vertice} en la cima de la pila, o {@code null} si la pila está vacía.
     */
    public Vertice pop() {
        if (isEmpty()) {
            return null;
        }
        Vertice dato = top.dato;
        top = top.siguiente;
        return dato;
    }
    
    /**
     * Devuelve el elemento en la cima de la pila sin eliminarlo (operación peek).
     *
     * @return El objeto {@code Vertice} en la cima de la pila, o {@code null} si la pila está vacía.
     */
    public Vertice peek() {
        if (isEmpty()) {
            return null;
        }
        return top.dato;
    }
    
    /**
     * Comprueba si la pila está vacía.
     *
     * @return {@code true} si la pila no contiene elementos, {@code false} en caso contrario.
     */
    public boolean isEmpty() {
        return top == null;
    }
}