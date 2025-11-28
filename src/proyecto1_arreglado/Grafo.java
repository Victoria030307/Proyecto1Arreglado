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
/**
 * Representa un Grafo Dirigido que modela una red social,
 * donde los vértices son usuarios y las aristas son las relaciones
 * de seguimiento (dirigidas) entre ellos. Los vértices se almacenan
 * en un arreglo dinámico para una gestión sencilla.
 */
public class Grafo {
    int num_vertices;
    Vertice[] usuarios;
    private int capacidad;
    
    /**
     * Construye un nuevo objeto {@code Grafo} con una capacidad inicial.
     * Si la capacidad inicial es menor o igual a cero, se establece en 10.
     *
     * @param num La capacidad inicial deseada para el arreglo de vértices.
     */
    public Grafo(int num){
        this.capacidad = (num > 0) ? num : 10;
        this.usuarios = new Vertice[this.capacidad];
        this.num_vertices = 0;
    }
    
    /**
     * Obtiene un arreglo que contiene todos los vértices (usuarios) actualmente
     * presentes en el grafo.
     *
     * @return Un arreglo de objetos {@code Vertice} sin espacios {@code null}.
     */
    public Vertice[] getVertices() {
        Vertice[] verticesActuales = new Vertice[this.num_vertices];
        for (int i = 0; i < this.num_vertices; i++) {
            verticesActuales[i] = this.usuarios[i];
        }
        return verticesActuales; 
    }
    
    /**
     * Busca un vértice (usuario) en el grafo por su nombre.
     *
     * @param nombre El nombre del usuario a buscar.
     * @return El objeto {@code Vertice} si se encuentra, o {@code null} si no existe.
     */
    public Vertice buscarUsuario(String nombre){
        for(int i = 0; i < this.num_vertices; i++){
            if(this.usuarios[i].usuario.equals(nombre)){
                return this.usuarios[i];
            }
        }
        return null;
    }

    /**
     * Duplica la capacidad del arreglo de vértices si el número actual de vértices
     * alcanza la capacidad máxima.
     */
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
    
    /**
     * Elimina un vértice (usuario) del grafo. Al eliminar, se compacta el arreglo
     * de vértices y se eliminan todas las aristas dirigidas que apunten al usuario
     * eliminado en las listas de adyacencia de los demás vértices.
     *
     * @param usuario El nombre del usuario a eliminar.
     * @return {@code true} si el usuario fue eliminado, {@code false} si no se encontró.
     */
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
    
    /**
     * Inserta un nuevo vértice (usuario) en el grafo.
     *
     * @param usuario El nombre del nuevo usuario a insertar.
     * @return {@code true} si se insertó el usuario, {@code false} si el usuario ya existe.
     */
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

    /**
     * Agrega una arista dirigida entre dos vértices existentes.
     * Esto significa que el usuario de origen sigue al usuario de destino.
     *
     * @param origen El nombre del usuario de origen (el que sigue).
     * @param destino El nombre del usuario de destino (el seguido).
     * @return {@code true} si la arista fue agregada, {@code false} si uno o ambos usuarios
     * no existen o si la arista ya existía.
     */
    public boolean agregarArista(String origen, String destino) {
        Vertice vOrigen = buscarUsuario(origen);
        Vertice vDestino = buscarUsuario(destino);
        if (vOrigen != null && vDestino != null) {
            return vOrigen.adyacentes.insertar(vDestino.usuario);
        }
        return false;
    }

    /**
     * Calcula y devuelve el Grafo Transpuesto, donde la dirección de todas
     * las aristas está invertida.
     *
     * @return Un nuevo objeto {@code Grafo} que es el transpuesto del grafo actual.
     */
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
    
    /**
     * Comprueba si una cadena de texto está presente en un arreglo de cadenas.
     *
     * @param arreglo El arreglo de cadenas a buscar.
     * @param numElementos El número real de elementos en el arreglo (para evitar los {@code null}).
     * @param str La cadena de texto a buscar.
     * @return {@code true} si la cadena está en el arreglo, {@code false} en caso contrario.
     */
    private boolean contieneString(String[] arreglo, int numElementos, String str) {
        for (int i = 0; i < numElementos; i++) {
            if (arreglo[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega una cadena de texto a un arreglo de cadenas si no está presente.
     *
     * @param arreglo El arreglo donde se insertará la cadena.
     * @param numElementos El número actual de elementos válidos en el arreglo.
     * @param str La cadena a agregar.
     * @return El nuevo número de elementos válidos en el arreglo (incrementado en 1 si se agregó, igual si no).
     */
    private int agregarString(String[] arreglo, int numElementos, String str) {
        if (!contieneString(arreglo, numElementos, str)) {
            arreglo[numElementos] = str;
            return numElementos + 1;
        }
        return numElementos;
    }

    /**
     * Limpia la marca de visitado (poniéndola a {@code false}) en todos los vértices del grafo.
     */
    private void limpiarVisitados() {
        for (int i = 0; i < this.num_vertices; i++) {
            this.usuarios[i].visitado = false;
        }
    }

    /**
     * Primer paso del Algoritmo de Kosaraju para encontrar Componentes Fuertemente Conectados (CFCs).
     * Realiza una búsqueda en profundidad (DFS) y llena una pila con los vértices
     * en el orden en que finalizan.
     *
     * @param v El vértice actual para el DFS.
     * @param visitadosArr Arreglo para registrar los nombres de los vértices visitados.
     * @param numVisitados Arreglo de un elemento que contiene el contador de vértices visitados.
     * @param pila La pila donde se apilan los vértices al finalizar su recorrido.
     */
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

    /**
     * Segundo paso del Algoritmo de Kosaraju para encontrar Componentes Fuertemente Conectados (CFCs).
     * Realiza una búsqueda en profundidad (DFS) en el grafo traspuesto,
     * recolectando los vértices de cada CFC.
     *
     * @param v El vértice actual para el DFS en el grafo traspuesto.
     * @param visitadosArr Arreglo para registrar los nombres de los vértices visitados.
     * @param numVisitados Arreglo de un elemento que contiene el contador de vértices visitados.
     * @param componenteActualArr Arreglo para almacenar los vértices del CFC actual.
     * @param numComponente Arreglo de un elemento que contiene el contador de vértices en el componente actual.
     */
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

    /**
     * Aplica el Algoritmo de Kosaraju para encontrar todos los Componentes
     * Fuertemente Conectados (CFCs) en el grafo.
     * 
     *
     * @return Un arreglo bidimensional de objetos {@code Vertice}, donde cada
     * arreglo interno representa un CFC.
     */
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

    /**
     * Genera una representación en cadena de texto de los Componentes
     * Fuertemente Conectados (CFCs) del grafo.
     *
     * @return Una cadena de texto formateada con la lista de CFCs.
     */
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