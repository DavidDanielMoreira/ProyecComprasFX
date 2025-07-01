package controladores;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import entidades.*;
import accesoADatos.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormInfComprasController implements Initializable {
    private ObservableList<Compra> listarCompras;
    private ObservableList<DetalleCompra> listarDetalles;
    private CompraData cData = new CompraData();
    private DetalleCompraData dcData = new DetalleCompraData();
    private ProductoData prodData = new ProductoData();
    private Compra cSelec;
    private Producto prodSelec;
    private boolean bajas = false;
    @FXML
    private javafx.scene.control.TextField txtTotal;
    @FXML
    private Button btnEliminarCompra;
    @FXML
    private Button btnCerrar;
    @FXML
    private TableView<Compra> tblCompras;
    @FXML
    private TableColumn<Compra,Integer> colIdC;
    @FXML
    private TableColumn<Compra, LocalDate> colFecC;
    @FXML
    private TableColumn<Compra,String> colTipC;
    @FXML
    private TableColumn<Compra,String> colNroC;
    @FXML
    private TableColumn<Compra,Integer> colIdProv;
    @FXML
    private TableColumn<Compra,String> colRazProv;
    @FXML
    private TableColumn<Compra,Double> colTotC;

    @FXML
    private TableView<DetalleCompra> tblDetalles;
    @FXML
    private TableColumn<DetalleCompra,Integer> colIdP;
    @FXML
    private TableColumn<DetalleCompra,String> colCodP;
    @FXML
    private TableColumn<DetalleCompra,String> colDesP;
    @FXML
    private TableColumn<DetalleCompra,Double> colCanD;
    @FXML
    private TableColumn<DetalleCompra,Double> colPreD;
    @FXML
    private TableColumn<DetalleCompra,Double> colImpD;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarCompras = FXCollections.observableArrayList();
        listarDetalles = FXCollections.observableArrayList();
        this.colIdC.setCellValueFactory(new PropertyValueFactory<>("idComp"));
        this.colFecC.setCellValueFactory(new PropertyValueFactory<>("fechaComp"));
        this.colTipC.setCellValueFactory(new PropertyValueFactory<>("tipoFacturaComp"));
        this.colNroC.setCellValueFactory(new PropertyValueFactory<>("nroFacturaComp"));
        this.colIdProv.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProveedor().getIdProv()));
        this.colRazProv.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProveedor().getRazonSocialProv()));
        this.colTotC.setCellValueFactory(new PropertyValueFactory<>("total"));

        this.colIdP.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProducto().getIdProd()));
        this.colCodP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getCodigoProd()));
        this.colDesP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getDescripcionCortaProd()));
        this.colCanD.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colPreD.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colImpD.setCellValueFactory(new PropertyValueFactory<>("importe"));
        cargarTablaC();
    }
    @FXML
    private void onSelecFila(MouseEvent event){
        cSelec = tblCompras.getSelectionModel().getSelectedItem();
        if(cSelec!=null){
            cargarTablaD();
        }
    }
    @FXML
    private void onBtnEliminarCompra(){
        if(cSelec!=null){
            Alert mensajeE = new Alert(Alert.AlertType.CONFIRMATION);
            mensajeE.setTitle("BAJAS COMPROBANTES DE COMPRAS");
            mensajeE.setContentText("¿Confirma la baja del comprobante?");
            mensajeE.setHeaderText(null);
            Optional<ButtonType> opcion = mensajeE.showAndWait();
            if(opcion.get()==ButtonType.OK){
                bajCompra();
                cargarTablaC();
            }
        }else{
            Alert mensajeI = new Alert(Alert.AlertType.INFORMATION);
            mensajeI.setTitle("INFORMACION!!!");
            mensajeI.setContentText("Debe seleccionar una fila de la tabla");
            mensajeI.setHeaderText(null);
            mensajeI.showAndWait();
        }
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    //cargar tabla Compras
    private void cargarTablaC(){
        listarCompras = (ObservableList) cData.listarCompras();
        if(!listarCompras.isEmpty()){
            tblCompras.setItems(listarCompras);
        }
    }

    //metodo cargar tabla Detalles
    private void cargarTablaD(){
        listarDetalles = (ObservableList) dcData.listarPorId(cSelec.getIdComp());
        if(!listarDetalles.isEmpty()){
            tblDetalles.setItems(listarDetalles);
        }
    }

    //metodo dar de baja la Compra
    private void bajCompra(){
        int vIdC = cSelec.getIdComp();
        if(vIdC!=0){
            //bajas Compra
            cData.bajasPorId(vIdC);
            for(DetalleCompra dc: listarDetalles){
                    double vStoN = 0;
                    int vIdProd= dc.getProducto().getIdProd();
                    prodSelec = prodData.buscarPorId(vIdProd);
                    vStoN = prodSelec.getStock() + dc.getCantidad();
                    //actualizo el stock
                    prodData.actualizarStock(vStoN,vIdProd);
            }
        }
    }

}
