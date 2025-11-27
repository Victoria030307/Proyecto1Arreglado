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
Esta clase es la que guarda cada vertice del grafo, o sea, cada usuario unico.
Cada usuario (vertice) tiene una Lista que guarda a todos los usuarios que sigue
*/
public class Vertice {
    String usuario;
    Lista adyacentes;
    boolean visitado;
    
    public Vertice(String usuario){
        this.usuario =  usuario;
        this.adyacentes = new Lista();
        visitado = false;
    }
}
