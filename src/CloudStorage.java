import java.util.HashMap;
import java.util.Map;

public class CloudStorage {

    private Map<String, CloudUser> users; // Almacenamiento de usuarios

    public CloudStorage() {
        users = new HashMap<>();
    }

    // Método para agregar un nuevo usuario al almacenamiento
    public void addUser(String name, int capacity) {
        users.put(name, new CloudUser(name, capacity));
    }

    // Método para recuperar un usuario del almacenamiento
    public CloudUser getUser(String name) {

        return users.get(name);
    }

    // Clase interna que representa un usuario en el almacenamiento
    public class CloudUser {

        private String name;
        private int capacity;
        private Map<String, CloudFile> files; // Almacenamiento de archivos para el usuario

        public CloudUser(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
            files = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public int getCapacity() {
            return capacity;
        }

        // Método para agregar un nuevo archivo al almacenamiento del usuario
        public boolean addFile(String name, int size) {
            if (getFreeSpace() >= size) {
                files.put(name, new CloudFile(name, size));
                return true;
            }
            return false;
        }

        // Método para recuperar un archivo del almacenamiento del usuario
        public CloudFile getFile(String name) {
            return files.get(name);
        }

        // Método para copiar un archivo en el almacenamiento del usuario
        public boolean copyFile(String sourceName, String destName) {
            CloudFile sourceFile = files.get(sourceName);
            if (sourceFile != null && getFreeSpace() >= sourceFile.getSize()) {
                files.put(destName, new CloudFile(destName, sourceFile.getSize()));
                return true;
            }
            return false;
        }

        // Método para buscar archivos por prefijo en el almacenamiento del usuario
        public CloudFile[] searchByPrefix(String prefix) {
            return files.values().stream()
                    .filter(f -> f.getName().startsWith(prefix))
                    .toArray(CloudFile[]::new);
        }

        // Método para buscar archivos por sufijo en el almacenamiento del usuario
        public CloudFile[] searchBySuffix(String suffix) {
            return files.values().stream()
                    .filter(f -> f.getName().endsWith(suffix))
                    .toArray(CloudFile[]::new);
        }

        // Método para obtener el espacio libre en el almacenamiento del usuario
        public int getFreeSpace() {
            int usedSpace = files.values().stream()
                    .mapToInt(CloudFile::getSize)
                    .sum();
            return capacity - usedSpace;
        }

        // Clase interna que representa un archivo en el almacenamiento del usuario
        public class CloudFile {

            private String name;
            private int size;

            public CloudFile(String name, int size) {
                this.name = name;
                this.size = size;
            }

            public String getName() {
                return name;
            }

            public int getSize() {
                return size;
            }
        }
    }
}

