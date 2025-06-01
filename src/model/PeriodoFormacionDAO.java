/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import resources.HibernateUtil;

/**
 * Clase DAO para las operaciones CRUD con la base de datos de la entidad
 * PeriodoFormacion
 *
 * @author migue
 */
public class PeriodoFormacionDAO {

    // Metodo para guardar un periodo de formacion en la empresa en la base de datos
    public void create(PeriodoFormacion periodo) {
        // Obtenemos sesion y comenzamos una transacción para guardar un periodo de formacion en la empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Persiste el objeto periodo en la Base de datos
            sesion.save(periodo);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener todos los periodo de formacion en la empresa de la base de datos
    public List<PeriodoFormacion> get() {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            // Devuelve todos los registros de la tabla periodo_formacion
            return sesion.createQuery("FROM PeriodoFormacion").list();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener un periodo de formacion en la empresa por su ID
    public PeriodoFormacion read(Long id) {
        // Obtenemos sesion y comenzamos una transacción para modificar un periodo de formacion en la empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (PeriodoFormacion) sesion.get(PeriodoFormacion.class, id);
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para actualizar los datos de un periodo de formacion en la empresa 
    public void update(PeriodoFormacion periodo) {
        // Obtenemos sesion y comenzamos una transacción para guardar un periodo de formacion en la empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Actualiza los datos del objeto PeriodoFormacion en la Base de datos
            sesion.update(periodo);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para eliminar un periodo de formacion en la empresa de la base de datos
    public void delete(PeriodoFormacion periodo) {
        // Obtenemos sesion y comenzamos una transacción para guardar un periodo de formacion en la empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Borramos el periodo de formacion en la empresa
            sesion.delete(periodo);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para evitar periodos de formacion duplicados
    public PeriodoFormacion existe(Long alumnoId, Long empresaId, java.util.Date nuevaInicio, java.util.Date nuevaFin) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM PeriodoFormacion p "
                    + "WHERE p.alumno.id = :aid "
                    + "AND p.empresa.id = :eid "
                    + "AND ( (p.fechaInicio <= :nfin AND p.fechaFin >= :ninicio) )";
            PeriodoFormacion p = (PeriodoFormacion) sesion.createQuery(hql)
                    .setParameter("aid", alumnoId)
                    .setParameter("eid", empresaId)
                    .setParameter("ninicio", nuevaInicio)
                    .setParameter("nfin", nuevaFin)
                    .uniqueResult();
            return p;

        } catch (HibernateException hibernateException) {
        }
        return null;
    }
}
