package javaapplication9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author axelt
 */
public class Main {
    private static ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final int ANIO_ACTUAL = 2026;

    private static void mostrarMenu() {
        System.out.println("--------------------------------");
        System.out.println("Sistema de Gestion de transporte");
        System.out.println("--------------------------------");
        System.out.println("1): Agregar vehiculo");
        System.out.println("2): Mostrar todos los vehiculos");
        System.out.println("3): Realizar servicios turisticos");
        System.out.println("4): Buscar vehiculo por patente");
        System.out.println("5): Mostrar vehiculo/s con capacidad mayor a 'X' valor");
        System.out.println("6): Mostrar vehiculos ordenados por año de manera descendente");
        System.out.println("7): Mostrar vehiculos ordenados por capacidad de manera descendente");
        System.out.println("8): Salir");
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        int opcion = 0;
        
        do {
            mostrarMenu();
            try {
                System.out.print("Selecciona una opcion: ");
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        agregarVehiculo();
                        break;
                    case 2:
                        mostrarVehiculos();
                        break;
                    case 3:
                        realizarServiciosVehiculos();
                        break;
                    case 4:
                        buscarPorPatente();
                        break;
                    case 5:
                        mostrarMayorCapacidad();
                        break;
                    case 6:
                        ordenarPorAnioDescendente();
                        break;
                    case 7:
                        ordenarPorCapacidadDescendente();
                        break;
                    case 8:
                        System.out.println("\nSaliendo....");
                        break;
                    default:
                        System.out.println("\nOpcion incorrecta. Escribi un numero del 1 al 8.");                                       
                }
            } catch (NumberFormatException e){
                System.out.println("\nEntrada incorrecta. Escribi un numero entero del 1 al 8.");
                opcion = 0;
            }
            System.out.println();
        } while (opcion != 8);
    }
    
    private static void agregarVehiculo() {
        System.out.println("\n-------Tipo de Vehiculos:--------");
        System.out.println("1). Colectivo Turistico");
        System.out.println("2). Van Ejecutiva");
        System.out.println("3). Lancha Turistica");
        System.out.print("------------ Elegi el tipo (1 al 3): ");
        
        int tipo = 0;
        try { 
            tipo = Integer.parseInt(scanner.nextLine());
            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo de vehiculo incorrecto.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada incorrecta. Tiene que ser un numero.");
            return;
        }

        String patente = leerPatenteValida();
        if (patente == null) return; 

        String marca = leerMarcaValida();
        int capacidad = leerCapacidadValida();
        int anio = leerAnioValido();

        if (tipo == 1) {
            int pisos = leerPisosValidos();
            listaVehiculos.add(new ColectivoTuristico(patente, marca, capacidad, anio, pisos));
            System.out.println("\nColectivo turistico agregado correctamente");
        } else if (tipo == 2) {
            boolean aire = leerAireValido();
            listaVehiculos.add(new VanEjecutiva(patente, marca, capacidad, anio, aire));
            System.out.println("\nVan ejecutiva agregada correctamente");
        } else if (tipo == 3) {
            TipoMotor motor = leerMotorValido();
            listaVehiculos.add(new LanchaTuristica(patente, marca, capacidad, anio, motor));
            System.out.println("\nLancha turistica agregada correctamente");
        }
    }  

    private static String leerPatenteValida(){
        while (true){
            System.out.print("Ingresa la patente: ");
            String patente = scanner.nextLine().trim().toUpperCase();
            if (patente.isEmpty()){
                System.out.println("La patente no puede estar vacia.");
                continue;
            }
            
            boolean existe = false;
            for (Vehiculo v : listaVehiculos){
                if (v.getPatente().equals(patente)){
                    existe = true;
                    break;
                }
            }
            
            if (existe){
                System.out.println("Entrada incorrecta, ya existe otro vehiculo con esa patente.");
                System.out.print("¿Intentar con otra patente? (s/n): ");
                String rta = scanner.nextLine().trim();
                if (rta.equalsIgnoreCase("n")){
                    return null;
                }
            } else {
                return patente;
            }
        }
    }

    private static String leerMarcaValida(){
        while (true){
            System.out.print("Ingresa la marca: ");
            String marca = scanner.nextLine().trim();
            if (!marca.isEmpty()){
                return marca;
            }
            System.out.println("La marca no puede estar vacia");
        }
    }

    private static int leerCapacidadValida(){
        while (true){
            try {
                System.out.print("Ingrese capacidad de pasajeros (> 0): ");
                int capacidad = Integer.parseInt(scanner.nextLine());
                if (capacidad > 0) {
                    return capacidad;
                }
                System.out.println("La capacidad tiene que ser mayor a cero");
            } catch (NumberFormatException e){
                System.out.println("Entrada incorrecta. Ingresa un numero entero");
            }
        }
    }

    private static int leerAnioValido(){
        while (true){
            try {
                System.out.print("Ingresa el año de Fabricacion (1990 - " + ANIO_ACTUAL + "): ");
                int anio = Integer.parseInt(scanner.nextLine());
                if (anio >= 1990 && anio <= ANIO_ACTUAL) {
                    return anio;
                }
                System.out.println("Entrada incorrecta. El año debe estar entre 1990 y " + ANIO_ACTUAL + "."); 
            } catch (NumberFormatException e) {
                System.out.println("Entrada incorrecta. Ingresa un numero entero.");
            }
        }    
    }
    
    private static int leerPisosValidos(){
        while (true) {
            try {
                System.out.print("Ingresa la cantidad de pisos (1-2): ");
                int pisos = Integer.parseInt(scanner.nextLine());
                if (pisos == 1 || pisos == 2){
                    return pisos;
                }
                System.out.println("Entrada Incorrecta. La cantidad de pisos debe ser 1 o 2.");
            } catch (NumberFormatException e){
                System.out.println("Entrada incorrecta. Ingresa un numero entre 1-2");
            }
        }
    }
    
    private static boolean leerAireValido() {
        while (true){
            System.out.print("¿Posee aire acondicionado? (s/n) : ");
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("s") || entrada.equals("si")){
                return true;
            } else if (entrada.equals("n") || entrada.equals("no")){
                return false;
            }
            System.out.println("Entrada Incorrecta. Escribi 's' para si o 'n' para no");
        }
    }
    
    private static TipoMotor leerMotorValido(){
        while (true){
            System.out.print("Ingresa el tipo de motor (Nafta / Diesel / Electrico): ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            try{
                return TipoMotor.valueOf(entrada);
            } catch (IllegalArgumentException e){
                System.out.println("Entrada Incorrecta. Ingresa un tipo de motor valido.");
            }
        }
    }

    private static void mostrarVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("\nNo hay vehículos registrados.");
            return;
        }
        System.out.println("\n--- LISTA DE VEHICULOS ---");
        for (Vehiculo v : listaVehiculos) {
            System.out.println(v);
        }
    }

    private static void realizarServiciosVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("\nNo hay vehiculos para realizar servicios.");
            return;
        }
        System.out.println("\n----- Realizando servicios Turisticos -----");
        for (Vehiculo v : listaVehiculos) {
            v.realizarServicio();
        }
    }

    private static void buscarPorPatente() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("\nNo hay vehiculos cargados.");
            return;
        }
        System.out.print("\nIngresa la patente a buscar: ");
        String patenteBuscada = scanner.nextLine().trim().toUpperCase();

        boolean encontrado = false;
        for (Vehiculo v : listaVehiculos) {
            if (v.getPatente().equals(patenteBuscada)) {
                System.out.println("\nVehiculo Encontrado:");
                System.out.println(v);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\nNo se encontró ningún vehículo con esa patente.");
        }
    }

    private static void mostrarMayorCapacidad() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("\nNo hay vehículos cargados.");
            return;
        }
        try {
            System.out.print("\nIngrese el valor minimo de capacidad de pasajeros: ");
            int minCapacidad = Integer.parseInt(scanner.nextLine());

            boolean hayCoincidentes = false;
            System.out.println("\n----- Vehiculos con capacidad mayor a " + minCapacidad + " -----");
            for (Vehiculo v : listaVehiculos) {
                if (v.getCapacidadPasajeros() > minCapacidad) {
                    System.out.println(v);
                    hayCoincidentes = true;
                }
            }
            if (!hayCoincidentes) {
                System.out.println("No se encontraron vehiculos que superen dicha capacidad.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero entero valido.");
        }
    }

    private static void ordenarPorAnioDescendente() {
        if (listaVehiculos.size() < 2) {
            System.out.println("\nNo hay suficientes vehiculos para ordenar.");
            return;
        }
        for (int i = 0; i < listaVehiculos.size() - 1; i++) {
            for (int j = 0; j < listaVehiculos.size() - 1 - i; j++) {
                if (listaVehiculos.get(j).getAnioFabricacion() < listaVehiculos.get(j + 1).getAnioFabricacion()) {
                    Vehiculo aux = listaVehiculos.get(j);
                    listaVehiculos.set(j, listaVehiculos.get(j + 1));
                    listaVehiculos.set(j + 1, aux);
                }
            }
        }
        System.out.println("\n----- Vehiculos ordenados por año (Descendentemente) -----");
        mostrarVehiculos();
    }

    private static void ordenarPorCapacidadDescendente() {
        if (listaVehiculos.size() < 2) {
            System.out.println("\nNo hay suficientes vehiculos para ordenar.");
            return;
        }
        for (int i = 0; i < listaVehiculos.size() - 1; i++) {
            for (int j = 0; j < listaVehiculos.size() - 1 - i; j++) {
                if (listaVehiculos.get(j).getCapacidadPasajeros() < listaVehiculos.get(j + 1).getCapacidadPasajeros()) {
                    Vehiculo aux = listaVehiculos.get(j);
                    listaVehiculos.set(j, listaVehiculos.get(j + 1));
                    listaVehiculos.set(j + 1, aux);
                }
            }
        }
        System.out.println("\n---- Vehiculos ordenados por capacidad (Descendentemente) ----");
        mostrarVehiculos();
    }
}