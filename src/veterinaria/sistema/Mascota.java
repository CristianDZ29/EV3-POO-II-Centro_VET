package veterinaria.sistema;

public abstract class Mascota {
    protected String nombre;
    protected int edad;

    public Mascota(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre()
    {
        return nombre;
    }
    public int getEdad()
    {
        return edad;
    }
    public abstract String getEspecie();

    @Override
    public String toString() {
        return getEspecie() + " - " + nombre + " (" + edad + " años)";
    }
}