package javaapplication9;

/**
 *
 * @author axelt
 */
public class VanEjecutiva extends Vehiculo {
    private boolean aireAcondicionado;

    public VanEjecutiva(String patente, String marca, int capacidadPasajeros, int anioFabricacion, boolean aireAcondicionado) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    @Override
    public void realizarServicio() {
        System.out.println("VAN. Realizando traslado ejecutivo. Patente: " + getPatente() + " Aire Acondicionado: " + (aireAcondicionado ? "Si" : "No"));
    }
    
    @Override
    public String toString() {
        return super.toString() + " Tipo: Van Ejecutiva. Aire Acondicionado: " + (aireAcondicionado ? "Si" : "No");
    }
}