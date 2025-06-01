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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Modulo;
import model.ModuloDAO;
import model.ResultadoAprendizaje;
import model.ResultadoAprendizajeDAO;

/**
 * Controlador de Resultados de aprendizaje, permete asignar un resultado de aprendizaje a un Modulo Dual determinado
 *
 * @author migue
 */
public class ResultadoAprendizajeController implements Initializable {

    @FXML
    private TableView<ResultadoAprendizaje> tableResultados;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colModulo;
    @FXML
    private TableColumn colDescripcion;
    @FXML
    private ComboBox<Modulo> comboModulo;
    @FXML
    private TextArea txtDescripcion;
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
    private ObservableList<ResultadoAprendizaje> listaResultados = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
        // Cargamos modulos en combobox
        comboModulo.setItems(listaModulos);
        // Cargamos los resultados de la base de datos y los mostramos en la tabla
        listaResultados.addAll(new ResultadoAprendizajeDAO().get());
        tableResultados.setItems(listaResultados);
        listaModulos.addAll(new ModuloDAO().get());

    }

    @FXML
    private void guardarResultado(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Comprobamos si el Resultado de aprendizaje ya existe o no antes de agregarlo.
        String desc = txtDescripcion.getText().trim();
        Modulo m = comboModulo.getValue();
        // Controlamos que el modulo sea dual, sino muestra mensaje de error
        if (m.getModalidad().equalsIgnoreCase("No")) {
            // Si el modulo no es dual se muestra mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" ERROR ");
            alert.setHeaderText(null);
            alert.setContentText(" EL MODULO NO ES DUAL ");
            alert.showAndWait();
            limpiarCampos();
            
        } else {
            ResultadoAprendizaje r = new ResultadoAprendizaje(desc, m);
            // Comprobamos que no exista el resultado de aprenzaje sino muestra error
            if (new ResultadoAprendizajeDAO().existe(desc, m.getId()) == null) {
                // Guardamos el Resultado de aprendizaje en la base de datos
                new ResultadoAprendizajeDAO().create(r);
                // Refrescamos la tabla
                listaResultados.clear();
                listaResultados.setAll(new ResultadoAprendizajeDAO().get());
                // Si el Resultado de aprendizaje se ha añadido se muestra mensaje de información
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RESULTADO DE APRENDIZAJE");
                alert.setHeaderText(null);
                alert.setContentText("EL " + r.toString() + " SE HA AÑADIDO CORRECTAMENTE");
                alert.showAndWait();
                // Limpiamos los campos
                limpiarCampos();
            } else {
                // Si el Resultado no se ha añadido se muestra mensaje de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR: RESULTADO DE APRENDIZAJE DUPLICADO ");
                alert.setHeaderText(null);
                alert.setContentText(" YA EXISTE EL " + r.toString());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void modificarResultado(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Resultado de aprendizaje de la tabla
        ResultadoAprendizaje r = tableResultados.getSelectionModel().getSelectedItem();
        r.setDescripcion(txtDescripcion.getText().trim());
        r.setModulo(comboModulo.getValue());
        // Actualizamos el Resultado de aprendizaje en la base de datos
        new ResultadoAprendizajeDAO().update(r);
        // Actualizamos la tabla con los datos del Resultado de aprendizaje modificados
        tableResultados.refresh();
        // Si el Resultado de aprendizaje se ha modificado se muestra mensaje de información
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RESULTADO DE APRENDIZAJE");
        alert.setHeaderText(null);
        alert.setContentText("EL " + r.toString() + " SE HA MODIFICADO CORRECTAMENTE");
        alert.showAndWait();
        // Limpiamos los campos
        limpiarCampos();
    }

    @FXML
    private void eliminarResultado(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Resultado de aprendizaje de la tabla
        ResultadoAprendizaje r = tableResultados.getSelectionModel().getSelectedItem();
        r.setDescripcion(txtDescripcion.getText().trim());
        r.setModulo(comboModulo.getValue());
        if (r != null) {
            // Eliminamos el Resultado de aprendizaje y actualizamos la tabla
            new ResultadoAprendizajeDAO().delete(r);
            listaResultados.clear();
            listaResultados.addAll(new ResultadoAprendizajeDAO().get());
            // Si el Resultado de aprendizaje se ha eliminado se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RESULTADO DE APRENDIZAJE");
            alert.setHeaderText(null);
            alert.setContentText("EL " + r.toString() + " SE HA ELIMINADO CORRECTAMENTE");
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

    // Metodo para seleccionar un Periodo de la tabla y cargar sus datos en los campos
    @FXML
    public void seleccionar(MouseEvent event) {
        ResultadoAprendizaje r = tableResultados.getSelectionModel().getSelectedItem();
        txtDescripcion.setText(r.getDescripcion());
        comboModulo.setValue(r.getModulo());
    }

    // Metodo para comprobar que los campos no estén vacios
    public boolean validarCampos() {
        if (txtDescripcion.getText().isEmpty()
                || comboModulo.getValue() == null) {
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

    // Metodo para limpiar los campos
    private void limpiarCampos() {
        txtDescripcion.clear();
        comboModulo.setValue(null);
    }

}
