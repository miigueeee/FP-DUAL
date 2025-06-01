/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author migue
 */
public class GestorFPDual extends Application {

    /**
     * @param args the command line arguments
     */
    // Inicia el archivo FXML del menu principal
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML de la ventana principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuVista.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana principal
        MenuController menuController = loader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("GESTOR FP DUAL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    // Cierra la aplicación al pulsar el botón Salir (FINALIZA LA APLICACION)
    @FXML
    private void salir(ActionEvent event) {
        Platform.exit();      
        System.exit(0);    
    }
}
