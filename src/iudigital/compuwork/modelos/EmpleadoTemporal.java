package iudigital.compuwork.modelos;

public class EmpleadoTemporal extends Empleado {
    private final int duracionContrato; // en meses

    public EmpleadoTemporal(int id, String nombre, double salario, int duracionContrato) {
        super(id, nombre, salario);
        this.duracionContrato = duracionContrato;
    }

    public int getDuracionContrato() {
        return duracionContrato;
    }

    @Override
    public String getTipoEmpleado() {
        return "Empleado Temporal";
    }

    @Override
    public String toString() {
        return getTipoEmpleado() + " - ID: " + id + ", Nombre: " + nombre + ", Salario: $" + salario + ", Duración contrato: " + duracionContrato + " meses";
    }
}
