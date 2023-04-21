
public class Main {

    public static void main(String[] args) {
        CloudStorage cloudStorage = new CloudStorage();
        cloudStorage.addUser("usuario1", 5000);

        CloudStorage myStorage = new CloudStorage();
        myStorage.addUser("Alice", 5000);


        // Agregar un nuevo archivo
        CloudStorage.CloudUser alice = myStorage.getUser("Alice");
        alice.addFile("myFile.txt", 1000);

        CloudStorage.CloudUser user1 = cloudStorage.getUser("usuario1");
        user1.addFile("Archivo1", 1000);

        // Recuperar un archivo
        CloudStorage.CloudUser.CloudFile archivo = cloudStorage.getUser("usuario1").getFile("Archivo1");
        System.out.println("Nombre del archivo: " + archivo.getName());
        System.out.println("Tamaño del archivo: " + archivo.getSize());

        // Copiar un archivo
        boolean exito = cloudStorage.getUser("usuario1").copyFile("Archivo1", "Archivo2");
        if (exito) {
            System.out.println("El archivo se copió exitosamente.");
        } else {
            System.out.println("No hay suficiente espacio en el almacenamiento para copiar el archivo.");
        }
    }
}