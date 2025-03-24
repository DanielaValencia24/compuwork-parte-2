package iudigital.compuwork.utilidades;


import iudigital.compuwork.modelos.Departamento;
import iudigital.compuwork.modelos.Empleado;
import iudigital.compuwork.modelos.EmpleadoPermanente;
import iudigital.compuwork.modelos.EmpleadoTemporal;

import java.util.Scanner;

public class MenuOpciones {

    public static int mostrarMenuPrincipal() {
        int opcionMenuGeneral = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("""
                    \n\n--- Menú Principal ---
                    1. Crear departamento
                    2. Listar departamentos
                    3. Actualizar departamento
                    4. Eliminar departamento
                    5. Agregar empleado permanente
                    6. Agregar empleado temporal
                    7. Listar empleados por departamento
                    8. Eliminar empleado de departamento
                    9. Transferir empleado de un departamento a otro
                    10. Listar todos los empleados por departamento
                    0. Salir""");
            System.out.print("Seleccione una opción: ");

            try {
                opcionMenuGeneral = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un valor numérico válido, intente nuevamente.");
                scanner.nextLine();
            }

            if(opcionMenuGeneral <0 || opcionMenuGeneral > 10) {
                System.out.println("\n *** Opción no válida *** \n");
            }

        } while(opcionMenuGeneral <0 || opcionMenuGeneral > 10);


        return opcionMenuGeneral;
    }

    public static Departamento mostrarMenuConstruccionDepartamento() {
        System.out.println("\n\n--- Menú de Creación de Departamento ---");
        int id = mostrarMenuIdentificadorNumerico("del departamento");
        String nombre = mostrarMenuNombre();

        Departamento departamento = new Departamento(id, nombre);

        return departamento;
    }


    public static Empleado mostrarMenuConstruccionEmpleado(boolean permanente) {
        System.out.println("\n\n--- Menú de Creación de Empleado " + ( permanente ? "Permanente" : "Temporal") + " ---");
        int id = mostrarMenuIdentificadorNumerico("del empleado");
        String nombre = mostrarMenuNombre();
        float salario = mostrarMenuSalario();

        if(permanente) {
            float beneficios = mostrarMenuBeneficios();
            EmpleadoPermanente nuevoEmpleadoPermanente = new EmpleadoPermanente(id, nombre, salario, beneficios);
            return nuevoEmpleadoPermanente;
        } else {
            int duracionMeses = mostrarMenuDuracionMeses();
            EmpleadoTemporal nuevoEmpleadoTemporal = new EmpleadoTemporal(id, nombre, salario, duracionMeses);
            return nuevoEmpleadoTemporal;
        }
    }




    public static int mostrarMenuIdentificadorNumerico(String complemento) {
        int id = -1;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Ingrese un identificador numérico " + complemento + ": ");
            try {
                id = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un valor numérico válido, intente nuevamente.");
                scanner.nextLine();
                id = -1;
            }
        } while (id <= 0);

        return id;
    }

    public static String mostrarMenuNombre() {
        String nombre;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Ingrese un nombre: ");
            nombre = scanner.nextLine();
        } while (nombre.isBlank());

        return nombre.trim();
    }   

    private static float mostrarMenuSalario() {
        float salario = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Ingrese el salario: ");
            try {
                salario = scanner.nextFloat();
            } catch (Exception e) {
                System.out.println("Debe ingresar un valor válido para el salario");
                scanner.nextLine();
            }
        } while (salario <= 0);

        return salario;
    }

    private static float mostrarMenuBeneficios() {
        float beneficios = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Ingrese el valor de los beneficios: ");
            try {
                beneficios = scanner.nextFloat();
            } catch (Exception e) {
                System.out.println("Debe ingresar un valor válido para los beneficios");
                scanner.nextLine();
            }
        } while (beneficios <= 0);

        return beneficios;
    }

    private static int mostrarMenuDuracionMeses() {
        int duracionMeses = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Ingrese el valor de los meses de duración del contrato: ");
            try {
                duracionMeses = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Debe ingresar un valor válido para los meses de duración del contrato");
                scanner.nextLine();
            }
        } while (duracionMeses <= 0);

        return duracionMeses;
    }
}
