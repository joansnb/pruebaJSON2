import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MiLectorJson {
    //actualizado a version 1.0
    //comentario para hacer un commit mas
    public static void main(String[] args) {
        String filePath = "src/main/java/datosUsuario.json"; // ruta real de archivo JSON
        String nuevoJsonPath = "src/main/java/datosUsuarioModificado.json"; //ruta del nuevo JSON

        //habilidad a buscar
        String habilidadBuscada = "Java";
        //try/catch para leer Json
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject objetoJSON = new JSONObject(content);

            //creamos copia de json existente
            JSONObject jsonNuevo = new JSONObject(objetoJSON.toString());

            // Acceder al elemento "nombre" y mostrarlo:
            String nombre = objetoJSON.getString("nombre");
            System.out.println("El nombre en el JSON es: " + nombre);

            //codigo para imprimir habilidades
            JSONArray habilidades = objetoJSON.getJSONArray("habilidades");
            System.out.println("Habilidades de " + nombre + ":");
            for (int i = 0; i < habilidades.length(); i++) {
                System.out.println("- " + habilidades.getString(i));
            }

            //accedemos al nuevo para agrgar habilidad
            JSONArray habilidadesNueva = jsonNuevo.getJSONArray("habilidades");
            habilidadesNueva.put("GIT");

            //escribimos en nuevo archivo, hacemos otro FileWrite
            //creamos excepcion para leer archivo
            try(FileWriter fileWriter = new FileWriter(nuevoJsonPath)){
                fileWriter.write(jsonNuevo.toString(2));
            }catch (IOException e){
                    System.out.println("Error escribiendo el archivo: " + e.getMessage());
            }

            //variable para duracion
            int duracion;

            //funcionalidad para imprimir trabajos
            JSONArray trabajos = objetoJSON.getJSONArray("trabajos");
            System.out.println("Trabajos de " + nombre + ":");
            for(int i =0;i< trabajos.length(); i++){
                JSONObject trabajo = trabajos.getJSONObject(i);
                System.out.println("Empresa: " + trabajo.getString("empresa") + " puesto: " + trabajo.getString("puesto"));
                duracion = (trabajo.getInt("añoFin") - trabajo.getInt("añoInicio"));
                System.out.println("Duracion: "+ duracion);
                trabajo.put("duracion", duracion);
            }

            //comprobamos edad si es entero
            Object edadObj = objetoJSON.get("edad");
            if (edadObj instanceof Integer) {
                int edad = objetoJSON.getInt("edad");
                System.out.println("La edad en el JSON es: " + edad);
            } else {
                System.out.println("Error: La edad en el JSON no es un número entero.");
                return; // Sale del programa
            }

            //escribimos duracion
            try(FileWriter fileWriter = new FileWriter(filePath)){
                fileWriter.write(objetoJSON.toString(2));
            }catch (IOException e){
                System.out.println("Error escribiendo el archivo: " + e.getMessage());
            }

            //llamamos a metodo para ver si tiene la habilidad
            if (tieneHabilidad(objetoJSON, habilidadBuscada)) {
                System.out.println("Yennefer tiene la habilidad: " + habilidadBuscada);
            } else {
                System.out.println("Yennefer no tiene la habilidad: " + habilidadBuscada);
            }


        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
    }

    //funcion que comprueba si tiene la habilidad, tiene que ser static igual que el main
    public static boolean tieneHabilidad(JSONObject jsonObject, String habilidad) { //static?
        JSONArray habilidades = jsonObject.getJSONArray("habilidades");

        for (int i = 0; i < habilidades.length(); i++) {
            if (habilidades.getString(i).equalsIgnoreCase(habilidad)) {
                return true;
            }
        }

        return false;
    }

}


