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
 * ResultadoAprendizaje
 *
 * @author migue
 */
public class ResultadoAprendizajeDAO {

    // Metodo para guardar un resultado de aprendizaje en la base de datos
    public void create(ResultadoAprendizaje resultado) {
        // Obtenemos sesion y comenzamos una transacción para guardar un resultado de aprendizaje
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Persiste el objeto ResultadoAprendizaje en la Base de datos
            sesion.save(resultado);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener todos los resultado de aprendizaje de la base de datos
    public List<ResultadoAprendizaje> get() {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            // Devuelve todos los registros de la tabla resultado_aprendizaje
            return sesion.createQuery("FROM ResultadoAprendizaje").list();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener un resultado de aprendizaje por su ID
    public ResultadoAprendizaje read(Long id) {
        // Obtenemos sesion y comenzamos una transacción para modificar un resultado de aprendizaje
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (ResultadoAprendizaje) sesion.get(ResultadoAprendizaje.class, id);
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para actualizar los datos de un resultado de aprendizaje 
    public void update(ResultadoAprendizaje resultado) {
        // Obtenemos sesion y comenzamos una transacción para guardar un resultado de aprendizaje
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Actualiza los datos del objeto ResultadoAprendizaje en la Base de datos
            sesion.update(resultado);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para eliminar un resultado de aprendizaje de la base de datos
    public void delete(ResultadoAprendizaje resultado) {
        // Obtenemos sesion y comenzamos una transacción para guardar un resultado de aprendizaje
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Borramos el resultado de aprendizaje
            sesion.delete(resultado);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo buscar por descripción y módulo y avitar duplicados
    public ResultadoAprendizaje existe(String descripcion, Long moduloId) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (ResultadoAprendizaje) sesion
                    .createQuery("FROM ResultadoAprendizaje WHERE descripcion = :desc AND modulo.id = :mid")
                    .setParameter("desc", descripcion)
                    .setParameter("mid", moduloId)
                    .uniqueResult();
        } catch (HibernateException hibernateException) {
        }
        return null;
    }

    // Metodo que devuelve un modulo dado su ID, lo utilizaremos para asignar un resultado de aprendizaje
    public List<ResultadoAprendizaje> obtenerModulo (Long moduloId) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (List<ResultadoAprendizaje> ) sesion
                    .createQuery("FROM ResultadoAprendizaje WHERE modulo.id = :mid")
                    .setParameter("mid", moduloId)
                    .list();
        } catch (HibernateException hibernateException) {
        }
        return null;
    }

}
