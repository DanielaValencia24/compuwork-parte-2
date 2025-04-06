/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iudigital.compuwork.utilidades;

import iudigital.compuwork.modelos.Departamento;
import iudigital.compuwork.modelos.Empleado;
import iudigital.compuwork.modelos.EmpleadoPermanente;
import iudigital.compuwork.modelos.EmpleadoTemporal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author j3c-dev
 */
public class InicializadorBaseDatos {
    
public static List<Departamento> cargarInformacion() {
        List<Departamento> departamentos = new ArrayList<>();
        String rutaArchivo = "departamentos-db.csv";
        File archivo = new File(rutaArchivo);
                
        if(!archivo.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar encabezado
                }

                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];

                Departamento departamento = new Departamento(id, nombre);
                
                List<EmpleadoTemporal> empleadosTemporales = cargarEmpleadosTemporales(id);
                empleadosTemporales.forEach((empleado) -> {
                    departamento.agregarEmpleado(empleado);
                });
                
                List<EmpleadoPermanente> empleadosPermanentes = cargarEmpleadosPermanentes(id);
                empleadosPermanentes.forEach((empleado) -> {
                    departamento.agregarEmpleado(empleado);
                });
                
                departamentos.add(departamento);
            }

        } catch (IOException e) {
            System.err.println("⚠️ Error leyendo el archivo: " + e.getMessage());
        }

        return departamentos;
    }    

private static List<EmpleadoTemporal> cargarEmpleadosTemporales(int idDepartamentoActual){
     List<EmpleadoTemporal> empleadosTemporales = new ArrayList<>();
        String rutaArchivo = "empleados-temporales.csv";
        File archivo = new File(rutaArchivo);
                
        if(!archivo.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar encabezado
                }

                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double salario = Double.parseDouble(datos[2]);
                int duracionContrato = Integer.parseInt(datos[3]);
                int idDepartamento = Integer.parseInt(datos[4]);
                

                EmpleadoTemporal empleadoTemporal = new EmpleadoTemporal(id, nombre, salario, duracionContrato);
                if (idDepartamento == idDepartamentoActual){
                    empleadosTemporales.add(empleadoTemporal);
                }
                
            }

        } catch (IOException e) {
            System.err.println("⚠️ Error leyendo el archivo: " + e.getMessage());
        }

        return empleadosTemporales;
    }


private static List<EmpleadoPermanente> cargarEmpleadosPermanentes(int idDepartamentoActual){
     List<EmpleadoPermanente> empleadosPermanentes = new ArrayList<>();
        String rutaArchivo = "empleados-permanentes.csv";
        File archivo = new File(rutaArchivo);
                
        if(!archivo.exists()) {
            return new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar encabezado
                }

                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double salario = Double.parseDouble(datos[2]);
                double beneficios = Double.parseDouble(datos[3]);
                int idDepartamento = Integer.parseInt(datos[4]);
                

                EmpleadoPermanente empleadoPermanente = new EmpleadoPermanente(id, nombre, salario, beneficios);
                if (idDepartamento == idDepartamentoActual){
                    empleadosPermanentes.add(empleadoPermanente);
                }
                
            }

        } catch (IOException e) {
            System.err.println("⚠️ Error leyendo el archivo: " + e.getMessage());
        }

        return empleadosPermanentes;
    }


}

