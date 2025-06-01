/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Set;
import javax.persistence.*;

/**
 * Enitdad que representa un Modulo del ciclo de FP Dual
 *
 * @author migue
 */
@Entity
@Table(name = "modulo",uniqueConstraints=@UniqueConstraint(columnNames="nombre")) // controlamos que el nombre sea unico
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", unique = true, nullable = false)// controlamos que el nombre sea unico
    private String nombre;
    private String modalidad;
        
    // Constructores
    public Modulo() {
        
    }

    public Modulo(String nombre, String modalidad) {
        this.nombre = nombre;
        this.modalidad = modalidad;
    }
    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    //tostring
    @Override
    public String toString() {
        return "MODULO {" + id + ", " + nombre + ", Dual: " + modalidad + '}';
    }

}
