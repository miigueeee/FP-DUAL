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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Alumno;
import model.AlumnoDAO;

/**
 * Controlador de Alumnos, permite agregar, modificar y eliminar alumnos y
 * visualizarlos en una tabla
 *
 * @author migue
 */
public class AlumnoController implements Initializable {

    @FXML
    private TableView<Alumno> tableAlumnos;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidos;
    @FXML
    private TableColumn colDNI;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colCurso;
    @FXML
    private TableColumn colCiclo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCurso;
    @FXML
    private TextField txtCiclo;
    @FXML
    private Label txtCaja;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVolver;

    // Arraylist de JavaFX
    private ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();

    //Utilizamos el método initialize para cargar los valores -> ObservableList
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Asociamos los datos del Alumno a cada columna de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<>("cicloFormativo"));

        // Cargamos los alumnos de la base de datos y los mostramos en la tabla
        listaAlumnos.addAll(new AlumnoDAO().get());
        tableAlumnos.setItems(listaAlumnos);
    }

    // Metodo para agregar un Alumno al pulsar el botón Agregar
    @FXML
    private void guardarAlumno(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Creamos un alumno con los datos introducidos en los campos correspondientes
        Alumno a = new Alumno();
        a.setNombre(txtNombre.getText());
        a.setApellidos(txtApellidos.getText());
        a.setDni(txtDNI.getText());
        a.setTelefono(txtTelefono.getText());
        a.setEmail(txtEmail.getText());
        a.setCurso(txtCurso.getText());
        a.setCicloFormativo(txtCiclo.getText());
        // Comprobamos si el Alumno ya existe o no antes de agregarlo.
        if (new AlumnoDAO().existe(a.getDni()) == null) {
            // Guardamos el alumno en la base de datos
            new AlumnoDAO().create(a);
            // Refrescamos la tabla
            listaAlumnos.clear();
            listaAlumnos.addAll(new AlumnoDAO().get());
            // Si el Alumno se ha añadido se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALUMNO");
            alert.setHeaderText(null);
            alert.setContentText("EL " + a.toString() + " SE HA AÑADIDO CORRECTAMENTE");
            alert.showAndWait();
            // Limpiamos los campos
            limpiarCampos();
        }else {
                // Si el Alumno no se ha añadido se muestra mensaje de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ERROR: DNI DUPLICADO ");
                alert.setHeaderText(null);
                alert.setContentText(" YA EXISTE UN ALUMNO CON DNI " +a.getDni());
                alert.showAndWait();
            }
    }
// Metodo para seleccionar un Alumno de la tabla y cargar sus datos en los campos

    @FXML
    public void seleccionar(MouseEvent event) {
        Alumno a = this.tableAlumnos.getSelectionModel().getSelectedItem();
        txtNombre.setText(a.getNombre());
        txtApellidos.setText(a.getApellidos());
        txtDNI.setText(a.getDni());
        txtTelefono.setText(a.getTelefono());
        txtEmail.setText(a.getEmail());
        txtCurso.setText(a.getCurso());
        txtCiclo.setText(a.getCicloFormativo());
    }

    // Metodo para modificar un Alumno al pulsar el botón Modificar
    @FXML
    private void modificarAlumno(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Alumno de la tabla
        Alumno a = tableAlumnos.getSelectionModel().getSelectedItem();
        // Actualizamos la tabla con los datos del Alumno modificados
        a.setNombre(txtNombre.getText().trim());
        a.setApellidos(txtApellidos.getText().trim());
        a.setDni(txtDNI.getText().trim());
        a.setTelefono(txtTelefono.getText().trim());
        a.setEmail(txtEmail.getText().trim());
        a.setCurso(txtCurso.getText().trim());
        a.setCicloFormativo(txtCiclo.getText().trim());
        // Actualizamos el alumno en la base de datos
        new AlumnoDAO().update(a);
        // Actualizamos la tabla con los datos del Alumno modificados
        tableAlumnos.refresh();
        // Si el Alumno se ha modificado se muestra mensaje de información
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALUMNO");
        alert.setHeaderText(null);
        alert.setContentText("EL " + a.toString() + " SE HA MODIFICADO CORRECTAMENTE");
        alert.showAndWait();

        // Limpiamos los campos
        limpiarCampos();
    }
    // Metodo para eliminar un Alumno al pulsar el botón Eliminar
    @FXML
    private void eliminarAlumno(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Alumno de la tabla
        Alumno a = tableAlumnos.getSelectionModel().getSelectedItem();
        if (a != null) {
            // Eliminamos el alumno y actualizamos la tabla
            new AlumnoDAO().delete(a);
            listaAlumnos.clear();
            listaAlumnos.addAll(new AlumnoDAO().get());
            // Si el Alumno se ha eliminado se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALUMNO");
            alert.setHeaderText(null);
            alert.setContentText("EL " + a.toString() + " SE HA ELIMINADO CORRECTAMENTE");
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
                || txtApellidos.getText().isEmpty()
                || txtDNI.getText().isEmpty()
                || txtTelefono.getText().isEmpty()
                || txtEmail.getText().isEmpty()
                || txtCurso.getText().isEmpty()
                || txtCiclo.getText().isEmpty()) {
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
        txtApellidos.clear();
        txtDNI.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtCurso.clear();
        txtCiclo.clear();
    }

}
