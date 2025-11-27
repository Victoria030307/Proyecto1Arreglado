/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_arreglado;

/**
 *
 * @author victo
 */
public class Pila {
    private NodoPila top;
    
    public Pila() {
        this.top = null;
    }
    
    public void push(Vertice v) {
        NodoPila nuevo = new NodoPila(v);
        nuevo.siguiente = top;
        top = nuevo;
    }
    
    public Vertice pop() {
        if (isEmpty()) {
            return null;
        }
        Vertice dato = top.dato;
        top = top.siguiente;
        return dato;
    }
    
    public Vertice peek() {
        if (isEmpty()) {
            return null;
        }
        return top.dato;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
}