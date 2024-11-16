import java.util.Date;

public class Llamada {
    private int ID_Llamada;
    private Date Fecha_Hora;
    private int Duracion;
    private String estado;
    private int ID_Cliente;
    private int ID_Asesor;

    public Llamada(int ID_Llamada, Date fechaHora, int duracion, String estado, int idCliente, int idAsesor) {
        this.ID_Llamada = ID_Llamada;
        this.Fecha_Hora = Fecha_Hora;
        this.Duracion = Duracion;
        this.estado = estado;
        this.ID_Cliente = ID_Cliente;
        this.ID_Asesor = ID_Asesor;
    }


}
