package veterinaria.servicios;

public class Consulta implements Servicio {
    @Override
    public String getNombre() { return "Consulta"; }

    @Override
    public void ejecutar() {
        System.out.println("Realizando consulta médica");
    }
}