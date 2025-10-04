package veterinaria.servicios;


public class Vacunacion implements Servicio {
    @Override
    public String getNombre() { return "Vacunación"; }

    @Override
    public void ejecutar() {
        System.out.println("Aplicando vacuna");
    }
}