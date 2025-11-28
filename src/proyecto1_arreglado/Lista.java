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
 * Implementa una estructura de datos de Lista Enlazada Simple para almacenar
 * nombres de usuarios (cadenas de texto). Se utiliza principalmente para
 * manejar las listas de adyacencia de un vértice en el grafo.
 */
public class Lista {

    Nodo primero;
    int size;

    /**
     * Crea una lista enlazada vacía.
     */
    public Lista() {
        this.primero = null;
        this.size = 0;
    }

    /**
     * Inserta un nuevo dato (nombre de usuario) al final de la lista.
     * No permite duplicados.
     *
     * @param dato El nombre de usuario a insertar.
     * @return {@code true} si se insertó el dato, {@code false} si el dato ya existía.
     */
    public boolean insertar(String dato) {
        if (this.buscar(dato) != null) {
            return false; 
        }
        Nodo nuevo = new Nodo(dato);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            Nodo aux = this.primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        size++;
        return true; 
    }
    
    /**
     * Elimina la primera ocurrencia de un dato (nombre de usuario) de la lista.
     *
     * @param dato El nombre de usuario a eliminar.
     */
    public void eliminar(String dato) {
        Nodo aux = this.primero;
        
        if(this.primero == null){
            return;
        }
        
        if(aux.usuario.equals(dato)){
            this.primero = aux.siguiente;
            size--; 
        } else {
            while(aux.siguiente != null && !aux.siguiente.usuario.equals(dato)){
                aux = aux.siguiente;
            }
            
            if(aux.siguiente == null){
                return;
            }
            aux.siguiente = aux.siguiente.siguiente;
            
            size--;
        
        }
        
    }
    

    /**
     * Devuelve todos los datos (nombres de usuario) de la lista en un arreglo de cadenas.
     *
     * @return Un arreglo de {@code String} con los nombres de la lista.
     */
    public String[] getNombres() {
        if (this.size == 0) {
            return new String[0];
        }
        String[] nombres = new String[this.size];
        Nodo aux = this.primero;
        int i = 0;
        while (aux != null) {
            nombres[i] = aux.usuario;
            aux = aux.siguiente;
            i++;
        }
        return nombres;
    }
    
    /**
     * Genera una representación en cadena de texto de todos los elementos de la lista.
     *
     * @return Una cadena de texto con los nombres de usuario separados por ", ".
     */
    public String mostrar (){
        String lista = "";
        Nodo aux = this.primero;
        
        while(aux != null){
            lista += aux.usuario + ", ";
            aux = aux.siguiente;
        }
 
        return lista;
    }
    
    /**
     * Busca un dato (nombre de usuario) en la lista.
     *
     * @param dato El nombre de usuario a buscar.
     * @return El objeto {@code Nodo} que contiene el dato si se encuentra, o {@code null} si no existe.
     */
    public Nodo buscar (String dato){
        Nodo aux = this.primero;

        while(aux != null && !aux.usuario.equals(dato)){
            
            aux = aux.siguiente;
        }
        return aux; 
    }
    
    
}