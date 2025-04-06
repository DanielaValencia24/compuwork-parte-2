package iudigital.compuwork.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaGestion {
    private List<Departamento> departamentos;

    public SistemaGestion(ArrayList<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Departamento> getDepartamentos() {
        return this.departamentos;
    }

    public void agregarDepartamento(Departamento departamento) {
        boolean existe = false;

        for (Departamento departamentoActual : this.departamentos) {
            if (departamentoActual.getId() == departamento.getId()) {
                existe = true;
                break;
            }
        }
        if (existe) {
            System.out.println("⚠️ El departamento con el id " + departamento.getId() + " ya existe.");
            return;
        }
        this.departamentos.add(departamento);

        System.out.println("✅ Departamento agregado.");
    }

    public void listarDepartamentos() {
        if (this.departamentos.isEmpty()) {
            System.out.println("⚠️ No hay departamentos registrados.");
        } else {
            System.out.println("\n\nImprimiendo lista de Departamentos:");
            for (Departamento departamentoActual : this.departamentos) {
                System.out.println(departamentoActual);
            }
        }
    }

    public Departamento buscarPorId(int idDepartamentoActualizar) {
        for (Departamento departamentoActual : this.departamentos) {
            if (departamentoActual.getId() == idDepartamentoActualizar) {
                return departamentoActual;
            }
        }
        return null;
    }

    public boolean existePorId(int idDepartamentoActualizar) {
        for (Departamento departamentoActual : this.departamentos) {
            if(departamentoActual.getId() == idDepartamentoActualizar) {
                return true;
            }
        }
        return false;
    }

    public void eliminarDepartamento(int idDepartamentoEliminar) {
        for (int i = 0; i <= this.departamentos.size() -1 ; i++) {
            Departamento departamentoActual = this.departamentos.get(i);
            if(departamentoActual.getId() == idDepartamentoEliminar) {
                System.out.println("Encontrado");
                this.departamentos.remove(i);
                break;
            }
        }
    }

    public Departamento buscarEmpleadoEnDepartamento(int idEmpleadoEliminar) {
        for (Departamento departamentoActual : this.departamentos) {
            boolean encontrado = false;
            for (Empleado empleado: departamentoActual.getEmpleados()) {
                if (empleado.getId() == idEmpleadoEliminar) {
                    encontrado = true;
                    break;
                }
            }
            if(encontrado) {
                return departamentoActual;
            }
        }
        return null;
    }
    
    public boolean existeEmpleado (int idEmpleado){
        for (Departamento departamentoActual : this.departamentos) {
            boolean encontrado = false;
            for (Empleado empleado: departamentoActual.getEmpleados()) {
                if (empleado.getId() == idEmpleado) {
                    encontrado = true;
                    break;
                }
            }
            if(encontrado) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.join("\n", departamentos.stream().map((d) -> d.toString()).toList());
    }

//    private static void listarEmpleados() {
//        if (empleados.isEmpty()) {
//            System.out.println("⚠️ No hay empleados registrados.");
//        } else {
//            System.out.println("\n📋 Lista de Empleados:");
//            for (Empleado e : empleados) {
//                System.out.println(e);
//            }
//        }
//    }
//
//    private static void eliminarEmpleado() {
//        System.out.print("Ingrese el ID del empleado a eliminar: ");
//        String id = scanner.nextLine();
//        empleados.removeIf(e -> e.getId().equals(id));
//        System.out.println("✅ Empleado eliminado.");
//    }
//

//
//    private static void asignarEmpleadoADepartamento() {
//        System.out.print("Ingrese el ID del empleado: ");
//        String id = scanner.nextLine();
//        Empleado empleado = empleados.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
//
//        if (empleado == null) {
//            System.out.println("⚠️ Empleado no encontrado.");
//            return;
//        }
//
//        System.out.print("Ingrese el nombre del departamento: ");
//        String nombreDepto = scanner.nextLine();
//        Departamento depto = departamentos.stream().filter(d -> d.getNombre().equalsIgnoreCase(nombreDepto)).findFirst().orElse(null);
//
//        if (depto == null) {
//            System.out.println("⚠️ Departamento no encontrado.");
//            return;
//        }
//
//        depto.agregarEmpleado(empleado);
//    }
//
//    private static void listarDepartamentos() {
//        if (departamentos.isEmpty()) {
//            System.out.println("⚠️ No hay departamentos registrados.");
//        } else {
//            System.out.println("\n📋 Lista de Departamentos:");
//            for (Departamento d : departamentos) {
//                System.out.println(d.getNombre());
//            }
//        }
//    }

}
