/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;


/**
 * Entidad que representa un Alumno de FP Dual  
 * Datos necesarios: nombre, apellidos, DNI, teléfono, correo electrónico, curso, y ciclo formativo.
 *
 * @author migue
 */

@Entity
@Table(name = "alumno", uniqueConstraints=@UniqueConstraint(columnNames="dni")) // controlamos que el dni sea unico
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    @Column(name = "dni", unique = true, nullable = false)// controlamos que el dni sea unico
    private String dni;
    private String telefono;
    private String email;
    private String curso;
    private String cicloFormativo;
    
    //Constructores
    public Alumno() {
        
    }

    public Alumno( String nombre, String apellidos, String dni, String telefono, String email, String curso, String cicloFormativo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.curso = curso;
        this.cicloFormativo = cicloFormativo;
    }
    //getters y setters

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(String cicloFormativo) {
        this.cicloFormativo = cicloFormativo;
    }
    
    //tostring
    @Override
    public String toString() {
        return " ALUMNO {" + id + ", " + nombre + ", " + apellidos + '}';
    } 
    
}
