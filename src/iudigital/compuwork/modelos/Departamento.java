package iudigital.compuwork.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Departamento {
    private int id;
    private String nombre;
    private List<Empleado> empleados;

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", # de empleados=" + empleados.size() +
                '}';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("✅ Empleado asignado al departamento " + nombre);
    }

    public Empleado eliminarEmpleado(int idEmpleadoEliminar) {
        for (int i = 0; i <= this.empleados.size() -1 ; i++) {
            Empleado empleadoActual = this.empleados.get(i);
            if(empleadoActual.getId() == idEmpleadoEliminar) {
                this.empleados.remove(i);
                return empleadoActual;
            }
        }
        return null;
    }

    public void listarEmpleados() {
        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null,"⚠️ No hay empleados en el departamento " + nombre);
        } else {
            JOptionPane.showMessageDialog(null,"\nEmpleados en el departamento " + nombre + ":");
            for (Empleado e : empleados) {
                System.out.println(e);
            }
        }
    }
}
