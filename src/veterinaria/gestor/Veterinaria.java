package veterinaria.gestor;

import veterinaria.sistema.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Veterinaria {


    private static Veterinaria instance;

    private final List<Mascota> mascotas;
    private final List<Cita> citas;

    private Veterinaria() {
        this.mascotas = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public static Veterinaria getInstance() {
        if (instance == null) {
            instance = new Veterinaria();
        }
        return instance;
    }

    // ============================================================
    // CRUD MASCOTAS
    public void registrarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void listarMascotas() {
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            System.out.println("\n=== Lista de Mascotas ===");
            mascotas.forEach(System.out::println);
        }
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    // ============================================================
    // CRUD CITAS
    public void registrarCita(Cita cita) {
        citas.add(cita);
    }

    public List<Cita> buscarCitasPorFecha(LocalDate fecha) {
        return citas.stream()
                .filter(c -> c.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    public void listarCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas programadas.");
        } else {
            System.out.println("\n=== Lista de Citas ===");
            citas.forEach(System.out::println);
        }
    }

    // ============================================================
    // FUNCIONAL - STREAMS
    public List<Mascota> mascotasMenoresDe2Anios() {
        return mascotas.stream()
                .filter(m -> m.getEdad() < 2)
                .collect(Collectors.toList());
    }

    public long contarPerros() {
        return mascotas.stream().filter(m -> m instanceof Perro).count();
    }

    public long contarGatos() {
        return mascotas.stream().filter(m -> m instanceof Gato).count();
    }
}
