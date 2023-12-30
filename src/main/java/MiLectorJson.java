import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MiLectorJson {

    public static void main(String[] args) {
        String filePath = "src/main/java/MiLectorJson.java"; // ruta real de archivo JSON

        //try/catch para leer Json
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject objetoJSON = new JSONObject(content);

            // Acceder al elemento "nombre" y mostrarlo:
            String nombre = objetoJSON.getString("nombre");
            System.out.println("El nombre en el JSON es: " + nombre);

        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}
