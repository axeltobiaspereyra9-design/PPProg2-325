package javaapplication9;

/**
 *
 * @author axelt
 */
public class ColectivoTuristico extends Vehiculo {
    private int cantidadPisos;

    public ColectivoTuristico(String patente, String marca, int capacidadPasajeros, int anioFabricacion, int cantidadPisos) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    public void setCantidadPisos(int cantidadPisos) {
        this.cantidadPisos = cantidadPisos;
    }

    @Override
    public void realizarServicio() {
        System.out.println("Colectivo. Realizando tour panoramico en unidad de " + cantidadPisos + " piso/s. Patente: " + getPatente());
    }

    @Override
    public String toString() {
         return super.toString() + " | Tipo: Colectivo Turistico | Piso/s: " + cantidadPisos;
    }
}