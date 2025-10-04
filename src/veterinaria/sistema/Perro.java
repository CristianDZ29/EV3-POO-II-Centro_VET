package veterinaria.sistema;

public class Perro extends Mascota {
    public Perro(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public String getEspecie() {
        return "Perro";
    }
}