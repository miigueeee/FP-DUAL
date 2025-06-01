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
 *
 * @author migue
 */
public class GestionFormacionDAO {

    // Metodo para guardar  relaci�n entre un per�odo y un resultado de aprendizaje en la base de datos
    public void create(GestionFormacion gestion) {
        // Obtenemos sesion y comenzamos una transacci�n para guardar una gesti�n
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Persiste el objeto GestionFormacion en la Base de datos
            sesion.save(gestion);
            // Confirmamos la transacci�n
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener todas las relaciones gestionadas de la base de datos
    public List<GestionFormacion> get() {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            // Devuelve todos los registros de la tabla gestion_formacion
            return sesion.createQuery("FROM GestionFormacion").list();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener una gesti�n por su ID
    public GestionFormacion read(Long id) {
        // Obtenemos sesion y comenzamos una transacci�n para modificar una gesti�n
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (GestionFormacion) sesion.get(GestionFormacion.class, id);
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para actualizar los datos de una gesti�n 
    public void update(GestionFormacion gestion) {
        // Obtenemos sesion y comenzamos una transacci�n para guardar una gesti�n
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Actualiza los datos del objeto gestion en la Base de datos
            sesion.update(gestion);
            // Confirmamos la transacci�n
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para eliminar una gesti�n de la base de datos
    public void delete(GestionFormacion gestion) {
        // Obtenemos sesion y comenzamos una transacci�n para guardar una gesti�n
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Borramos la gesti�n
            sesion.delete(gestion);
            // Confirmamos la transacci�n
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para evitar formaciones duplicadas
    public GestionFormacion existe(Long periodoId, Long resultadoId) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (GestionFormacion) sesion.createQuery(
                    "FROM GestionFormacion WHERE periodo.id = :pid AND resultado.id = :rid")
                    .setParameter("pid", periodoId)
                    .setParameter("rid", resultadoId)
                    .uniqueResult();
        } catch (HibernateException hibernateException) {
        }
        return null;
    }
}
