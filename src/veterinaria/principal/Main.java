package veterinaria.principal;


import veterinaria.gestor.Veterinaria;
import veterinaria.sistema.*;
import veterinaria.servicios.*;
import veterinaria.factoryM.MascotaFactory;
import veterinaria.factoryM.ServicioFactory;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Veterinaria vet = Veterinaria.getInstance();

    public static void main(String[] args) {
        // Cargar registros iniciales
        datosIniciales();

        // Menú principal
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Elige una opción: ");
            switch (opcion) {
                case 1 -> registrarMascota();
                case 2 -> registrarCita();
                case 3 -> listarMascotasMenores2();
                case 4 -> contarPerrosYGatos();
                case 5 -> listarMascotas();
                case 6 -> buscarCitasPorFecha();
                case 7 -> listarCitas();
                case 8 -> {
                    System.out.println("--- Saliendo del sistema ---");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // ===============================================================
    // MENÚ
    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA VETERINARIA =====");
        System.out.println("1. Registrar nueva mascota");
        System.out.println("2. Registrar nueva cita");
        System.out.println("3. Listar mascotas menores de 2 años");
        System.out.println("4. Contar perros y gatos");
        System.out.println("5. Mostrar todas las mascotas");
        System.out.println("6. Buscar citas por fecha");
        System.out.println("7. Ver todas las citas programadas");
        System.out.println("8. Salir");
        System.out.println("===============================================");
    }

    // ===============================================================
    // DATOS INICIALES
    private static void datosIniciales() {
        Propietario p1 = new Propietario("Santiago", "12345");
        Propietario p2 = new Propietario("Kevin", "67890");

        Mascota m1 = new Perro("Polo", 1);
        Mascota m2 = new Gato("Sombra", 3);

        vet.registrarMascota(m1);
        vet.registrarMascota(m2);

        Cita c1 = new Cita(LocalDate.now(), "Vacunación anual", m1, p1, new Vacunacion());
        Cita c2 = new Cita(LocalDate.now(), "Consulta general", m2, p2, new Consulta());

        vet.registrarCita(c1);
        vet.registrarCita(c2);

        System.out.println("Datos iniciales cargados correctamente.");
    }

    // ===============================================================
    // OPCIÓN 1: REGISTRAR NUEVA MASCOTA
    private static void registrarMascota() {
        System.out.println("\n=== Registrar Nueva Mascota ===");
        System.out.print("Nombre de la mascota: ");
        String nombre = scanner.nextLine();

        int edad = leerEntero("Edad de la mascota (en años): ");
        System.out.print("Especie (1=Perro / 2=Gato): ");
        int tipo = leerEntero("");

        Mascota mascota = MascotaFactory.crearMascota(tipo, nombre, edad);
        if (mascota == null) {
            System.out.println("Especie inválida. No se registró la mascota.");
            return;
        }
        vet.registrarMascota(mascota);
        System.out.println("Mascota registrada: " + mascota);
    }

    // ===============================================================
    // OPCIÓN 2: REGISTRAR NUEVA CITA
    private static void registrarCita() {
        System.out.println("\n=== Registrar Nueva Cita ===");

        System.out.print("Nombre del propietario: ");
        String nombreProp = scanner.nextLine();
        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();
        Propietario propietario = new Propietario(nombreProp, contacto);

        Mascota mascota = elegirOMRegistrarMascota();
        if (mascota == null) {
            System.out.println("No se pudo registrar la cita porque no se seleccionó una mascota.");
            return;
        }

        System.out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine();

        System.out.println("Tipo de servicio (1=Vacunación / 2=Consulta / 3=Estética): ");
        int tipoServicio = leerEntero("");
        Servicio servicio = ServicioFactory.crearServicio(tipoServicio);
        if (servicio == null) {
            System.out.println("Servicio inválido.");
            return;
        }

        System.out.print("Fecha (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            System.out.println("Fecha inválida.");
            return;
        }

        Cita cita = new Cita(fecha, motivo, mascota, propietario, servicio);
        vet.registrarCita(cita);
        System.out.println("Cita registrada: " + cita);
    }

    // ===============================================================
    // OPCIÓN 3: LISTAR MASCOTAS MENORES DE 2 AÑOS
    private static void listarMascotasMenores2() {
        System.out.println("\n=== Mascotas menores de 2 años ===");
        List<Mascota> menores = vet.mascotasMenoresDe2Anios();
        if (menores.isEmpty()) {
            System.out.println("No hay mascotas menores de 2 años registradas.");
        } else {
            menores.forEach(System.out::println);
        }
    }

    // ===============================================================
    // OPCIÓN 4: CONTAR PERROS Y GATOS
    private static void contarPerrosYGatos() {
        long perros = vet.contarPerros();
        long gatos = vet.contarGatos();
        System.out.println("\n=== Conteo de Mascotas ===");
        System.out.println("Perros: " + perros);
        System.out.println("Gatos: " + gatos);
    }

    // ===============================================================
    // OPCIÓN 5: LISTAR TODAS LAS MASCOTAS
    private static void listarMascotas() {
        vet.listarMascotas();
    }

    // ===============================================================
    // OPCIÓN 6: BUSCAR CITAS POR FECHA
    private static void buscarCitasPorFecha() {
        System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        try {
            LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            List<Cita> citas = vet.buscarCitasPorFecha(fecha);
            if (citas.isEmpty()) {
                System.out.println("No hay citas en esa fecha.");
            } else {
                citas.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Fecha inválida.");
        }
    }

    // ===============================================================
    // OPCIÓN 7: LISTAR TODAS LAS CITAS
    private static void listarCitas() {
        vet.listarCitas();
    }

    // ===============================================================
    // MÉTODOS AUXILIARES
    private static int leerEntero(String mensaje) {
        int valor;
        while (true) {
            try {
                if (!mensaje.isEmpty()) System.out.print(mensaje);
                valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
            }
        }
    }

    private static Mascota elegirOMRegistrarMascota() {
        List<Mascota> listaMascotas = vet.getMascotas();
        if (listaMascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas. Debes registrar una nueva.");
            return registrarMascotaDesdeCita();
        }

        System.out.println("\n=== Mascotas Registradas ===");
        for (int i = 0; i < listaMascotas.size(); i++) {
            System.out.println((i + 1) + ". " + listaMascotas.get(i));
        }

        System.out.print("¿Deseas usar una mascota existente? (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        if (respuesta.equals("S")) {
            int seleccion = leerEntero("Elige el número de la mascota: ");
            if (seleccion < 1 || seleccion > listaMascotas.size()) {
                System.out.println("Selección inválida.");
                return null;
            }
            return listaMascotas.get(seleccion - 1);
        } else {
            return registrarMascotaDesdeCita();
        }
    }

    private static Mascota registrarMascotaDesdeCita() {
        System.out.println("\n=== Registrar Nueva Mascota ===");
        System.out.print("Nombre de la mascota: ");
        String nombre = scanner.nextLine();
        int edad = leerEntero("Edad de la mascota (en años): ");
        System.out.print("Especie (1=Perro / 2=Gato): ");
        int tipo = leerEntero("");

        Mascota mascota = MascotaFactory.crearMascota(tipo, nombre, edad);
        if (mascota == null) {
            System.out.println("Especie inválida.");
            return null;
        }

        vet.registrarMascota(mascota);
        System.out.println("Mascota registrada exitosamente.");
        return mascota;
    }
}
