/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.GestionFormacion;
import model.GestionFormacionDAO;
import model.Modulo;
import model.ModuloDAO;
import model.PeriodoFormacion;
import model.PeriodoFormacionDAO;
import model.ResultadoAprendizaje;
import model.ResultadoAprendizajeDAO;

/**
 * Controlador para Gestion de la formacion, permite asignar periodos de formacion con Modulos y resultados de aprendizaje
 *
 * @author migue
 */
public class GestionFormacionController implements Initializable {

    @FXML
    private TableView<GestionFormacion> tableGestion;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colPeriodo;
    @FXML
    private TableColumn colResultado;
    @FXML
    private ComboBox<PeriodoFormacion> comboPeriodo;
    @FXML
    private ComboBox<Modulo> comboModulo;
    @FXML
    private ComboBox<ResultadoAprendizaje> comboResultado;
    @FXML
    private Button btnAsignar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVolver;
    // Arraylist de JavaFX

    private ObservableList<GestionFormacion> listaGestion = FXCollections.observableArrayList();

    //Utilizamos el método initialize para cargar los valores 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        colResultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));
        // Combobox
        comboPeriodo.getItems().addAll(new PeriodoFormacionDAO().get());
        comboModulo.getItems().addAll(new ModuloDAO().get());
        // Establecemos un listener para actualizar el combobox de resultados de aprendizaje dependiendo del modulo que se seleccione
        comboModulo.getSelectionModel().selectedItemProperty().addListener((obs, oldM, newM) -> {
            if (newM != null) {
                List<ResultadoAprendizaje> resultadosModulo = new ResultadoAprendizajeDAO().obtenerModulo(newM.getId());
                comboResultado.setItems(FXCollections.observableArrayList(resultadosModulo));
            } else {
                comboResultado.getItems().clear();
            }
        });
        // Tabla
        listaGestion.addAll(new GestionFormacionDAO().get());
        tableGestion.setItems(listaGestion);
    }

    // Metodo para agregar una Gestion de formacion al pulsar el botón Agregar
    @FXML
    private void Asignar(ActionEvent event) {
        // Controlamos que no haya campos vacios y las fechas sean correctas
        if (!validarCampos()) {
            return;
        }
        GestionFormacion g = new GestionFormacion();
        g.setPeriodo(comboPeriodo.getValue());
        g.setResultado(comboResultado.getValue());
        // Comprobamos que la formacion no exista
        if (new GestionFormacionDAO().existe(g.getPeriodo().getId(), g.getResultado().getId()) == null) {
            new GestionFormacionDAO().create(g);
            // Refrescamos la tabla
            listaGestion.clear();
            listaGestion.addAll(new GestionFormacionDAO().get());
            // Si se ha añadido se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" GESTION DE FORMACION ");
            alert.setHeaderText(null);
            alert.setContentText("EL " + g.toString() + " SE HA AÑADIDO CORRECTAMENTE");
            alert.showAndWait();
            // Limpiamos los campos            
            limpiarCampos();
        } else {
            // Si el Modulo no se ha añadido se muestra mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" ERROR: FORMACION DUPLICADA ");
            alert.setHeaderText(null);
            alert.setContentText(" YA EXISTE LA FORMACION " + g.toString());
            alert.showAndWait();
        }
    }

    // Metodo para modificar una Gestion de la formacion al pulsar el botón Modificar
    @FXML
    private void Modificar(ActionEvent event) {
        // Controlamos que no haya campos vacios 
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos la Gestion de la tabla
        GestionFormacion g = tableGestion.getSelectionModel().getSelectedItem();
        g.setPeriodo(comboPeriodo.getValue());
        g.setResultado(comboResultado.getValue());
        new GestionFormacionDAO().update(g);
        // Actualizamos la tabla
        tableGestion.refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GESTION  DE FORMACION");
        alert.setHeaderText(null);
        alert.setContentText("EL " + g.toString() + " SE HA MODIFICADO CORRECTAMENTE");
        alert.showAndWait();
        // Limpiamos los campos
        limpiarCampos();
    }
    // Metodo para eliminar una Gestion de la formacion al pulsar el botón Eliminar

    @FXML
    private void Eliminar(ActionEvent event) {
        // Controlamos que no haya campos vacios 
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos la Gestion de la tabla
        GestionFormacion g = tableGestion.getSelectionModel().getSelectedItem();
        // Eliminamos y actualizamos la tabla
        new GestionFormacionDAO().delete(g);
        listaGestion.clear();
        listaGestion.addAll(new GestionFormacionDAO().get());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(" GESTION DE FORMACION");
        alert.setHeaderText(null);
        alert.setContentText("EL " + g.toString() + " SE HA ELIMINADO CORRECTAMENTE");
        alert.showAndWait();
        // Limpiamos los campos

        limpiarCampos();
    }
    // Metodo para comprobar que los campos no estén vacios 

    public boolean validarCampos() {
        if (comboPeriodo.getValue() == null
                || comboResultado.getValue() == null
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

    // Cierra la ventana y regresa al menu principal al pulsar el boton VOLVER AL MENU    
    @FXML
    private void volverAlMenu(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    // Metodo para seleccionar una Gestion de la tabla y cargar sus datos en los campos

    @FXML
    private void seleccionar(MouseEvent event) {
        GestionFormacion g = tableGestion.getSelectionModel().getSelectedItem();
        comboPeriodo.setValue(g.getPeriodo());
        comboModulo.setValue(g.getResultado().getModulo());
        comboResultado.setValue(g.getResultado());
    }
    // Metodo para limpiar los combobox

    private void limpiarCampos() {
        comboPeriodo.setValue(null);
        comboModulo.setValue(null);
        comboResultado.setValue(null);
    }
}
