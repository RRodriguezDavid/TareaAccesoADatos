package UsuariosYAficionesConcordantes;
import Objetos.Persona;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "aficiones.txt";
    static final String Concordancias = "concordancias.txt";

    public static void main(String[] args) {

        int opcion;
        while(true) {
            System.out.println(
                    "|--------------Menu------------------|\n"
                  + "| 1. Ingresar usuario                |\n"
                  + "| 2. Mostrar usuarios introducidos   |\n"
                  + "| 3. Borrar fichero                  |\n"
                  + "| 4. Generar fichero concordancias   |\n"
                  + "| 5  Mostrar fichero concordantes    |\n"
                  + "| 6. Eliminar fichero concordancias  |\n"
                  + "| 7. Salir                           |\n"
                  + "|------------------------------------|");
            System.out.printf("Ingrese la opción que desea realizar: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            if (opcion <= 0 || opcion > 7) {
                System.out.println("       ¡Opción no valida!");
            } else {
                switch (opcion) {
                    case 1 -> annadirPersona();
                    case 2 -> mostrarUsuarios();
                    case 3 -> borrarArchivo();
                    case 4 -> ficheroConcordancias();
                    case 5 -> mostrarFicheroConcordancias();
                    case 6 -> eliminarConcordancias();
                    case 7 -> finalizar();
                }
            }
        }
    }

    public static void annadirPersona() {
        Set<Integer> codigosExistentes = obtenerCodigosExistentes();
        int codigo;
        while (true) {
            int sugerido = sugerirCodigo();
            System.out.println("Introduce el código de la persona (sugerido: " + sugerido + "):");
            codigo = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            if (!codigosExistentes.contains(codigo)) break;
            System.out.println("Ese código ya existe. Por favor, elige otro o usa el sugerido: " + sugerido);
        }

        System.out.println("Introduce las aficiones de la persona separadas por espacios:");
        String aficiones = sc.nextLine();

        Persona persona = new Persona(aficiones, codigo);

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(persona.getCodigo() + ":" + persona.getAficiones());
            System.out.println("Persona agregada correctamente!");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static void mostrarUsuarios() {
        File fichero = new File(FILE_NAME);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            System.out.println("Listado de personas:");
            while ((linea = br.readLine()) != null) {
                // Separar código de aficiones
                String[] partes = linea.split(":", 2);
                System.out.println("Código: " + partes[0] + ", Aficiones: " + partes[1]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void borrarArchivo() {
        File fichero = new File(FILE_NAME);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe");
            return;
        }

        if (fichero.delete()) {
            System.out.println("El fichero eliminado correctamente!");
        }
    }

    public static void ficheroConcordancias() {
        File fichero = new File(FILE_NAME);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        System.out.println("Dime cuántas aficiones coincidentes tienen que tener (>=1): ");
        int coincidencias = sc.nextInt();
        sc.nextLine();

        if (coincidencias < 1) {
            System.out.println("El número de coincidencias debe ser >= 1.");
            return;
        }

        Map<Integer, Set<String>> usuarios = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":", 2);
                if (partes.length == 2) {
                    int codigo = Integer.parseInt(partes[0]);
                    Set<String> aficiones = new HashSet<>();
                    for (String af : partes[1].trim().split("\\s+")) {
                        aficiones.add(af.toLowerCase());
                    }
                    usuarios.put(codigo, aficiones);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        // Generar todas las parejas
        List<String> resultados = new ArrayList<>();
        List<Integer> codigos = new ArrayList<>(usuarios.keySet());

        for (int i = 0; i < codigos.size(); i++) {
            for (int j = i + 1; j < codigos.size(); j++) {
                int c1 = codigos.get(i);
                int c2 = codigos.get(j);

                Set<String> comunes = new HashSet<>(usuarios.get(c1));
                comunes.retainAll(usuarios.get(c2)); // intersección

                if (comunes.size() >= coincidencias) {
                    List<String> comunesOrdenadas = new ArrayList<>(comunes);
                    Collections.sort(comunesOrdenadas); // ordenar alfabéticamente
                    resultados.add(c1 + " - " + c2 + " : " + String.join(" ", comunesOrdenadas));
                }
            }
        }

        // Ordenar resultados por número de coincidencias (mayor a menor)
        resultados.sort((a, b) -> {
            int comunesA = a.split(":")[1].trim().split("\\s+").length;
            int comunesB = b.split(":")[1].trim().split("\\s+").length;
            return Integer.compare(comunesB, comunesA);
        });

        if (resultados.isEmpty()) {
            System.out.println("No hay parejas con al menos " + coincidencias + " aficiones en común.");
            return;
        }

        // Escribir resultados en fichero
        try (PrintWriter out = new PrintWriter(new FileWriter(Concordancias))) {
            for (String linea : resultados) {
                out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir concordancias: " + e.getMessage());
            return;
        }

        System.out.println("Se han encontrado " + resultados.size() + " parejas con al menos " + coincidencias + " aficiones en común.");
        System.out.println("Fichero 'concordancias.txt' generado correctamente.");
    }

    public static Set<Integer> obtenerCodigosExistentes() {
        Set<Integer> codigos = new HashSet<>();

        File fichero = new File(FILE_NAME);
        if (!fichero.exists()) return codigos;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":", 2);
                codigos.add(Integer.parseInt(partes[0]));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        return codigos;
    } //evitar duplicados

    public static void mostrarFicheroConcordancias() {
        File fichero = new File(Concordancias);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            System.out.println("Listado de personas:");
            while ((linea = br.readLine()) != null) {
                // Separar código de aficiones
                String[] partes = linea.split(":", 2);
                System.out.println("Código: " + partes[0] + ", Aficiones: " + partes[1]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    public static void eliminarConcordancias() {
        File fichero = new File(Concordancias);
        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        if (fichero.delete()) {
            System.out.println("El fichero eliminado correctamente!");
        }
    }

    public static int sugerirCodigo() {
        Set<Integer> codigosExistentes = obtenerCodigosExistentes();
        int codigoSugerido = 101;
        while (codigosExistentes.contains(codigoSugerido)) {
            codigoSugerido++;
        }
        return codigoSugerido;
    }

    public static void finalizar() {
        System.out.println("Ha sido un placer, vuelva cuando quiera.");
        System.exit(0);
    }
}