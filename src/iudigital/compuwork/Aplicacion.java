package iudigital.compuwork;

import iudigital.compuwork.modelos.*;
import iudigital.compuwork.utilidades.MenuOpciones;

import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion {

    private static SistemaGestion sistemaGestion;


    public static void main(String[] args) {
        sistemaGestion = new SistemaGestion(new ArrayList<>());

        int opcionElegida;
        do {
            opcionElegida = MenuOpciones.mostrarMenuPrincipal();

            switch (opcionElegida) {
                case 1 -> crearDepartamento();
                case 2 -> listarDepartamentos();
                case 3 -> actualizarDepartamento();
                case 4 -> eliminarDepartamento();
                case 5 -> agregarEmpleado(true);
                case 6 -> agregarEmpleado(false);
                case 7 -> listarEmpleadosPorDepartamento();
                case 8 -> eliminarEmpleado();
                case 9 -> transferirEmpleado();
                case 10 -> listarTodosLosEmpleados();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcionElegida != 0);
    }




    private static void agregarEmpleado(boolean permanente) {
        Scanner scanner = new Scanner(System.in);

        int idDepartamento = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        Departamento departamento = sistemaGestion.buscarPorId(idDepartamento);

        if(departamento == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }

        Empleado nuevoEmpleado = MenuOpciones.mostrarMenuConstruccionEmpleado(permanente);

        boolean existeEmpleado = sistemaGestion.existeEmpleado(nuevoEmpleado.getId());

        if (existeEmpleado) {
            System.out.println("⚠️ El empleado con el ID " + nuevoEmpleado.getId() + " ya existe en el sistema");
            return;
        }
        
        departamento.agregarEmpleado(nuevoEmpleado);
        System.out.println("✅ Empleado agregado con éxito.");
    }

    private static void crearDepartamento() {
        Departamento nuevoDepartamento = MenuOpciones.mostrarMenuConstruccionDepartamento();

        sistemaGestion.agregarDepartamento(nuevoDepartamento);
    }

    private static void listarDepartamentos() {
        sistemaGestion.listarDepartamentos();
    }

    private static void actualizarDepartamento() {
        int idDepartamentoActualizar = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        Departamento departamentoActualizar = sistemaGestion.buscarPorId(idDepartamentoActualizar);

        if (departamentoActualizar == null) {
            System.out.println("⚠️ No se encontró un departamento con el ID " + idDepartamentoActualizar);
            return;
        }

        String nuevoNombre = MenuOpciones.mostrarMenuNombre();

        departamentoActualizar.setNombre(nuevoNombre);
        System.out.println("✔ Departamento actualizado con éxito");

    }
    private static void eliminarDepartamento() {
        int idDepartamentoEliminar = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        boolean existe = sistemaGestion.existePorId(idDepartamentoEliminar);

//        if (existe == false) {}
        if (!existe) {
            System.out.println("⚠️ No se encontró un departamento con el ID " + idDepartamentoEliminar);
            return;
        }
        // En este punto, estamos seguros que el departamento a eliminar si existe
        sistemaGestion.eliminarDepartamento(idDepartamentoEliminar);

        System.out.println("✔ Departamento eliminado con éxito");

    }

    private static void listarEmpleadosPorDepartamento() {
        int idDepartamento = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        Departamento departamento = sistemaGestion.buscarPorId(idDepartamento);

        if (departamento == null) {
            System.out.println("⚠️ No se encontró un departamento con el ID " + idDepartamento);
            return;
        }
        departamento.listarEmpleados();
    }

    private static void eliminarEmpleado() {
        int idEmpleadoEliminar = MenuOpciones.mostrarMenuIdentificadorNumerico("del empleado a eliminar");

        Departamento departamento = sistemaGestion.buscarEmpleadoEnDepartamento(idEmpleadoEliminar);

        if (departamento == null) {
            System.out.println("⚠️ No se encontró un departamento que contenga un empleado con el ID " + idEmpleadoEliminar);
            return;
        }
        Empleado empleadoEliminado = departamento.eliminarEmpleado(idEmpleadoEliminar);
        System.out.println("✅ Empleado " + empleadoEliminado.getNombre() + " eliminado del departamento " + departamento.getNombre());
    }

    private static void transferirEmpleado() {
        int idEmpleadoTranferir = MenuOpciones.mostrarMenuIdentificadorNumerico("del empleado a transferir");

        Departamento departamentoOrigen = sistemaGestion.buscarEmpleadoEnDepartamento(idEmpleadoTranferir);

        if (departamentoOrigen == null) {
            System.out.println("⚠️ No se encontró un departamento que contenga un empleado con el ID " + idEmpleadoTranferir);
            return;
        }

        int idDepartamentoDestino = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento de destino: ");
        Departamento departamentoDestino = sistemaGestion.buscarPorId(idDepartamentoDestino);

        if (departamentoDestino == null) {
            System.out.println("⚠️ No se encontró un departamento de destino con el id " + idDepartamentoDestino);
            return;
        }

        if (departamentoOrigen.getId() == departamentoDestino.getId()) {
            System.out.println("⚠️ Los departamentos deben ser diferentes ");
            return;
        }
        
        
        Empleado empleadoEliminado = departamentoOrigen.eliminarEmpleado(idEmpleadoTranferir);
        departamentoDestino.agregarEmpleado(empleadoEliminado);
    }

    private static void listarTodosLosEmpleados() {
        for (Departamento departamentoActual: sistemaGestion.getDepartamentos()) {
            System.out.println("\n\n --- Departamento " + departamentoActual.getNombre() + " ---");
            departamentoActual.listarEmpleados();
        }
    }
}
