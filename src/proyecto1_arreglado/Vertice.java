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
 * Representa un **Vértice** (nodo) dentro de un grafo, específicamente
 * modelando un usuario en una red social.
 */
public class Vertice {
    /** El nombre o identificador único del usuario (vértice). */
    String usuario;
    
    /** * La lista de adyacencia del vértice, que contiene los nombres de los usuarios
     * a los que este vértice sigue (aristas salientes).
     */
    Lista adyacentes;
    
    /** Indicador booleano utilizado en algoritmos de recorrido de grafos (como DFS). */
    boolean visitado;
    
    /**
     * Crea un nuevo vértice inicializando el nombre del usuario y su lista de adyacencia.
     * El estado {@code visitado} se establece por defecto en {@code false}.
     *
     * @param usuario El nombre del usuario para este vértice.
     */
    public Vertice(String usuario){
        this.usuario =  usuario;
        this.adyacentes = new Lista();
        visitado = false;
    }
}