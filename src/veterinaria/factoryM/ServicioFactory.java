package veterinaria.factoryM;

import veterinaria.servicios.Consulta;
import veterinaria.servicios.Estetica;
import veterinaria.servicios.Servicio;
import veterinaria.servicios.Vacunacion;

public class ServicioFactory {

    /**
     * Crea un servicio según el tipo indicado.
     * @param tipo 1 = Vacunación, 2 = Consulta, 3 = Estética
     * @return instancia de Servicio o null si el tipo es inválido
     */
    public static Servicio crearServicio(int tipo) {
        return switch (tipo) {
            case 1 -> new Vacunacion();
            case 2 -> new Consulta();
            case 3 -> new Estetica();
            default -> null;
        };
    }
}