/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 * Entidad que representa un resultado de aprendizaje asociado a un módulo
 *
 * @author migue
 */
@Entity
@Table(name = "resultado_aprendizaje")
public class ResultadoAprendizaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    
    // Relación Many-to-One con Módulo (Clave foranea)
    @ManyToOne
    @JoinColumn(name = "id_modulo", nullable = false)  // Clave foránea
    private Modulo modulo;

    public ResultadoAprendizaje() {
    }

    public ResultadoAprendizaje(String descripcion, Modulo modulo) {
        this.descripcion = descripcion;
        this.modulo = modulo;
    }

    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
    // tostring
    @Override
    public String toString() {
        return "RESULTADO DEL APRENDIZAJE {" + modulo + ", " + descripcion +  '}';
    }
    
}
