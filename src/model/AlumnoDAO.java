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
 * Clase DAO para las operaciones CRUD con la base de datos de la entidad Alumno
 * Gestión de Alumnos: Registro, edición y eliminación de alumnos.
 *
 * @author migue
 */
public class AlumnoDAO {

    // Metodo para guardar un Alumno en la base de datos
    public void create(Alumno alumno) {
        // Obtenemos sesion y comenzamos una transacción para guardar un alumno
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Persiste el objeto alumno en la Base de datos
            sesion.save(alumno);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener todos los alumnos de la base de datos
    public List<Alumno> get() {
        List<Alumno> lista = null;

        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            // Devuelve todos los registros de la tabla alumno
            lista = sesion.createQuery("FROM Alumno").list();
            return lista;
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener un Alumno por su ID
    public Alumno read(Long id) {
        // Obtenemos sesion y comenzamos una transacción para modificar un alumno
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (Alumno) sesion.get(Alumno.class, id);
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para actualizar los datos de un Alumno 
    public void update(Alumno alumno) {
        // Obtenemos sesion y comenzamos una transacción para guardar un alumno
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Actualiza los datos del objeto alumno en la Base de datos
            sesion.update(alumno);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para eliminar un Alumno de la base de datos
    public void delete(Alumno alumno) {
        // Obtenemos sesion y comenzamos una transacción para guardar un alumno
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Borramos el alumno
            sesion.delete(alumno);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para buscar por dni y evitar duplicados
    public Alumno existe(String dni) {

        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (Alumno) sesion
                    .createQuery("FROM Alumno a WHERE a.dni = :dni")
                    .setParameter("dni", dni)
                    .uniqueResult();
        } catch (HibernateException hibernateException) {
        }
        return null;
    }
    
}
