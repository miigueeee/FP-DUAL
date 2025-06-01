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
 * Clase DAO para las operaciones CRUD con la base de datos de la entidad Empresa
 * Gestión de Empresas: Registro, edición y eliminación de empresas colaboradoras
 * @author migue
 */
public class EmpresaDAO {
    // Metodo para guardar una Empresa en la base de datos
    public void create (Empresa empresa) {
        // Obtenemos sesion y comenzamos una transacción para guardar una empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Persiste el objeto empresa en la Base de datos
            sesion.save(empresa);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener todos las empresas de la base de datos
    public List<Empresa> get () {
        List<Empresa> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            // Devuelve todos los registros de la tabla empresa
            lista = sesion.createQuery("FROM Empresa").list();
            return lista;
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener una Empresa por su ID
    public Empresa read (Long id) {
        // Obtenemos sesion y comenzamos una transacción para modificar una empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (Empresa) sesion.get(Empresa.class, id);
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para actualizar los datos de un Empresa 
    public void update (Empresa empresa) {
        // Obtenemos sesion y comenzamos una transacción para guardar una empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Actualiza los datos del objeto empresa en la Base de datos
            sesion.update(empresa);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para eliminar una Empresa de la base de datos
    public void delete (Empresa empresa) {
        // Obtenemos sesion y comenzamos una transacción para guardar una empresa
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            // Borramos la empresa
            sesion.delete(empresa);
            // Confirmamos la transacción
            sesion.getTransaction().commit();
            // Cerramos la sesion
            sesion.close();
        } catch (HibernateException ex) {
            System.err.println("No se pudo crear la sesion " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    // Metodo para buscar por cif y evitar duplicados
    public Empresa existe(String cif) {

        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            return (Empresa) sesion
                    .createQuery("FROM Empresa e WHERE e.cif = :cif")
                    .setParameter("cif", cif)
                    .uniqueResult();
        } catch (HibernateException hibernateException) {
        }
        return null;
    }
}
