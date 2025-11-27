/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_arreglado;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author victo
 */

/*
Esta clase es la que guarda al grafo.
Tiene un arreglo con todos los vertices que existen en el grafo

*/
public class Grafo {
    int num_vertices;
    List<Vertice> usuarios;
    
    public Grafo(int num){
        this.usuarios = new ArrayList<>();
        this.num_vertices = 0;
    }
    
    public List<Vertice> getVertices() {
        return this.usuarios; 
    }
    
    
    
    public Vertice buscarUsuario(String nombre){
        for(Vertice v: this.usuarios){
            if(v.usuario.equals(nombre)){
                return v;
            }
        }
        return null;
    }
    
public boolean eliminar(String usuario) {
    Vertice v = buscarUsuario(usuario);
    if (v == null) {
        return false; 
    }
    this.usuarios.removeIf(u -> u.usuario.equals(usuario));

    for (Vertice vRestante : this.usuarios) {
        vRestante.adyacentes.eliminar(usuario);
    }
    this.num_vertices = this.usuarios.size();
    return true; 
}
    
 public boolean insertar(String usuario){
    if (buscarUsuario(usuario) != null) {
        return false; 
    }
    Vertice nuevoVertice = new Vertice(usuario);
    this.usuarios.add(nuevoVertice);
    this.num_vertices = this.usuarios.size();
    return true; 
}

public boolean agregarArista(String origen, String destino) {
    Vertice vOrigen = buscarUsuario(origen);
    Vertice vDestino = buscarUsuario(destino);
    if (vOrigen != null && vDestino != null) {
        return vOrigen.adyacentes.insertar(vDestino.usuario);
    }
    return false; 
}

    public Grafo getTranspuesto() {
        Grafo gTranspuesto = new Grafo(this.num_vertices);

        for (Vertice v : this.getVertices()) {
            gTranspuesto.insertar(v.usuario);
        }

        for (Vertice v : this.getVertices()) {
            for (String adyacente : v.adyacentes.getNombres()) {
                gTranspuesto.agregarArista(adyacente, v.usuario);
            }
        }
        return gTranspuesto;
    }

    private void dfsPaso1(Vertice v, Set<String> visitados, Stack<Vertice> pila) {
        visitados.add(v.usuario);
        for (String nombreAdyacente : v.adyacentes.getNombres()) {
            Vertice vAdyacente = this.buscarUsuario(nombreAdyacente);
            if (vAdyacente != null && !visitados.contains(vAdyacente.usuario)) {
                dfsPaso1(vAdyacente, visitados, pila);
            }
        }
        pila.push(v);
    }

    private void dfsPaso2(Vertice v, Set<String> visitados, List<Vertice> componenteActual) {
        visitados.add(v.usuario);
        componenteActual.add(v); 
        for (String nombreAdyacente : v.adyacentes.getNombres()) {
            Vertice vAdyacente = this.buscarUsuario(nombreAdyacente);
            if (vAdyacente != null && !visitados.contains(vAdyacente.usuario)) {
                dfsPaso2(vAdyacente, visitados, componenteActual);
            }
        }
    }

       //mediante los pasos ya realizados se encuentran los componentes fuertemente conectados

    public List<List<Vertice>> encontrarComponentesFuertementeConectados() {
        Stack<Vertice> pila = new Stack<>();
        Set<String> visitados = new HashSet<>();
        
        for (Vertice v : this.getVertices()) {
            if (v != null && !visitados.contains(v.usuario)) {
                dfsPaso1(v, visitados, pila);
            }
        }
        
        Grafo gTranspuesto = this.getTranspuesto();
        visitados.clear(); 
        List<List<Vertice>> todosLosComponentes = new ArrayList<>();
        
        while (!pila.isEmpty()) {
            Vertice v = pila.pop();
            Vertice vTranspuesto = gTranspuesto.buscarUsuario(v.usuario);
            
            if (vTranspuesto != null && !visitados.contains(vTranspuesto.usuario)) {
                List<Vertice> componenteActual = new ArrayList<>();
                gTranspuesto.dfsPaso2(vTranspuesto, visitados, componenteActual);
                todosLosComponentes.add(componenteActual);
            }
        }
        return todosLosComponentes;
    }

    //se obtiene un string formateado con los componentes fuertemente conectados usando el metodo anterior 
    public String getFuertementeconectados() {
        String resultadoFinal = "";
        resultadoFinal += "--- Componentes Fuertemente Conectados ---\n";
        List<List<Vertice>> componentes = this.encontrarComponentesFuertementeConectados();
        int i = 1;
        for (List<Vertice> componente : componentes) {
            resultadoFinal += "Componente " + i + ": { ";
            String Nodos = "";
            for (Vertice v : componente) {
                Nodos += v.usuario + ", "; 
            }
            if (!Nodos.isEmpty()) {
                Nodos = Nodos.substring(0, Nodos.length() - 2);
            }
            resultadoFinal += Nodos + " }\n";
            i++;
        }
        resultadoFinal += "------------------------------------------\n";
        return resultadoFinal;
    }
}
