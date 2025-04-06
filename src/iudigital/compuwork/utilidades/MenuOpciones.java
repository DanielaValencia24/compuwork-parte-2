package iudigital.compuwork.utilidades;


import iudigital.compuwork.modelos.Departamento;
import iudigital.compuwork.modelos.Empleado;
import iudigital.compuwork.modelos.EmpleadoPermanente;
import iudigital.compuwork.modelos.EmpleadoTemporal;

import javax.swing.JOptionPane;

public class MenuOpciones {

    

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

        do {
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un identificador numérico valido " + complemento + ":"));
            } catch (Exception e) {
                System.out.println("Ingrese un valor numérico válido, intente nuevamente.");
                id = -1;
            }
        } while (id <= 0);

        return id;
    }

    public static String mostrarMenuNombre() {
        String nombre;
        do {
            System.out.print("Ingrese un nombre: ");
            nombre = JOptionPane.showInputDialog("Ingrese un nombre: ");
        } while (nombre.isBlank());

        return nombre.trim();
    }   

    private static float mostrarMenuSalario() {
        float salario = 0;
        do {
            System.out.print("Ingrese el salario: ");
            try {
                salario = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el salario: "));
            } catch (Exception e) {
                System.out.println("Debe ingresar un valor válido para el salario");
            }
        } while (salario <= 0);

        return salario;
    }

    private static float mostrarMenuBeneficios() {
        float beneficios = 0;
        do {
            System.out.print("Ingrese el valor de los beneficios: ");
            try {
                beneficios = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de los beneficios: "));
            } catch (Exception e) {
                System.out.println("Debe ingresar un valor válido para los beneficios");
            }
        } while (beneficios <= 0);

        return beneficios;
    }

    private static int mostrarMenuDuracionMeses() {
        int duracionMeses = 0;
        do {
            System.out.print("Ingrese el valor de los meses de duración del contrato: ");
            try {
                duracionMeses = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la duracion en meses: "));
            } catch (Exception e) {
                System.out.println("Debe ingresar un valor válido para los meses de duración del contrato");
            }
        } while (duracionMeses <= 0);

        return duracionMeses;
    }
}
