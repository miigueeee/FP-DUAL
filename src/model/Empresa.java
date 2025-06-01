/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 * Entidad que representa una Empresa colaboradora de FP Dual Datos necesarios:
 * nombre de la empresa, CIF, sector, persona de contacto, teléfono, correo
 * electrónico, y dirección.
 *
 * @author migue
 */
@Entity
@Table(name = "empresa", uniqueConstraints = @UniqueConstraint(columnNames = "cif")) // conttrolamos que el cif sea unico
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Column(name = "cif", unique = true, nullable = false)// controlamos que el cif sea unico
    private String cif;
    private String sector;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;

    //Constructores
    public Empresa() {

    }

    public Empresa(String nombre, String cif, String sector, String contacto, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.cif = cif;
        this.sector = sector;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // tostring
    @Override
    public String toString() {
        return " EMPRESA {" + id + ", " + nombre + '}';
    }

}
