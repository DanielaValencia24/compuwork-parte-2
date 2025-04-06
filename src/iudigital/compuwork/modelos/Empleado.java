package iudigital.compuwork.modelos;

public abstract class Empleado {
    protected int id;
    protected String nombre;
    protected double salario;
    protected ReporteDesempenioEmpleado reporteDesempenio;

    public Empleado(int id, String nombre, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }
    
    public ReporteDesempenioEmpleado getReporteDesempenio() {
        return this.reporteDesempenio;
    }

    public void setReporteDesempenio(ReporteDesempenioEmpleado reporte) {
        this.reporteDesempenio = reporte;
    }
    
    public abstract String getTipoEmpleado();

}
