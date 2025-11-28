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
Esta clase es la que guarda al grafo.
Tiene un arreglo con todos los vertices que existen en el grafo

*/
public class Grafo {
    int num_vertices;
    Vertice[] usuarios; 
    private int capacidad;
    
    public Grafo(int num){
        this.capacidad = (num > 0) ? num : 10;
        this.usuarios = new Vertice[this.capacidad];
        this.num_vertices = 0;
    }
    
    public Vertice[] getVertices() {
        Vertice[] verticesActuales = new Vertice[this.num_vertices];
        for (int i = 0; i < this.num_vertices; i++) {
            verticesActuales[i] = this.usuarios[i];
        }
        return verticesActuales; 
    }
    
    public Vertice buscarUsuario(String nombre){
        for(int i = 0; i < this.num_vertices; i++){
            if(this.usuarios[i].usuario.equals(nombre)){
                return this.usuarios[i];
            }
        }
        return null;
    }

    private void asegurarCapacidad() {
        if (this.num_vertices == this.capacidad) {
            this.capacidad *= 2;
            Vertice[] nuevoArreglo = new Vertice[this.capacidad];
            for (int i = 0; i < this.num_vertices; i++) {
                nuevoArreglo[i] = this.usuarios[i];
            }
            this.usuarios = nuevoArreglo;
        }
    }
    
    public boolean eliminar(String usuario) {
        int indiceAEliminar = -1;
        for (int i = 0; i < this.num_vertices; i++) {
            if (this.usuarios[i].usuario.equals(usuario)) {
                indiceAEliminar = i;
                break;
            }
        }
        
        if (indiceAEliminar == -1) {
            return false;
        }
        
        if (indiceAEliminar != this.num_vertices - 1) {
            this.usuarios[indiceAEliminar] = this.usuarios[this.num_vertices - 1];
        }
        this.usuarios[this.num_vertices - 1] = null;
        this.num_vertices--;
        
        for (int i = 0; i < this.num_vertices; i++) {
            this.usuarios[i].adyacentes.eliminar(usuario);
        }
        return true;
    }
    
    public boolean insertar(String usuario){
        if (buscarUsuario(usuario) != null) {
            return false;
        }
        asegurarCapacidad();
        Vertice nuevoVertice = new Vertice(usuario);
        this.usuarios[this.num_vertices] = nuevoVertice;
        this.num_vertices++;
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

        for (int i = 0; i < this.num_vertices; i++) {
            gTranspuesto.insertar(this.usuarios[i].usuario);
        }

        for (int i = 0; i < this.num_vertices; i++) {
            Vertice v = this.usuarios[i];
            String[] adyacentes = v.adyacentes.getNombres();
            for (String adyacente : adyacentes) {
                gTranspuesto.agregarArista(adyacente, v.usuario);
            }
        }
        return gTranspuesto;
    }
    
    private boolean contieneString(String[] arreglo, int numElementos, String str) {
        for (int i = 0; i < numElementos; i++) {
            if (arreglo[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    private int agregarString(String[] arreglo, int numElementos, String str) {
        if (!contieneString(arreglo, numElementos, str)) {
            arreglo[numElementos] = str;
            return numElementos + 1;
        }
        return numElementos;
    }

    private void limpiarVisitados() {
        for (int i = 0; i < this.num_vertices; i++) {
            this.usuarios[i].visitado = false;
        }
    }

    private void dfsPaso1(Vertice v, String[] visitadosArr, int[] numVisitados, Pila pila) {
        numVisitados[0] = agregarString(visitadosArr, numVisitados[0], v.usuario);
        
        String[] nombresAdyacentes = v.adyacentes.getNombres();
        for (String nombreAdyacente : nombresAdyacentes) {
            Vertice vAdyacente = this.buscarUsuario(nombreAdyacente);
            if (vAdyacente != null && !contieneString(visitadosArr, numVisitados[0], vAdyacente.usuario)) {
                dfsPaso1(vAdyacente, visitadosArr, numVisitados, pila);
            }
        }
        pila.push(v);
    }

    private void dfsPaso2(Vertice v, String[] visitadosArr, int[] numVisitados, Vertice[] componenteActualArr, int[] numComponente) {
        numVisitados[0] = agregarString(visitadosArr, numVisitados[0], v.usuario);
        
        if (numComponente[0] < componenteActualArr.length) {
            componenteActualArr[numComponente[0]] = v;
            numComponente[0]++;
        }
        
        String[] nombresAdyacentes = v.adyacentes.getNombres();
        for (String nombreAdyacente : nombresAdyacentes) {
            Vertice vAdyacente = this.buscarUsuario(nombreAdyacente);
            if (vAdyacente != null && !contieneString(visitadosArr, numVisitados[0], vAdyacente.usuario)) {
                dfsPaso2(vAdyacente, visitadosArr, numVisitados, componenteActualArr, numComponente);
            }
        }
    }

    public Vertice[][] encontrarComponentesFuertementeConectados() {
        Pila pila = new Pila();
        
        String[] visitadosArr = new String[this.num_vertices]; 
        int[] numVisitados = {0}; 

        Vertice[] vertices = this.getVertices();
        for (Vertice v : vertices) {
            if (v != null && !contieneString(visitadosArr, numVisitados[0], v.usuario)) {
                dfsPaso1(v, visitadosArr, numVisitados, pila);
            }
        }
        
        Grafo gTranspuesto = this.getTranspuesto();
        
        numVisitados[0] = 0;
        Vertice[][] todosLosComponentesTemp = new Vertice[this.num_vertices][]; 
        int numComponentes = 0;
        
        while (!pila.isEmpty()) {
            Vertice v = pila.pop();
            Vertice vTranspuesto = gTranspuesto.buscarUsuario(v.usuario);
            
            if (vTranspuesto != null && !contieneString(visitadosArr, numVisitados[0], vTranspuesto.usuario)) {
                Vertice[] componenteActualArr = new Vertice[this.num_vertices]; 
                int[] numComponente = {0};

                gTranspuesto.dfsPaso2(vTranspuesto, visitadosArr, numVisitados, componenteActualArr, numComponente);
                
                Vertice[] componenteRecortado = new Vertice[numComponente[0]];
                for (int i = 0; i < numComponente[0]; i++) {
                    componenteRecortado[i] = componenteActualArr[i];
                }
                
                todosLosComponentesTemp[numComponentes] = componenteRecortado;
                numComponentes++;
            }
        }
        
        Vertice[][] todosLosComponentes = new Vertice[numComponentes][];
        for (int i = 0; i < numComponentes; i++) {
            todosLosComponentes[i] = todosLosComponentesTemp[i];
        }
        
        return todosLosComponentes;
    }

    public String getFuertementeconectados() {
        String resultadoFinal = "";
        resultadoFinal += "--- Componentes Fuertemente Conectados ---\n";
        Vertice[][] componentes = this.encontrarComponentesFuertementeConectados();
        int i = 1;
        for (Vertice[] componente : componentes) {
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