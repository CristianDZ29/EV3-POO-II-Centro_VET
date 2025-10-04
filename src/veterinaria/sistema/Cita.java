package veterinaria.sistema;

import veterinaria.servicios.Servicio;
import java.time.LocalDate;

public class Cita {
    private LocalDate fecha;
    private String motivo;
    private Mascota mascota;
    private Propietario propietario;
    private Servicio servicio;

    public Cita(LocalDate fecha, String motivo, Mascota mascota, Propietario propietario, Servicio servicio) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.mascota = mascota;
        this.propietario = propietario;
        this.servicio = servicio;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }
    public Mascota getMascota()
    {
        return mascota;
    }
    public Propietario getPropietario()
    {
        return propietario;
    }

    @Override
    public String toString() {
        return "Cita: " + fecha + " - " + motivo + " | Mascota: " + mascota + " | Propietario: " + propietario + " | Servicio: " + servicio.getNombre();
    }
}