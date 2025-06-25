package controladores;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import entidades.*;
import accesoADatos.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FormInfTicketsController implements Initializable {
    private ObservableList<Ticket> listarTickets;
    private ObservableList<DetalleTicket> listarDetalles;
    private TicketData tData = new TicketData();
    private DetalleTicketData dtData = new DetalleTicketData();
    private Ticket tSelec;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnCerrar;
    @FXML
    private TableView<Ticket> tblTickets;
    @FXML
    private TableColumn<Ticket, Integer> colIdT;
    @FXML
    private TableColumn<Ticket, LocalDate> colFecT;
    @FXML
    private TableColumn<Ticket,String> colTipT;
    @FXML
    private TableColumn<Ticket,Integer> colNroT;
    @FXML
    private TableColumn<Ticket,Integer> colIdC;
    @FXML
    private TableColumn<Ticket,String> colApeC;
    @FXML
    private TableColumn<Ticket,String> colNomC;
    @FXML
    private TableColumn<Ticket,Double> colTotT;

    @FXML
    private TableView<DetalleTicket> tblDetalles;
    @FXML
    private TableColumn<DetalleTicket,Integer> colIdP;
    @FXML
    private TableColumn<DetalleTicket,String> colCodP;
    @FXML
    private TableColumn<DetalleTicket,String> colDesP;
    @FXML
    private TableColumn<DetalleTicket,Double> colCanD;
    @FXML
    private TableColumn<DetalleTicket,Double> colPreP;
    @FXML
    private TableColumn<DetalleTicket,Double> colImpD;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarTickets = FXCollections.observableArrayList();
        listarDetalles = FXCollections.observableArrayList();
        this.colIdT.setCellValueFactory(new PropertyValueFactory<>("idTick"));
        this.colFecT.setCellValueFactory(new PropertyValueFactory<>("fechaTick"));
        this.colNroT.setCellValueFactory(new PropertyValueFactory<>("nroTick"));
        this.colTipT.setCellValueFactory(new PropertyValueFactory<>("tipoTick"));
        this.colIdC.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCliente().getIdClie()));
        this.colApeC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getApellidoClie()));
        this.colNomC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNombresClie()));
        this.colTotT.setCellValueFactory(new PropertyValueFactory<>("total"));

        this.colIdP.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProducto().getIdProd()));
        this.colCodP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getCodigoProd()));
        this.colDesP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getDescripcionCortaProd()));
        this.colCanD.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colPreP.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getProducto().getPrecioV()));
        this.colImpD.setCellValueFactory(new PropertyValueFactory<>("importe"));
        cargarTablaT();
    }

    @FXML
    private void onSelecFila(MouseEvent event){
            tSelec = tblTickets.getSelectionModel().getSelectedItem();
            if(tSelec!=null){
                int vIdT = tSelec.getIdTick();
                //busco el Detalle del Ticket seleccionado
                listarDetalles = (ObservableList) dtData.listarPorId(vIdT);
                tblDetalles.setItems(listarDetalles);
                txtTotal.setText(String.valueOf(tSelec.getTotal()));
            }
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    //metodo cargar tabla (tickets)
    private void cargarTablaT(){
        listarTickets = (ObservableList) tData.listarTickets();
        if(!listarTickets.isEmpty()){
            tblTickets.setItems(listarTickets);
        }

    }
}
