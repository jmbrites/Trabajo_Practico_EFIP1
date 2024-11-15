public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String email;

    public Cliente(int id, String nombre, String apellido, String telefono, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    // Getters y Setters
    // toString para imprimir información
}
