package iudigital.compuwork;

import iudigital.compuwork.modelos.*;
import iudigital.compuwork.utilidades.InicializadorBaseDatos;
import iudigital.compuwork.utilidades.MenuOpciones;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Aplicacion extends JFrame {

    private SistemaGestion sistemaGestion;
    private JTextArea displayArea;

    public static void main(String[] args) {
        new Aplicacion();
    }
    
    public Aplicacion () {
        List<Departamento> departamentos = InicializadorBaseDatos.cargarInformacion();
        sistemaGestion = new SistemaGestion((ArrayList<Departamento>) departamentos);
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);

        setTitle("Gestión de Empleados y Departamentos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel contenedor de botones con dos filas (Departamentos y Empleados)
        JPanel panelBotones = new JPanel(new GridLayout(2, 1)); // 2 filas, 1 columna

        // Botones departamentos
        JButton btnListarDepartamentos = new JButton("Listar departamentos");
        JButton btnAgregarDepartamento = new JButton("Agregar departamento");
        JButton btnActualizarDepartamento = new JButton("Actualizar Departamento");
        JButton btnEliminarDepartamento = new JButton("Eliminar Departamento");
        
        JPanel departamentosButtonPanel = new JPanel();
        departamentosButtonPanel.add(btnListarDepartamentos);
        departamentosButtonPanel.add(btnAgregarDepartamento);
        departamentosButtonPanel.add(btnActualizarDepartamento);
        departamentosButtonPanel.add(btnEliminarDepartamento);

        // Botones empleados
        JButton btnAgregarEmpleadoPermanente = new JButton("Agregar Empleado Permanente");
        JButton btnAgregarEmpleadoTemporal = new JButton("Agregar Empleado Temporal");
        JButton btnListarEmpleadosPorDepartamento = new JButton("Listar Empleados por Departamento");
        JButton btnListarTodosLosEmpleados = new JButton("Listar Todos los Empleados");
        JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
        JButton btnTransferirEmpleado = new JButton("Transferir Empleado");

        JPanel empleadosButtonPanel = new JPanel();
        empleadosButtonPanel.add(btnAgregarEmpleadoPermanente);
        empleadosButtonPanel.add(btnAgregarEmpleadoTemporal);
        empleadosButtonPanel.add(btnListarEmpleadosPorDepartamento);
        empleadosButtonPanel.add(btnListarTodosLosEmpleados);
        empleadosButtonPanel.add(btnEliminarEmpleado);
        empleadosButtonPanel.add(btnTransferirEmpleado);

        // Agregar ambos paneles de botones al contenedor
        panelBotones.add(departamentosButtonPanel);
        panelBotones.add(empleadosButtonPanel);

        // Añadir paneles al frame
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        add(panel);
        setVisible(true);

        // Listeners
        btnListarDepartamentos.addActionListener(e -> formatearDepartamentos());
        btnAgregarDepartamento.addActionListener(e -> crearDepartamento());
        btnActualizarDepartamento.addActionListener(e -> actualizarDepartamento());
        btnEliminarDepartamento.addActionListener(e -> eliminarDepartamento());

        btnAgregarEmpleadoPermanente.addActionListener(e -> agregarEmpleado(true));
        btnAgregarEmpleadoTemporal.addActionListener(e -> agregarEmpleado(false));
        btnListarEmpleadosPorDepartamento.addActionListener(e -> listarEmpleadosPorDepartamento());
        btnEliminarEmpleado.addActionListener(e -> eliminarEmpleado());
        btnTransferirEmpleado.addActionListener(e -> transferirEmpleado());
        btnListarTodosLosEmpleados.addActionListener(e -> listarTodosLosEmpleados());

        formatearDepartamentos();
       // int opcionElegida;
       // do {
       //     opcionElegida = MenuOpciones.mostrarMenuPrincipal();
//
  //          switch (opcionElegida) {
  //              case 2 -> listarDepartamentos();
  //              case 3 -> actualizarDepartamento();
  //              case 4 -> eliminarDepartamento();
  //              case 5 -> agregarEmpleado(true);
  //              case 6 -> agregarEmpleado(false);
  //              case 7 -> listarEmpleadosPorDepartamento();
  //              case 8 -> eliminarEmpleado();
  //              case 9 -> transferirEmpleado();
  //              case 10 -> listarTodosLosEmpleados();
  //              case 0 -> System.out.println("Saliendo del sistema...");
  //              default -> System.out.println("Opción no válida.");
  //          }

 //       } while (opcionElegida != 0);
    }

    public void formatearDepartamentos() {
        StringBuilder sb = new StringBuilder();

        for (Departamento d : sistemaGestion.getDepartamentos()) {
            sb.append("-------------------------------------\n");
            sb.append("Departamento ID: ").append(d.getId()).append("\n");
            sb.append("Nombre: ").append(d.getNombre()).append("\n");
            sb.append("Cantidad de empleados: ").append(d.getEmpleados().size()).append("\n");
        }

        displayArea.setText(sb.toString());
    }
    
    public void formatearEmpleados(List<Empleado> empleados) {
        StringBuilder sb = new StringBuilder();
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        for (Empleado e : empleados) {
            if (e instanceof EmpleadoTemporal) {
                EmpleadoTemporal et = (EmpleadoTemporal) e;
                sb.append("--- Empleado Temporal ---\n");
                sb.append("ID: ").append(et.getId()).append("\n");
                sb.append("Nombre: ").append(et.getNombre()).append("\n");
                sb.append("Salario: ").append(formatoMoneda.format(et.getSalario())).append("\n");
                sb.append("Duración contrato: ").append(et.getDuracionContrato()).append(" meses\n");
            } else if (e instanceof EmpleadoPermanente) {
                EmpleadoPermanente ep = (EmpleadoPermanente) e;
                sb.append("--- Empleado Permanente ---\n");
                sb.append("ID: ").append(ep.getId()).append("\n");
                sb.append("Nombre: ").append(ep.getNombre()).append("\n");
                sb.append("Salario: ").append(formatoMoneda.format(ep.getSalario())).append("\n");
                sb.append("Beneficios: ").append(formatoMoneda.format(ep.getBeneficios())).append("\n");
            }
            sb.append("\n");
        }

        displayArea.setText(sb.toString());

    }

    
    private void agregarEmpleado(boolean permanente) {

        int idDepartamento = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        Departamento departamento = sistemaGestion.buscarPorId(idDepartamento);

        if(departamento == null) {
            JOptionPane.showMessageDialog(this, "Departamento no encontrado.");
            return;
        }

        Empleado nuevoEmpleado = MenuOpciones.mostrarMenuConstruccionEmpleado(permanente);

        boolean existeEmpleado = sistemaGestion.existeEmpleado(nuevoEmpleado.getId());

        if (existeEmpleado) {
            JOptionPane.showMessageDialog(this,"⚠️ El empleado con el ID " + nuevoEmpleado.getId() + " ya existe en el sistema");
            return;
        }
        
        departamento.agregarEmpleado(nuevoEmpleado);
        formatearDepartamentos();
        JOptionPane.showMessageDialog(this,"✅ Empleado agregado con éxito.");
    }

    private void crearDepartamento() {
        Departamento nuevoDepartamento = MenuOpciones.mostrarMenuConstruccionDepartamento();

        sistemaGestion.agregarDepartamento(nuevoDepartamento);
        formatearDepartamentos();
    }

    private void listarDepartamentos() {
        sistemaGestion.listarDepartamentos();
    }

    private void actualizarDepartamento() {
        int idDepartamentoActualizar = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        Departamento departamentoActualizar = sistemaGestion.buscarPorId(idDepartamentoActualizar);

        if (departamentoActualizar == null) {
            JOptionPane.showMessageDialog(this,"⚠️ No se encontró un departamento con el ID " + idDepartamentoActualizar);
            return;
        }

        String nuevoNombre = MenuOpciones.mostrarMenuNombre();

        departamentoActualizar.setNombre(nuevoNombre);
        formatearDepartamentos();
        JOptionPane.showMessageDialog(this,"✔ Departamento actualizado con éxito");
        
    }
    private void eliminarDepartamento() {
        int idDepartamentoEliminar = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        boolean existe = sistemaGestion.existePorId(idDepartamentoEliminar);

//        if (existe == false) {}
        if (!existe) {
            JOptionPane.showMessageDialog(this, "⚠️ No se encontró un departamento con el ID " + idDepartamentoEliminar);
            return;
        }
        // En este punto, estamos seguros que el departamento a eliminar si existe
        sistemaGestion.eliminarDepartamento(idDepartamentoEliminar);
        formatearDepartamentos();
        JOptionPane.showMessageDialog(this,"✔ Departamento eliminado con éxito");

    }

    private void listarEmpleadosPorDepartamento() {
        int idDepartamento = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento");
        Departamento departamento = sistemaGestion.buscarPorId(idDepartamento);

        if (departamento == null) {
            JOptionPane.showMessageDialog(this,"⚠️ No se encontró un departamento con el ID " + idDepartamento);
            return;
        }
        formatearEmpleados(departamento.getEmpleados());
        
    }

    private void eliminarEmpleado() {
        int idEmpleadoEliminar = MenuOpciones.mostrarMenuIdentificadorNumerico("del empleado a eliminar");

        Departamento departamento = sistemaGestion.buscarEmpleadoEnDepartamento(idEmpleadoEliminar);

        if (departamento == null) {
            JOptionPane.showMessageDialog(this,"⚠️ No se encontró un departamento que contenga un empleado con el ID " + idEmpleadoEliminar);
            return;
        }
        Empleado empleadoEliminado = departamento.eliminarEmpleado(idEmpleadoEliminar);
        formatearDepartamentos();
        JOptionPane.showMessageDialog(this,"✅ Empleado " + empleadoEliminado.getNombre() + " eliminado del departamento " + departamento.getNombre());
    }

    private void transferirEmpleado() {
        int idEmpleadoTranferir = MenuOpciones.mostrarMenuIdentificadorNumerico("del empleado a transferir");

        Departamento departamentoOrigen = sistemaGestion.buscarEmpleadoEnDepartamento(idEmpleadoTranferir);

        if (departamentoOrigen == null) {
            JOptionPane.showMessageDialog(this,"⚠️ No se encontró un departamento que contenga un empleado con el ID " + idEmpleadoTranferir);
            return;
        }

        int idDepartamentoDestino = MenuOpciones.mostrarMenuIdentificadorNumerico("del departamento de destino: ");
        Departamento departamentoDestino = sistemaGestion.buscarPorId(idDepartamentoDestino);

        if (departamentoDestino == null) {
            JOptionPane.showMessageDialog(this,"⚠️ No se encontró un departamento de destino con el id " + idDepartamentoDestino);
            return;
        }

        if (departamentoOrigen.getId() == departamentoDestino.getId()) {
            JOptionPane.showMessageDialog(this,"⚠️ Los departamentos deben ser diferentes ");
            return;
        }
        

        Empleado empleadoEliminado = departamentoOrigen.eliminarEmpleado(idEmpleadoTranferir);
        departamentoDestino.agregarEmpleado(empleadoEliminado);
    
        formatearDepartamentos();

    }

    private void listarTodosLosEmpleados() {
        List<Empleado> todosEmpleados = new ArrayList<>();
        for (Departamento depActual: sistemaGestion.getDepartamentos()) {
            todosEmpleados.addAll(depActual.getEmpleados());
        }
        formatearEmpleados(todosEmpleados);
    }
}
