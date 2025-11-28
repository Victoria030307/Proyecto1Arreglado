package proyecto1_arreglado;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase de utilidad para gestionar la carga y el guardado de un objeto {@code Grafo}
 * desde y hacia un archivo de texto. Está diseñada para manejar la persistencia
 * de la información de la red social (usuarios y relaciones/aristas).
 */
public class GestorArchivos {

    /**
     * Constructor por defecto de la clase {@code GestorArchivos}.
     */
    public GestorArchivos() {
       
    }

    /**
     * Muestra un cuadro de diálogo para que el usuario seleccione un archivo
     * y carga un objeto {@code Grafo} a partir del contenido de ese archivo.
     * <p>
     * El formato esperado del archivo es:
     * <ul>
     * <li>Línea que contiene "usuarios" (sección de vértices)</li>
     * <li>Lista de nombres de usuario (uno por línea)</li>
     * <li>Línea que contiene "relaciones" (sección de aristas dirigidas)</li>
     * <li>Lista de relaciones en el formato "origen, destino" (una por línea)</li>
     * </ul>
     *
     * @return El objeto {@code Grafo} cargado con los usuarios y relaciones del archivo,
     * o {@code null} si el usuario cancela o si ocurre un error de lectura.
     */
    public static Grafo cargarGrafoDesdeArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            
            Grafo grafoCargado = new Grafo(0); 

            try (BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado))) {
                String linea;
                boolean leyendoUsuarios = false;
                boolean leyendoRelaciones = false;

                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    
                    if (linea.isEmpty()) continue;

                    if (linea.equals("usuarios")) {
                        leyendoUsuarios = true;
                        leyendoRelaciones = false;
                        continue;
                    } else if (linea.equals("relaciones")) {
                        leyendoRelaciones = true;
                        leyendoUsuarios = false;
                        continue;
                    }

                    if (leyendoUsuarios) {
                        grafoCargado.insertar(linea); 
                    } 
                    else if (leyendoRelaciones) {
                        String[] partes = linea.split(",\\s*"); 
                        if (partes.length == 2) {
                            String origen = partes[0];
                            String destino = partes[1];
                            grafoCargado.agregarArista(origen, destino);
                        }
                    }
                }
                return grafoCargado; 

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
            }
        }
        
        return null; 
    }

    /**
     * Guarda el contenido de un objeto {@code Grafo} en un archivo de texto con un nombre predefinido.
     * El archivo de salida se nombra "red_social.txt".
     * <p>
     * El formato de guardado es:
     * <ul>
     * <li>Línea "usuarios"</li>
     * <li>Nombres de los vértices (usuarios)</li>
     * <li>Línea "relaciones"</li>
     * <li>Relaciones (aristas) en el formato "origen, destino"</li>
     * </ul>
     *
     * @param grafo El objeto {@code Grafo} a ser guardado.
     */
    public static void guardarGrafoEnArchivo(Grafo grafo) {
        String nombreArchivo = "red_social.txt"; 

        try (PrintWriter pw = new PrintWriter(nombreArchivo)) {
            
            pw.println("usuarios");
            for (Vertice v : grafo.getVertices()) {
                pw.println(v.usuario);
            }
            pw.println("relaciones");
            for (Vertice v : grafo.getVertices()) {
                
                for (String seguidor : v.adyacentes.getNombres()) {
                    pw.println(v.usuario + ", " + seguidor);
                }
            }
            JOptionPane.showMessageDialog(null, "¡Repositorio actualizado en " + nombreArchivo + "!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}