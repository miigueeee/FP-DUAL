/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Alumno;
import model.AlumnoDAO;
import model.Empresa;
import model.EmpresaDAO;
import model.PeriodoFormacion;
import model.PeriodoFormacionDAO;

/**
 * Controlador para la Asignación de Alumnos a Empresas: ? Vincular a cada
 * alumno con una o varias empresas durante su formación. ? Permitir gestionar
 * el periodo de formación en cada empresa (fecha de inicio y fin). ? Visualizar
 * un listado de las asignaciones realizadas y permitir actualizarlas o
 * eliminarlas.
 *
 * @author migue
 */
public class PeriodoFormacionController implements Initializable {

    @FXML
    private TableView<PeriodoFormacion> tablePeriodos;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colAlumno;
    @FXML
    private TableColumn colEmpresa;
    @FXML
    private TableColumn colInicio;
    @FXML
    private TableColumn colFin;
    @FXML
    private TableColumn<PeriodoFormacion, String> colDuracion;
    @FXML
    private ComboBox<Alumno> comboAlumno;
    @FXML
    private ComboBox<Empresa> comboEmpresa;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Button btnAsignar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVolver;
    // Arraylist de JavaFX
    private ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();
    private ObservableList<Empresa> listaEmpresas = FXCollections.observableArrayList();
    private ObservableList<PeriodoFormacion> listaPeriodos = FXCollections.observableArrayList();

//Utilizamos el método initialize para cargar los valores -> ObservableList
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAlumno.setCellValueFactory(new PropertyValueFactory<>("alumno"));
        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        colInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion_total"));
        // Formato fecha yyyy-MM-dd
        colInicio.setCellFactory(column -> new TableCell<PeriodoFormacion, java.util.Date>() {
            private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            protected void updateItem(java.util.Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(format.format(date));
                }
            }
        });
        // Formato fecha yyyy-MM-dd
        colFin.setCellFactory(column -> new TableCell<PeriodoFormacion, java.util.Date>() {
            private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            protected void updateItem(java.util.Date date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(format.format(date));
                }
            }
        });
        // Calculamos la Duracion total de la formación en horas
        colDuracion.setCellValueFactory(cellData -> {
            int horas = cellData.getValue().calcularDuracion();
            return new ReadOnlyStringWrapper(String.valueOf(horas));
        });
        // Cargamos alumnos y empresas en los combobox
        comboAlumno.setItems(listaAlumnos);
        comboEmpresa.setItems(listaEmpresas);

        // Cargamos los alumnos y empresas de la base de datos y los mostramos en la tabla
        listaAlumnos.addAll(new AlumnoDAO().get());
        listaEmpresas.addAll(new EmpresaDAO().get());
        listaPeriodos.addAll(new PeriodoFormacionDAO().get());
        tablePeriodos.setItems(listaPeriodos);

    }
    // Metodo para agregar un Periodo de formacion al pulsar el botón Agregar

    @FXML
    private void Asignar(ActionEvent event) {
        // Controlamos que no haya campos vacios y las fechas sean correctas
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos alumno y empresa de combobox, y las fechas de inicio y fin
        Alumno a = comboAlumno.getValue();
        Empresa e = comboEmpresa.getValue();
        LocalDate localFechaInicio = dateInicio.getValue();
        LocalDate localFechaFin = dateFin.getValue();
        PeriodoFormacion p = new PeriodoFormacion();
        p.setAlumno(a);
        p.setEmpresa(e);
        // Convertir fechas LocalDate a java.util.Date
        java.util.Date fechaInicio = java.util.Date.from(localFechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date fechaFin = java.util.Date.from(localFechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
        p.setFechaInicio(fechaInicio);
        p.setFechaFin(fechaFin);
        // Comprobamos que el periodo de formacion no exista
        if (new PeriodoFormacionDAO().existe(p.getAlumno().getId(), p.getEmpresa().getId(), p.getFechaInicio(), p.getFechaFin()) == null) {
            new PeriodoFormacionDAO().create(p);
            // Refrescamos la tabla
            listaPeriodos.clear();
            listaPeriodos.addAll(new PeriodoFormacionDAO().get());
            // Si el Periodo se ha añadido se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" PERIODO DE FORMACION ");
            alert.setHeaderText(null);
            alert.setContentText("EL " + p.toString() + " SE HA AÑADIDO CORRECTAMENTE");
            alert.showAndWait();
            // Limpiamos los campos            
            limpiarCampos();
        } else {
            // Si el Producto no se ha añadido se muestra mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" ERROR ");
            alert.setHeaderText(null);
            alert.setContentText(" YA EXISTE EL " + p.toString());
            alert.showAndWait();
        }
    }

    // Metodo para modificar un Periodo al pulsar el botón Modificar
    @FXML
    private void Modificar(ActionEvent event) {
        // Controlamos que no haya campos vacios y las fechas sean correctas
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Periodo de la tabla
        PeriodoFormacion p = (PeriodoFormacion) tablePeriodos.getSelectionModel().getSelectedItem();
        if (p != null) {
            // Obtener valores de los campos
            Alumno a = comboAlumno.getValue();
            Empresa e = comboEmpresa.getValue();
            LocalDate localFechaInicio = dateInicio.getValue();
            LocalDate localFechaFin = dateFin.getValue();

            p.setAlumno(a);
            p.setEmpresa(e);
            // Convertir fechas LocalDate a java.util.Date
            p.setFechaInicio(java.util.Date.from(localFechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            p.setFechaFin(java.util.Date.from(localFechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            // Actualizamos el periodo en la base de datos
            new PeriodoFormacionDAO().update(p);
            // Actualizamos la tabla con los datos del Modulo modificados
            tablePeriodos.refresh();
            // Si el Periodo se ha modificado se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PERIODO DE FORMACION");
            alert.setHeaderText(null);
            alert.setContentText("EL " + p.toString() + " SE HA MODIFICADO CORRECTAMENTE");
            alert.showAndWait();

            // Limpiamos los campos
            limpiarCampos();
        }
    }
    // Metodo para eliminar un Periodo al pulsar el botón Eliminar

    @FXML
    private void Eliminar(ActionEvent event) {
        // Controlamos que no haya campos vacios y las fechas sean correctas
        if (!validarCampos()) {
            return;
        }
        // Seleccionamos el Periodo de la tabla
        PeriodoFormacion p = tablePeriodos.getSelectionModel().getSelectedItem();
        if (p != null) {
            // Eliminamos el periodo y actualizamos la tabla
            new PeriodoFormacionDAO().delete(p);
            listaPeriodos.clear();
            listaPeriodos.addAll(new PeriodoFormacionDAO().get());
            // Si el periodo se ha eliminado se muestra mensaje de información
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" PERIODO DE FORMACION");
            alert.setHeaderText(null);
            alert.setContentText("EL " + p.toString() + " SE HA ELIMINADO CORRECTAMENTE");
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

    // Metodo para comprobar que los campos no estén vacios y las fechas son correctas
    public boolean validarCampos() {
        if (comboAlumno.getValue() == null
                || comboEmpresa.getValue() == null
                || dateInicio.getValue() == null
                || dateFin.getValue() == null) {
            // Si no se introducen todos los campos se muestra mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("SE DEBEN COMPLETAR TODOS LOS CAMPOS");
            alert.showAndWait();
            return false;
        }
        // Validamos que la fecha de inicio no sea posterior a la fecha de fin
        if (dateInicio.getValue() != null && dateFin.getValue() != null) {
            if (dateInicio.getValue().isAfter(dateFin.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText(" LA FECHA DE INICIO NO PUEDE SER POSTERIOR A LA FECHA DE FIN ");
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }

    // Metodo para limpiar los TextField
    private void limpiarCampos() {
        comboAlumno.setValue(null);
        comboEmpresa.setValue(null);
        dateInicio.setValue(null);
        dateFin.setValue(null);
    }

    // Metodo para seleccionar un Periodo de la tabla y cargar sus datos en los campos
    @FXML
    public void seleccionar(MouseEvent event) {
        PeriodoFormacion p = (PeriodoFormacion) tablePeriodos.getSelectionModel().getSelectedItem();
        try {
            comboAlumno.setValue(p.getAlumno());
            comboEmpresa.setValue(p.getEmpresa());
            // Convertimos Date a LocalDate para solucionar errores con las fechas
            LocalDate fechaInicio = p.getFechaInicio().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDate fechaFin = p.getFechaFin().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            dateInicio.setValue(fechaInicio);
            dateFin.setValue(fechaFin);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al cargar fechas: " + e.getMessage());
            alert.showAndWait();
        }
    }

}
