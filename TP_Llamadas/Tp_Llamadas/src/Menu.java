import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n===== Sistema de Gestión de Llamadas =====");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Asesores");
            System.out.println("3. Registrar Llamada");
            System.out.println("4. Consultar Llamadas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = obtenerEntrada();

            switch (opcion) {
                case 1:
                    gestionarClientes();
                    break;
                case 2:
                    gestionarAsesores();
                    break;
                case 3:
                    registrarLlamada();
                    break;
                case 4:
                    consultarLlamadas();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private int obtenerEntrada() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
            return -1;
        }
    }

    private void gestionarClientes() {
        System.out.println("\n--- Gestión de Clientes ---");
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();

        try (Connection conn = DatabaseConnector.connect()) {
            String sql = "INSERT INTO Cliente (nombre, apellido, telefono, email) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, email);
            stmt.executeUpdate();
            System.out.println("Cliente registrado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gestionarAsesores() {

        System.out.println("\n--- Gestión de Asesores ---");
        System.out.print("Ingrese el ID del asesor: ");
        int ID_Asesor = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nombre del asesor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del asesor: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el email del asesor: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese el telefono del asesor: ");
        String telefono = scanner.nextLine();


        try (Connection conn = DatabaseConnector.connect()) {
            String sql = "INSERT INTO Asesor (ID_Asesor, nombre, apellido, email, telefono) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ID_Asesor);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, email);
            stmt.setString(5, telefono);
            stmt.executeUpdate();
            System.out.println("Asesor registrado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registrarLlamada() {
        System.out.println("\n--- Registro de Llamada ---");
        // Fecha y hora actual automáticamente
        LocalDateTime Fecha_Hora = LocalDateTime.now();
        String Fecha_HoraStr = Fecha_Hora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        //Ingresar otros datos
        System.out.print("Ingrese el ID de Llamada: ");
        int ID_Llamada = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el ID del cliente: ");
        int ID_Cliente = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el ID del asesor: ");
        int ID_Asesor = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese duracion de la llamada(segundos): ");
        int Duracion = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el resultado de la llamada(Pendiente,Finalizada o Cancelada): ");
        String Estado = scanner.nextLine();

        try (Connection conn = DatabaseConnector.connect()) {
            String sql = "INSERT INTO Llamada (Fecha_Hora, duracion, estado, ID_Llamada, ID_Asesor, ID_Cliente) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Fecha_HoraStr);
            stmt.setInt(2, Duracion);
            stmt.setString(3, Estado);
            stmt.setInt(4, ID_Llamada);
            stmt.setInt(5, ID_Asesor);
            stmt.setInt(6, ID_Cliente);
            stmt.executeUpdate();
            System.out.println("Llamada registrada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consultarLlamadas() {
        System.out.println("\n--- Consulta de Llamadas ---");
        try (Connection conn = DatabaseConnector.connect()) {
            String sql = "SELECT * FROM Llamada";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID_Llamada") + ", Cliente: " + rs.getInt("ID_Cliente")
                        + ", Asesor: " + rs.getInt("ID_Asesor") + ", Resultado: " + rs.getString("Estado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
