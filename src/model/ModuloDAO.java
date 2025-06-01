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
  * Clase DAO para las operaciones CRUD con la base de datos de la entidad Modulo

 * @author migue
 */
public class ModuloDAO {
    // Metodo para guardar un Modulo en la base de datos
    public void create (Modulo modulo) {
        // Obtenemos sesion y comenzamos una transacción para guardar un modulo
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Persiste el objeto modulo en la Base de datos
            sesion.save(modulo);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener todos los modulos de la base de datos
    public List<Modulo> get () {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            // Devuelve todos los registros de la tabla modulo
            return sesion.createQuery("FROM Modulo").list();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener un Modulo por su ID
    public Modulo read (Long id) {
        // Obtenemos sesion y comenzamos una transacción para modificar un modulo
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (Modulo) sesion.get(Modulo.class, id);
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para actualizar los datos de un Modulo 
    public void update (Modulo modulo) {
        // Obtenemos sesion y comenzamos una transacción para guardar un modulo
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Actualiza los datos del objeto modulo en la Base de datos
            sesion.update(modulo);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para eliminar un Modulo de la base de datos
    public void delete (Modulo modulo) {
        // Obtenemos sesion y comenzamos una transacción para guardar un modulo
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Borramos el modulo
            sesion.delete(modulo);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    // Metodo para buscar por nombre y evitar duplicados
    public Modulo existe(String nombre) {

        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (Modulo) sesion
                    .createQuery("FROM Modulo m WHERE m.nombre = :nombre")
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } catch (HibernateException hibernateException) {
        }
        return null;
    }
}
