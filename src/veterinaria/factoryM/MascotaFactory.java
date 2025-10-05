package veterinaria.factoryM;


import veterinaria.sistema.Gato;
import veterinaria.sistema.Mascota;
import veterinaria.sistema.Perro;

public class MascotaFactory {

    /**
     * Crea una mascota según el tipo indicado.
     * @param tipo 1 = Perro, 2 = Gato
     * @param nombre Nombre de la mascota
     * @param edad Edad de la mascota
     * @return instancia de Mascota o null si el tipo es inválido
     */
    public static Mascota crearMascota(int tipo, String nombre, int edad) {
        return switch (tipo) {
            case 1 -> new Perro(nombre, edad);
            case 2 -> new Gato(nombre, edad);
            default -> null;
        };
    }
}
