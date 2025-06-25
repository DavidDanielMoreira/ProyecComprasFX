package controladores;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import entidades.*;
import accesoADatos.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormTicketsController implements Initializable {
    public static boolean isOpen = false;
    private ObservableList<ModoPago> listarMPagos;
    private ObservableList<DetalleTicket> listarDetalles;
    private ModoPagoData mpData = new ModoPagoData();
    private TicketData tData = new TicketData();
    private ClienteData cData = new ClienteData();
    private ProductoData pData = new ProductoData();
    private DetalleTicketData dtData = new DetalleTicketData();
    private Cliente cSelec;
    private ModoPago mpSelec;
    private Producto pSelec;
    private Ticket tSelec;
    private LocalDate vFecSelec;    //guarda la fecha seleccionada del DatePicket
    private double vDesc;
    private double vTotal=0;
    private double vAlmTotal=0; //en caso de aplicar el descuento guarda el total en la variable
    private boolean descuento = false;
    private boolean bajas = false;
    @FXML
    private TextField txtIdCom;
    @FXML
    private TextField txtIdCli;
    @FXML
    private TextField txtTipoCom;
    @FXML
    private TextField txtNroCom;
    @FXML
    private TextField txtApeCli;
    @FXML
    private TextField txtNomCli;
    @FXML
    private TextField txtDomCli;
    @FXML
    private TextField txtDniCli;
    @FXML
    private TextField txtTelCli;
    @FXML
    private TextField txtIdProd;
    @FXML
    private TextField txtCodProd;
    @FXML
    private TextField txtDesProd;
    @FXML
    private TextField txtPreProd;
    @FXML
    private TextField txtCanDeta;
    @FXML
    private TextField txtImpDeta;
    @FXML
    private TextField txtDescCom;
    @FXML
    private TextField txtTotCom;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnBajas;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnProductos;
    @FXML
    private ComboBox<ModoPago> cmbMPagos;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private RadioButton rbDescuento;
    @FXML
    private TableView<DetalleTicket> tblDetalleTickets;
    @FXML
    private TableColumn<DetalleTicket,Integer> colIdP;
    @FXML
    private TableColumn<DetalleTicket,String> colCodP;
    @FXML
    private TableColumn<DetalleTicket,String> colDesP;
    @FXML
    private TableColumn<DetalleTicket,Double> colCanD;
    @FXML
    private TableColumn<DetalleTicket,Double> colPreD;
    @FXML
    private TableColumn<DetalleTicket,Double> colImpD;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isOpen = true;
        listarMPagos = FXCollections.observableArrayList();
        listarDetalles = FXCollections.observableArrayList();
        this.colIdP.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProducto().getIdProd()));
        this.colCodP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getCodigoProd()));
        this.colDesP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getDescripcionCortaProd()));
        this.colCanD.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colPreD.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colImpD.setCellValueFactory(new PropertyValueFactory<>("importe"));
        //txtDescCom.setDisable(true); //inhabilita el campo descuento
        cargarCombo();
        crearNroComprobante();
    }
    //recibir datos Cliente
    public void recibirDatosCliente(Cliente cliente){
        txtIdCli.setText(String.valueOf(cliente.getIdClie()));
        txtApeCli.setText(cliente.getApellidoClie());
        txtNomCli.setText(cliente.getNombresClie());
        txtDniCli.setText(cliente.getDniClie());
        txtDomCli.setText(cliente.getDomicilioClie());
        txtTelCli.setText(cliente.getTelefonoClie());
    }
    //metodo recibir datos Producto
    public void recibirDatosProducto(Producto producto){
        txtIdProd.setText(String.valueOf(producto.getIdProd()));
        txtCodProd.setText(producto.getCodigoProd());
        txtDesProd.setText(producto.getDescripcionCortaProd());
        txtPreProd.setText(String.valueOf(producto.getPrecioV()));
    }
    @FXML
    private void onBtnGuardar(ActionEvent event){
        if(!txtIdCli.getText().isEmpty() && vFecSelec!=null && !txtTotCom.getText().isEmpty() && mpSelec!=null){
            Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
            mensajeA.setTitle("ALTAS VENTAS");
            mensajeA.setContentText("¿Confirma la venta?");
            mensajeA.setHeaderText(null);
            Optional<ButtonType> opcion = mensajeA.showAndWait();
            if(opcion.get()==ButtonType.OK){
                insertTicket();
                insertDTicket();
            }
        }else{
            System.out.println("Campos vacios...");
        }
    }
    @FXML
    private void onBtnEditar(ActionEvent event){

    }
    @FXML
    private void onBtnBajas(ActionEvent event){

    }
    @FXML
    private void onBtnCancelar(ActionEvent event){

    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
        isOpen = false;
    }
    @FXML
    private void onBtnAgregar(ActionEvent event){

    }
    @FXML
    private void onBtnInsertar(ActionEvent event){
        if(!txtIdProd.getText().isEmpty() && !txtCanDeta.getText().isEmpty()){
            insertarProductoATabla();
        }else{
            System.out.println("Campos vacios...");
        }
    }
    @FXML
    private void onBtnEliminar(ActionEvent event){
            if(!tblDetalleTickets.getItems().isEmpty()){
                eliminarProductoTabla();
            }else{
                System.out.println("Tabla vacia...");
            }
    }
    @FXML
    private void onBtnClientes(ActionEvent event){
            abrirClientes();
    }
    @FXML
    private void onBtnProductos(ActionEvent event){
        abrirProductos();
    }
    @FXML
    private void onBuscarPorNroF(ActionEvent event){

    }
    @FXML
    private void onFechaSelec(MouseEvent event){
        vFecSelec = dpFecha.getValue();
    }
    @FXML
    private void onSelecModo(MouseEvent event){
        mpSelec = cmbMPagos.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void onCalcImp(KeyEvent event){
        if(!txtCanDeta.getText().isEmpty() && !txtPreProd.getText().isEmpty()){
            double vImp = Double.parseDouble(txtCanDeta.getText())*Double.parseDouble(txtPreProd.getText());
            txtImpDeta.setText(String.valueOf(vImp));
        }

    }
    @FXML
    private void onAplDescuento(){
        descuento = true;
        txtDescCom.setDisable(false);
        if(rbDescuento.isSelected()){
            if(!txtTotCom.getText().isEmpty() && !txtDescCom.getText().isEmpty()){
                vAlmTotal = Double.parseDouble(txtTotCom.getText());
                vDesc = Double.parseDouble(txtDescCom.getText());
                double vTotN = (Double.parseDouble(txtTotCom.getText()) - (Double.parseDouble(txtTotCom.getText()) * vDesc));
                txtTotCom.setText(String.valueOf(vTotN));
            }else{
                System.out.println("Campos vacios...");
                rbDescuento.setSelected(false);
            }
        }else{
            txtDescCom.setText("");
            txtTotCom.setText(String.valueOf(vAlmTotal));
        }

    }
    @FXML
    private void onCalcDescuento(KeyEvent event){

      
    }

    //cargar combo ListaPago
    private void cargarCombo(){
        listarMPagos = (ObservableList) mpData.listarModos();
        if(!listarMPagos.isEmpty()){
            cmbMPagos.getItems().addAll(listarMPagos);
        }
    }

    //metodo insertar comprobante
    private void insertTicket(){
        String vNroT = txtNroCom.getText().trim();
        String vTipoT = txtTipoCom.getText().trim();
        int vIdC = Integer.parseInt(txtIdCli.getText());
        //busco el Cliente
        cSelec = cData.buscarPorId(vIdC);
        if(txtDescCom.getText().isEmpty()){
            double vDesc = 0.0;
        }else{
            double vDesc = Double.parseDouble(txtDescCom.getText());
        }

        double vTotal = Double.parseDouble(txtTotCom.getText());
        //creo el objecto
        Ticket ticket = new Ticket(vFecSelec,vNroT,vTipoT,cSelec,mpSelec,vDesc,vTotal,true);
        tData.insertarTicket(ticket);
    }
    //metodo insertar DetalleTicket
    private void insertDTicket(){
        int vUltId = tData.ultimoId();
        //busco el Ticket
        tSelec = tData.buscarPorId(vUltId);
        if(!listarDetalles.isEmpty()){
            for(DetalleTicket dt : listarDetalles){
                dt.setTicket(tSelec);
                //busco el Producto
                int vIdP = dt.getProducto().getIdProd();
                pSelec = pData.buscarPorId(vIdP);
                //acualizo el stock
                double vStoN = pSelec.getStock()-dt.getCantidad();
                pData.actualizarStock(vStoN,vIdP);
                dtData.insertarDetalleTicket(dt);
            }
        }

    }
    //metodo insertar Producto a tabla
    private void insertarProductoATabla(){
        int vIdP = Integer.parseInt(txtIdProd.getText());
        pSelec = pData.buscarPorId(vIdP);
        double vCan = Double.parseDouble(txtCanDeta.getText());
        double vPre = Double.parseDouble(txtPreProd.getText());
        double vImp = Double.parseDouble(txtImpDeta.getText());
        //creo el objecto
        DetalleTicket dt = new DetalleTicket();
        dt.setProducto(pSelec);
        dt.setCantidad(vCan);
        dt.setPrecio(vPre);
        dt.setImporte(vImp);
        listarDetalles.add(dt);
        tblDetalleTickets.setItems(listarDetalles);
        vTotal = vTotal + vImp;
        txtTotCom.setText(String.valueOf(vTotal));
        limpiarCamposProd();
    }

    //metodo eliminar Producto de la tabla
    private void eliminarProductoTabla(){
        int fSelec = tblDetalleTickets.getSelectionModel().getSelectedIndex();
        double vImp=0;
        if(fSelec!=-1){
            vImp = this.colImpD.getCellData(fSelec);
            //descuenta el importe de la fila seleccionada
            vTotal = vTotal - vImp;
            txtTotCom.setText(String.valueOf(vTotal));
            //elimina la fila
            tblDetalleTickets.getItems().remove(fSelec);
        }else{
            System.out.println("Debe seleccionar una fila...");
        }
    }

    //metodo dar de baja una venta y acualizar stock
    private void bajasTickets(){

    }

    //metodo abrir Clientes
    private void abrirClientes(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formClientes.fxml"));
        try {
            Parent root = loader.load();
            FormClientesController formClientesController = loader.getController();
            formClientesController.setFormTicketsController(this);
            Scene scene = new Scene(root);
            Stage fClie = new Stage();
            fClie.setScene(scene);
            fClie.setResizable(false);
            fClie.setTitle("CLIENTES");
            fClie.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al abrir Clientes..."+e.getMessage());
        }
    }

    //metodo abrir Productos
    private void abrirProductos(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formProductos.fxml"));
        try {
            Parent root = loader.load();
            FormProductosController formProductosController = loader.getController();
            formProductosController.setFormTicketsController(this);
            Scene scene = new Scene(root);
            Stage fProd = new Stage();
            fProd.setScene(scene);
            fProd.setResizable(false);
            fProd.setTitle("PRODUCTOS");
            fProd.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al abrir Productos..."+e.getMessage());
        }
    }

    //metodo crear Nro Comprobante
    private void crearNroComprobante(){
        int ultId = tData.ultimoId();
        if(ultId==0){
            String vNroT = "00000001";
            txtNroCom.setText(vNroT);
        }else if(ultId<=9){
            ultId = ultId+1;
            txtNroCom.setText("0000000"+ultId);
        }else if(ultId<=99){
            txtNroCom.setText("000000"+ultId);
        }else if(ultId<=999){
            txtNroCom.setText("00000"+ultId);
        }else if(ultId<=9999){
            txtNroCom.setText("0000"+ultId);
        }
    }

    //metodo limpiar campos Productos
    private void limpiarCamposProd(){
        txtIdProd.setText("");
        txtCodProd.setText("");
        txtDesProd.setText("");
        txtCanDeta.setText("");
        txtPreProd.setText("");
        txtImpDeta.setText("");
    }

}
