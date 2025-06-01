/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 * Entidad que relaciona un período de formación con módulos y resultados de
 * aprendizaje (tabla intermedia)
 *
 * @author migue
 */
@Entity
@Table(name = "gestion_formacion")
public class GestionFormacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Many-to-One con PeriodoFormacion (Clave foránea)
    @ManyToOne
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoFormacion periodo;
    
    // Relación Many-to-One con ResultadoAprendizaje (Clave foránea)
    @ManyToOne
    @JoinColumn(name = "id_resultado", nullable = false)
    private ResultadoAprendizaje resultado;

    public GestionFormacion() {
    }
    
    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeriodoFormacion getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoFormacion periodo) {
        this.periodo = periodo;
    }

    public ResultadoAprendizaje getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoAprendizaje resultado) {
        this.resultado = resultado;
    }
    // tostring
    @Override
    public String toString() {
        return "GestionFormacion{" + "id=" + id + ", periodo=" + periodo + ", resultado=" + resultado + '}';
    }
    
}
