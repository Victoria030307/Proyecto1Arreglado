/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_arreglado;

/**
 *
 * @author victo
 */

/*
La clase de Nodo sirve para guardar cada una dee las Aristas de cada vertice
Es decir, guardan al usuario que sigue
Vertices    Lista de seguidos
....
@victoria  Sigue a: @juan -> @pedro (Juan y Pedro serian los nodos de la lista de seguidos)

*/
public class Nodo {
    Nodo siguiente;
    String usuario;
    
    public Nodo (String dato){
        this.siguiente = null;
        this.usuario = dato;
    }
}
