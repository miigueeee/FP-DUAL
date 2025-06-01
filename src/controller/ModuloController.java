/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Modulo;
import model.Modulo;
import model.ModuloDAO;

/**
Controlador de Modulos , permite agregar, modificar y eliminar modulos y
 * visualizarlos en una tabla
 *
 * @author migue
 */
public class ModuloController implements Initializable {

    @FXML
    private TableView<Modulo> tableModulos;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colModalidad;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> txtModalidad;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVolver;
    // Arraylist de JavaFX
    private ObservableList<Modulo> listaModulos = FXCollections.observableArrayList();

    //Utilizamos el método initialize para cargar los valores -> ObservableList
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Asociamos los datos del Modulo a cada columna de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        // Cargamos las opciones para Modalidad dual en el ComboBox
        txtModalidad.setItems(FXCollections.observableArrayList("Si", "No"));
        // Cargamos los modulos de la base de datos y los mostramos en la tabla
        listaModulos.addAll(new ModuloDAO().get());
        tableModulos.setItems(listaModulos);

    }
    // Metodo para agregar un Moculo al pulsar el botón Agregar

    @FXML
    private void guardarModulo(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Creamos un Modulo con los datos introducidos en los campos correspondientes
        Modulo m = new Modulo();
        m.setNombre(txtNombre.getText());
        m.setModalidad(txtModalidad.getValue());
        // Comprobamos si el Modulo ya existe o no antes de agregarlo.
        if (new ModuloDAO().existe(m.getNombre()) == null) {
            // Guardamos el modulo en la base de datos
            new ModuloDAO().create(m);
            // Refrescamos la tabla
            listaModulos.clear();
            listaModulos.addAll(new ModuloDAO().get());
            // Si el Modulo se ha añadido se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MODULO");
            alert.setHeaderText(null);
            alert.setContentText("EL " + m.toString() + " SE HA AÑADIDO CORRECTAMENTE");
            alert.showAndWait();
            // Limpiamos los campos
            limpiarCampos();
        } else {
            // Si el Modulo no se ha añadido se muestra mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" ERROR: MODULO DUPLICADO ");
            alert.setHeaderText(null);
            alert.setContentText(" YA EXISTE EL MODULO " + m.getNombre());
            alert.showAndWait();
        }
    }
// Metodo para seleccionar un Modulo de la tabla y cargar sus datos en los campos

    @FXML
    public void seleccionar(MouseEvent event) {
        Modulo m = this.tableModulos.getSelectionModel().getSelectedItem();
        txtNombre.setText(m.getNombre());
        txtModalidad.setValue(m.getModalidad());
    }

    // Metodo para modificar un Modulo al pulsar el botón Modificar
    @FXML
    private void modificarModulo(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Modulo de la tabla
        Modulo m = this.tableModulos.getSelectionModel().getSelectedItem();
        m.setNombre(txtNombre.getText());
        m.setModalidad(txtModalidad.getValue());
        // Actualizamos el modulo en la base de datos
        new ModuloDAO().update(m);
        // Actualizamos la tabla con los datos del Modulo modificados
        tableModulos.refresh();
        // Si el Modulo se ha modificado se muestra mensaje de información
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MODULO");
        alert.setHeaderText(null);
        alert.setContentText("EL " + m.toString() + " SE HA MODIFICADO CORRECTAMENTE");
        alert.showAndWait();

        // Limpiamos los campos
        limpiarCampos();
    }
    // Metodo para eliminar un Modulo al pulsar el botón Eliminar

    @FXML
    private void eliminarModulo(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Modulo de la tabla
        Modulo m = tableModulos.getSelectionModel().getSelectedItem();
        if (m != null) {
            // Eliminamos el modulo y actualizamos la tabla
            new ModuloDAO().delete(m);
            listaModulos.clear();
            listaModulos.addAll(new ModuloDAO().get());
            // Si el Modulo se ha eliminado se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MODULO");
            alert.setHeaderText(null);
            alert.setContentText("EL " + m.toString() + " SE HA ELIMINADO CORRECTAMENTE");
            alert.showAndWait();
            // Limpiamos los campos
            limpiarCampos();
        }
    }

    // Cierra la ventana y regresa al menu principal al pulsar el boton VOLVER AL MENU    
    @FXML
    private void volverAlMenu(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    // Metodo para comprobar que los campos no estén vacios 

    public boolean validarCampos() {
        if (txtNombre.getText().isEmpty()
                || txtModalidad.getValue().isEmpty()) {
            // Si no se introducen todos los campos se muestra mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("SE DEBEN COMPLETAR TODOS LOS CAMPOS");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    // Metodo para limpiar los TextField
    private void limpiarCampos() {
        txtNombre.clear();
        txtModalidad.getSelectionModel().clearSelection();
    }
}
