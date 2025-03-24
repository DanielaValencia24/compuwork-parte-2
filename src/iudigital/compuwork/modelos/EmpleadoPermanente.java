package iudigital.compuwork.modelos;

public class EmpleadoPermanente extends Empleado {
    private final double beneficios;

    public EmpleadoPermanente(int id, String nombre, double salario, double beneficios) {
        super(id, nombre, salario);
        this.beneficios = beneficios;
    }

    public double getBeneficios() {
        return beneficios;
    }

    @Override
    public String getTipoEmpleado() {
        return "Empleado Permanente";
    }

    @Override
    public String toString() {
        return getTipoEmpleado() + " - ID: " + id + ", Nombre: " + nombre + ", Salario: $" + salario + ", Beneficios: " + beneficios;
    }
}
