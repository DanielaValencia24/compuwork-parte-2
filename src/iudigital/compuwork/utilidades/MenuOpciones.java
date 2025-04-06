package iudigital.compuwork.utilidades;

import iudigital.compuwork.modelos.Departamento;
import iudigital.compuwork.modelos.Empleado;
import iudigital.compuwork.modelos.EmpleadoPermanente;
import iudigital.compuwork.modelos.EmpleadoTemporal;
import iudigital.compuwork.modelos.ReporteDesempenioEmpleado;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class MenuOpciones {

    public static Departamento mostrarMenuConstruccionDepartamento() {
        int id = mostrarMenuIdentificadorNumerico("del departamento");
        String nombre = mostrarMenuNombre();

        Departamento departamento = new Departamento(id, nombre);

        return departamento;
    }
    
    public static ReporteDesempenioEmpleado mostrarMenuConstruccionReporteDesempenioEmpleado() {
        float calificacion = mostrarMenuCalificacion();
        LocalDate fechaGeneracion = LocalDate.now();
        int id = (int) Math.floor(Math.random() * 100000);
        ReporteDesempenioEmpleado reporte = new ReporteDesempenioEmpleado(id, fechaGeneracion, calificacion);

        return reporte;
    }


    public static Empleado mostrarMenuConstruccionEmpleado(boolean permanente) {
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
                JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido, intente nuevamente.");
                id = -1;
            }
        } while (id <= 0);

        return id;
    }

    private static float mostrarMenuBeneficios() {
        float beneficios = 0;
        do {
            try {
                beneficios = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de los beneficios: "));
            } catch (Exception e) {
                System.out.println("Debe ingresar un valor válido para los beneficios");
            }
        } while (beneficios <= 0);

        return beneficios;
    }
    
    public static String mostrarMenuNombre() {
        String nombre;
        do {
            nombre = JOptionPane.showInputDialog("Ingrese un nombre: ");
        } while (nombre.isBlank());

        return nombre.trim();
    }   

    private static float mostrarMenuSalario() {
        float salario = 0;
        do {
            try {
                salario = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el salario: "));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor válido para el salario");

            }
        } while (salario <= 0);

        return salario;
    }

    private static float mostrarMenuCalificacion() {
        float calificacion = -1;
        do {
            try {
                calificacion = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de la calificacion 1~10: "));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor válido para la calificacion");
                calificacion = -1;
            }
        } while (calificacion < 0 || calificacion > 10);

        return calificacion;
    }

    private static int mostrarMenuDuracionMeses() {
        int duracionMeses = 0;
        do {
            try {
                duracionMeses = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la duracion en meses: "));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor válido para los meses de duración del contrato");
            }
        } while (duracionMeses <= 0);

        return duracionMeses;
    }
}
