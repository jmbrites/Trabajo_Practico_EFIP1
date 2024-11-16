import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Ingreso al menu
        Menu menu = new Menu();
        menu.mostrarMenuPrincipal();

        // Ejemplos de inserción con nombres de jugadores de la selección argentina
         // insertarCliente("Lionel", "Messi", "123456789", "Rosario, Argentina", "messi@example.com");
         // insertarAsesor("Emiliano", "Martínez", "987654321", "dibu.martinez@example.com");

        // Consultar e imprimir clientes y asesores
        ArrayList<Cliente> clientes = consultarClientes();
        ArrayList<Asesor> asesores = consultarAsesores();

        clientes.forEach(System.out::println);
        asesores.forEach(System.out::println);
    }

    public static void insertarCliente(String nombre, String apellido, String telefono, String direccion, String email) {
        String query = "INSERT INTO Cliente (Nombre, Apellido, Telefono, Direccion, Email) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, direccion);
            stmt.setString(5, email);
            stmt.executeUpdate();
            System.out.println("Cliente insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarAsesor(int ID_Asesor, String nombre, String apellido, String telefono, String email) {
        String query = "INSERT INTO Asesor (ID_Asesor, Nombre, Apellido, Telefono, Email) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ID_Asesor);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, telefono);
            stmt.setString(5, email);
            stmt.executeUpdate();
            System.out.println("Asesor insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Cliente> consultarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("ID_Cliente"), rs.getString("Nombre"),
                        rs.getString("Apellido"), rs.getString("Telefono"), rs.getString("Direccion"),
                        rs.getString("Email"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public static ArrayList<Asesor> consultarAsesores() {
        ArrayList<Asesor> asesores = new ArrayList<>();
        String query = "SELECT * FROM Asesor";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Asesor asesor = new Asesor(rs.getInt("ID_Asesor"), rs.getString("Nombre"),
                        rs.getString("Apellido"), rs.getString("Telefono"), rs.getString("Email"));
                asesores.add(asesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asesores;
    }
}
