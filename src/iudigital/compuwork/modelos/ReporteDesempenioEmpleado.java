/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iudigital.compuwork.modelos;

import java.time.LocalDate;

/**
 *
 * @author j3c-dev
 */
public class ReporteDesempenioEmpleado {
    private int id;
    private LocalDate fechaGeneracion;
    private float calificacion;

    public ReporteDesempenioEmpleado(int id, LocalDate fechaGeneracion, float calificacion) {
        this.id = id;
        this.fechaGeneracion = fechaGeneracion;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }
    
}
