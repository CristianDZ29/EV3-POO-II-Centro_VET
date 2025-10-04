package veterinaria.servicios;

public class Estetica implements Servicio {
    @Override
    public String getNombre() { return "Estética"; }

    @Override
    public void ejecutar() {
        System.out.println("Realizando servicio de estética");
    }
}