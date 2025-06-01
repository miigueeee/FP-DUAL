/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.persistence.*;

/**
 * Entidad que representa un período de formación dual de un alumno en una
 * empresa
 *
 * @author migue
 */
@Entity
@Table(name = "periodo_formacion")
public class PeriodoFormacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Many-to-One con Alumno (Clave foránea)
    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno alumno;

    // Relación Many-to-One con Empresa (Clave foránea)
    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    @Column(name = "fechaInicio", nullable = false, columnDefinition = "DATE")
    private java.util.Date fechaInicio;

    @Column(name = "fechaFin", nullable = false, columnDefinition = "DATE")
    private java.util.Date fechaFin;
    @Column(name = "duracion_total", nullable = false)
    private int duracionTotal;

    public PeriodoFormacion() {
    }

    public PeriodoFormacion(Alumno alumno, Empresa empresa, java.util.Date fechaInicio, java.util.Date fechaFin) {
        this.alumno = alumno;
        this.empresa = empresa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.calcularDuracion();
    }

    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public java.util.Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(java.util.Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        this.calcularDuracion();
    }

    public java.util.Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(java.util.Date fechaFin) {
        this.fechaFin = fechaFin;
        this.calcularDuracion();
        
    }
    
    public int getDuracionTotal() {
        return duracionTotal;
    }

    public void setDuracionTotal(int duracionTotal) {
        this.duracionTotal = duracionTotal;
    }
    // tostring

    @Override
    public String toString() {
        // Formato de fechas yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String inicioFormateado = sdf.format(fechaInicio);
        String finFormateado = sdf.format(fechaFin);

        return "PERIDO DE FORMACION {" + alumno + ", " + empresa + ", fechaInicio=" + inicioFormateado + ", fechaFin=" + finFormateado + '}';
    }

    // Metodo para obtener duración total de la formación dual para cada alumno en horas
    public int calcularDuracion() {
        if (this.fechaInicio == null || this.fechaFin == null) {
           return this.duracionTotal = 0;
        }
        // Convertimos java.util.Date a LocalDate para manejar días de la semana
        LocalDate inicioLD = Instant.ofEpochMilli(fechaInicio.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate finLD = Instant.ofEpochMilli(fechaFin.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        // Si la fecha de fin es anterior a la de inicio, devolvemos 0
        if (finLD.isBefore(inicioLD)) {
            return this.duracionTotal = 0;
        }

        int diasLaborables  = 0;
        LocalDate d = inicioLD;
        while (!d.isAfter(finLD)) {
            DayOfWeek dia = d.getDayOfWeek();
            // Solo contamos de lunes a viernes
            if (dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) {
                diasLaborables ++;
            }
            d = d.plusDays(1);
        }
        // Devuelve las horas totales
        return this.duracionTotal = diasLaborables  * 8;
    }

}
