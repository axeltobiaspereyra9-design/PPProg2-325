package javaapplication9;

/**
 *
 * @author axelt
 */
public class LanchaTuristica extends Vehiculo {
    private TipoMotor tipoMotor;


    public LanchaTuristica(String patente, String marca, int capacidadPasajeros, int anioFabricacion, TipoMotor tipoMotor) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.tipoMotor = tipoMotor;
    }

    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(TipoMotor tipoMotor) {
        this.tipoMotor = tipoMotor;
    }
    
    @Override
    public void realizarServicio (){
        System.out.println("Lancha. Realizando una excursion y paseo acuatico. Patente: " + getPatente() + "  Motor: " + tipoMotor);
    }
    
    @Override
    public String toString() {
         return super.toString() + "  Tipo: Lancha Turistica  Motor: " + tipoMotor;
    }
}