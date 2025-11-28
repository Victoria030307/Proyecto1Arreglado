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
 * Representa un nodo en una **Lista Enlazada Simple** (como la clase {@code Lista}),
 * utilizado específicamente para almacenar el nombre de un usuario o una arista
 * de adyacencia en el contexto de un grafo de red social.
 * 
 */
public class Nodo {
    /** Referencia al siguiente nodo en la lista. */
    Nodo siguiente;
    /** El dato almacenado en el nodo, que representa el nombre de un usuario. */
    String usuario;
    
    /**
     * Crea una nueva instancia de {@code Nodo} inicializando el campo de usuario.
     * El puntero {@code siguiente} se establece inicialmente en {@code null}.
     *
     * @param dato El nombre de usuario que será almacenado en el nodo.
     */
    public Nodo (String dato){
        this.siguiente = null;
        this.usuario = dato;
    }
}