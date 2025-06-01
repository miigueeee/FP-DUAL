/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import model.Alumno;

/**
 *
 * @author migue
 */
public class MenuController {

    @FXML
    private Button btnAlumnos;
    @FXML
    private Button btnEmpresas;
    @FXML
    private Button btnPeriodos;
    @FXML
    private Button btnModulos;
    @FXML
    private Button btnResultados;
    @FXML
    private Button btnSalir;

    // Arraylist de JavaFX
    private ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();
    @FXML
    private Button btnGestion;

    // Inicia el archivo FXML de la ventana de Alumnos al pulsar el boton ALUMNOS
    @FXML
    private void abrirVentanaAlumnos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlumnoVista.fxml"));
        Parent root = loader.load();
        // Obtener el controlador de la ventana 
        AlumnoController alumnoController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("GESTION DE ALUMNOS");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Inicia el archivo FXML de la ventana de Empresas al pulsar el boton EMPRESAS
    @FXML
    private void abrirVentanaEmpresas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmpresaVista.fxml"));
        Parent root = loader.load();
        // Obtener el controlador de la ventana 
        EmpresaController empresaController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("GESTION DE EMPRESAS");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Inicia el archivo FXML de la ventana de periodos de formación al pulsar el boton ASIGNACION DE ALUMNOS A EMPRESAS
    @FXML
    private void abrirVentanaPeriodos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PeriodoFormacionVista.fxml"));
        Parent root = loader.load();
        // Obtener el controlador de la ventana
        PeriodoFormacionController periodoController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("ASIGNACION DE ALUMNOS A EMPRESAS");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Inicia el archivo FXML de la ventana de Modulos al pulsar el boton MODULOS
    @FXML
    private void abrirVentanaModulos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModuloVista.fxml"));
        Parent root = loader.load();
        // Obtener el controlador de la ventana 
        ModuloController moduloController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("CONTROL DE MODULOS DUALIZADOS");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Inicia el archivo FXML de la ventana de Resultados de Aprendizaje al pulsar el boton RESULTADOS DE APRENDIZAJE
    @FXML
    private void abrirVentanaResultados(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ResultadoAprendizajeVista.fxml"));
        Parent root = loader.load();
        // Obtener el controlador de la ventana 
        ResultadoAprendizajeController resultadoController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("CONTROL DE RESULTADOS DE APRENDIZAJE");
        stage.setScene(new Scene(root));
        stage.show();
    }
    // Inicia el archivo FXML de la ventana de Gestion de la formacion al pulsar el boton GESTION MODULOS Y RESULTADOS
    @FXML
    private void abrirVentanaGestion (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GestionFormacionVista.fxml"));
        Parent root = loader.load();
        // Obtener el controlador de la ventana d
        GestionFormacionController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("GESTION DE MODULOS Y RESULTADOS");
        stage.setScene(new Scene(root));
        stage.show();
    }
    // Cierra la aplicación al pulsar el boton SALIR
    @FXML
    private void Salir(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
