import java.util.Date;

public class Llamada {
    private int id;
    private Date fechaHora;
    private int duracion;
    private String estado;
    private int idCliente;
    private int idAsesor;

    public Llamada(int id, Date fechaHora, int duracion, String estado, int idCliente, int idAsesor) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idAsesor = idAsesor;
    }

    // Getters y Setters
    // toString para imprimir información
}
