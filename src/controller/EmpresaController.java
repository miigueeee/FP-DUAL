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
import model.Empresa;
import model.EmpresaDAO;

/**
 * Controlador de Empresas, permite agregar, modificar y eliminar empresas y
 * visualizarlos en una tabla
 *
 * @author migue
 */
public class EmpresaController implements Initializable {

    @FXML
    private TableView<Empresa> tableEmpresas;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCIF;
    @FXML
    private TableColumn colSector;
    @FXML
    private TableColumn colContacto;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCIF;
    @FXML
    private TextField txtSector;
    @FXML
    private TextField txtContacto;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDireccion;
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
    private ObservableList<Empresa> listaEmpresas = FXCollections.observableArrayList();

      //Utilizamos el método initialize para cargar los valores -> ObservableList
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Asociamos los datos de Empresa a las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCIF.setCellValueFactory(new PropertyValueFactory<>("cif"));
        colSector.setCellValueFactory(new PropertyValueFactory<>("sector"));
        colContacto.setCellValueFactory(new PropertyValueFactory<>("contacto"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        // Cargamos las empresas desde la base de datos y mostramos en la tabla
        listaEmpresas.addAll(new EmpresaDAO().get());
        tableEmpresas.setItems(listaEmpresas);
    }
    // Metodo para agregar Empresa al pulsar el botón Agregar

    @FXML
    private void guardarEmpresa(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Creamos una nueva empresa con los datos introducidos en los campos correspondientes
        Empresa e = new Empresa();
        e.setNombre(txtNombre.getText().trim());
        e.setCif(txtCIF.getText().trim());
        e.setSector(txtSector.getText().trim());
        e.setContacto(txtContacto.getText().trim());
        e.setTelefono(txtTelefono.getText().trim());
        e.setEmail(txtEmail.getText().trim());
        e.setDireccion(txtDireccion.getText().trim());
        // Comprobamos si la Empresa ya existe o no antes de agregarla.
        if (new EmpresaDAO().existe(e.getCif()) == null) {
            // Guardamos la empresa en la base de datos
            new EmpresaDAO().create(e);
            // Refrescamos la tabla
            listaEmpresas.clear();
            listaEmpresas.addAll(new EmpresaDAO().get());
            // Si la Empresa se ha añadido se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EMPRESA");
            alert.setHeaderText(null);
            alert.setContentText("LA " + e.toString() + " SE HA AÑADIDO CORRECTAMENTE");
            alert.showAndWait();
            // Limpiar los campos
            limpiarCampos();
        } else {
            // Mostrar mensaje de error por CIF duplicado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" ERROR: CIF DUPLICADO ");
            alert.setHeaderText(null);
            alert.setContentText(" YA EXISTE UNA EMPRESA CON CIF " + e.getCif());
            alert.showAndWait();
        }
    }
    // Metodo para seleccionar una Empresa de la tabla y cargar sus datos en los campos
    @FXML
    public void seleccionar(MouseEvent event) {
        Empresa e = this.tableEmpresas.getSelectionModel().getSelectedItem();
        txtNombre.setText(e.getNombre());
        txtCIF.setText(e.getCif());
        txtSector.setText(e.getSector());
        txtContacto.setText(e.getContacto());
        txtTelefono.setText(e.getTelefono());
        txtEmail.setText(e.getEmail());
        txtDireccion.setText(e.getDireccion());

    }

    // Metodo para modificar una Empresa al pulsar el botón Modificar
    @FXML
    private void modificarEmpresa(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos la empresa de la tabla
        Empresa e = tableEmpresas.getSelectionModel().getSelectedItem();
        // Actualizamos la tabla con los datos la empresa modificados
        e.setNombre(txtNombre.getText().trim());
        e.setCif(txtCIF.getText().trim());
        e.setSector(txtSector.getText().trim());
        e.setContacto(txtContacto.getText().trim());
        e.setTelefono(txtTelefono.getText().trim());
        e.setEmail(txtEmail.getText().trim());
        e.setDireccion(txtDireccion.getText().trim());
        // Actualizamos la empresa en la base de datos
        new EmpresaDAO().update(e);
        // Actualizamos la tabla con los datos de la empresa modificados
        tableEmpresas.refresh();
        // Si la empresa se ha modificado se muestra mensaje de información
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("EMPRESA");
        alert.setHeaderText(null);
        alert.setContentText("LA " + e.toString() + " SE HA MODIFICADO CORRECTAMENTE");
        alert.showAndWait();
        // Limpiar los campos
        limpiarCampos();

    }
    // Metodo para eliminar una Empresa al pulsar el botón Eliminar

    @FXML
    private void eliminarEmpresa(ActionEvent event) {
        // Controlamos que no haya campos vacios
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos la empresa de la tabla
        Empresa e = tableEmpresas.getSelectionModel().getSelectedItem();
        if (e != null) {
            // Eliminamos la empresa de la base de datos
            new EmpresaDAO().delete(e);
            // Actualizar la lista de empresas
            listaEmpresas.clear();
            listaEmpresas.addAll(new EmpresaDAO().get());
            // Si la empresa se ha eliminado se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EMPRESA");
            alert.setHeaderText(null);
            alert.setContentText("LA " + e.toString() + " SE HA ELIMINADO CORRECTAMENTE");
            alert.showAndWait();
            // Limpiar los campos
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
                || txtCIF.getText().isEmpty()
                || txtSector.getText().isEmpty()
                || txtContacto.getText().isEmpty()
                || txtTelefono.getText().isEmpty()
                || txtEmail.getText().isEmpty()
                || txtDireccion.getText().isEmpty()) {
            // Mostrar mensaje de error si hay campos vacíos
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
        txtCIF.clear();
        txtSector.clear();
        txtContacto.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtDireccion.clear();
    }
}
