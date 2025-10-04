package veterinaria.sistema;

public class Gato extends Mascota {
    public Gato(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public String getEspecie() {
        return "Gato";
    }
}