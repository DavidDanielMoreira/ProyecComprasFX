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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormComprasController implements Initializable {
    public static boolean isOpen = false;
    private ObservableList<ModoPago> listarModos; //lista los ModoPagos cargados
    private ObservableList<DetalleCompra> cargarDetalles;   //se utiliza para cargar los productos que se compran
    private ModoPagoData mpData = new ModoPagoData();
    private ProveedorData provData = new ProveedorData();
    private ProductoData prodData = new ProductoData();
    private CompraData compData = new CompraData();
    private DetalleCompraData dcData = new DetalleCompraData();
    private ModoPago mpSelec;
    private Proveedor provSelec;
    private Producto prodSelec;
    private Compra compSelec;
    private DetalleCompra dcSelec;
    private double vTotal;
    private LocalDate vFecha; //guarda la fecha seleccionada del datePiker;
    @FXML
    private TextField txtIdComp;
    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtNroF;
    @FXML
    private TextField txtIdProv;
    @FXML
    private TextField txtRaz;
    @FXML
    private TextField txtCui;
    @FXML
    private TextField txtIdProd;
    @FXML
    private TextField txtCod;
    @FXML
    private TextField txtDes;
    @FXML
    private TextField txtPre;
    @FXML
    private TextField txtCan;
    @FXML
    private TextField txtImp;
    @FXML
    private TextField txtTot;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox<ModoPago> cmbModos;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnBajas;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnProveedores;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnProductos;
    @FXML
    private TableView<DetalleCompra> tblDetalleCompras;
    @FXML
    private TableColumn<DetalleCompra,Integer> colIdP;
    @FXML
    private TableColumn<DetalleCompra,String> colCodP;
    @FXML
    private TableColumn<DetalleCompra,String> colDesP;
    @FXML
    private TableColumn<DetalleCompra,Double> colCan;
    @FXML
    private TableColumn<DetalleCompra,Double> colPre;
    @FXML
    private TableColumn<DetalleCompra,Double> colImp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            isOpen = true;
            listarModos = FXCollections.observableArrayList();
            cargarDetalles = FXCollections.observableArrayList();
            this.colIdP.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProducto().getIdProd()));
            this.colCodP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getCodigoProd()));
            this.colDesP.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProducto().getDescripcionCortaProd()));
            this.colCan.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            this.colPre.setCellValueFactory(new PropertyValueFactory<>("precio"));
            this.colImp.setCellValueFactory(new PropertyValueFactory<>("importe"));
            cargarCombo();
    }
    //metodo recibir datos del Proveedor
    public void recibirDatosProveedor(Proveedor proveedor){
        txtIdProv.setText(String.valueOf(proveedor.getIdProv()));
        txtRaz.setText(proveedor.getRazonSocialProv());
        txtCui.setText(proveedor.getCuitProv());
    }

    //metodo recibir datos Producto
    public void recibirDatosProducto(Producto producto){
        txtIdProd.setText(String.valueOf(producto.getIdProd()));
        txtCod.setText(producto.getCodigoProd());
        txtDes.setText(producto.getDescripcionCortaProd());
    }

    @FXML
    private void onBtnAceptar(ActionEvent event){
        if(!txtIdProv.getText().isEmpty() && !txtRaz.getText().isEmpty() && vFecha!=null){
            Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
            mensajeA.setTitle("ALTAS COMPRAS");
            mensajeA.setContentText("¿Confirma el alta de la compra?");
            mensajeA.setContentText(null);
            Optional<ButtonType> opcion = mensajeA.showAndWait();
            if(opcion.get()==ButtonType.OK){
                insertCompras();
                insertDetalle();
                limpiarCampos();
                cargarDetalles.clear();
            }else{
                limpiarCampos();
                cargarDetalles.clear();
            }
        }else{
            Alert mensajeI = new Alert(Alert.AlertType.INFORMATION);
            mensajeI.setTitle("INFORMACION");
            mensajeI.setContentText("Campos vacios");
            mensajeI.setHeaderText(null);
            mensajeI.showAndWait();
        }

    }
    @FXML
    private void onBtnBajas(ActionEvent event){

    }
    @FXML
    private void onBtnCancelar(ActionEvent event){
        limpiarCampos();
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){

    }
    @FXML
    private void onBtnProveedores(ActionEvent event){
        abrirProveedores();
    }
    @FXML
    private void onBtnAgregar(ActionEvent event){

    }
    @FXML
    private void onBtnEliminar(ActionEvent event){
        eliminarProductoTabla();
    }
    @FXML
    private void onBtnInsertar(ActionEvent event){
        if(!txtIdProd.getText().isEmpty()){
            insertarProductoATabla();
        }else{
            System.out.println("Campos vacios...");
        }

    }
    @FXML
    private void onBtnProductos(ActionEvent event){
        abrirProductos();
    }
    @FXML
    private void onSelecFecha(MouseEvent event){
        vFecha = dpFecha.getValue();
    }
    @FXML
    private void onSelecModo(MouseEvent event){
        mpSelec = cmbModos.getSelectionModel().getSelectedItem();
    }
    @FXML
    private void onSelecFila(MouseEvent event){

    }
    @FXML
    private void onCalcImp(KeyEvent event){
            if(!txtPre.getText().isEmpty()){
                double vImp = Double.parseDouble(txtPre.getText()) * Double.parseDouble(txtCan.getText());
                txtImp.setText(String.valueOf(vImp));
            }
    }

    //metodo cargar combo
    private void cargarCombo(){
        listarModos = (ObservableList) mpData.listarModos();
        if(!listarModos.isEmpty()){
            cmbModos.getItems().addAll(listarModos);
        }
    }
    //metodo insertar compra
    private void insertCompras(){
            String vTipo = txtTipo.getText().trim();
            String vNroF = txtNroF.getText().trim();
            int vIdProv = Integer.parseInt(txtIdProv.getText());
            //busco el Proveedor
            provSelec = provData.buscarPorId(vIdProv);
            double vTotal = Double.parseDouble(txtTot.getText());
            //creo el objecto
            Compra compra = new Compra();
            compra.setFechaComp(vFecha);
            compra.setTipoFacturaComp(vTipo);
            compra.setNroFacturaComp(vNroF);
            compra.setProveedor(provSelec);
            compra.setModoPado(mpSelec);
            compra.setTotal(vTotal);
            compra.setEstadoComp(true);
            compData.insertarCompra(compra);
    }
    //metodo insertar DetalleCompra
    private void insertDetalle(){
        int vUltC = compData.ultimoId();
        if(!cargarDetalles.isEmpty()){
            for(DetalleCompra dc: cargarDetalles){
                //busco la Compra
                compSelec = compData.buscarPorId(vUltC);
                dc.setCompra(compSelec);
                dcData.insertarDetalleCompra(dc);
                //actualizo el stock
                int vIdProd = dc.getProducto().getIdProd();
                //busco el Producto
                prodSelec = prodData.buscarPorId(vIdProd);
                double vStoN = prodSelec.getStock() + dc.getCantidad();
                prodData.actualizarStock(vStoN,vIdProd);
            }
        }
    }

    //metodo insertarProducto a la tabla
    private void insertarProductoATabla(){
        int vIdProd = Integer.parseInt(txtIdProd.getText());
        //busco el Producto
        prodSelec = prodData.buscarPorId(vIdProd);
        double vPre = Double.parseDouble(txtPre.getText());
        double vCan = Double.parseDouble(txtCan.getText());
        double vImp = Double.parseDouble(txtImp.getText());
        vTotal = vTotal + vImp;
        txtTot.setText(String.valueOf(vTotal));
        //creo el objecto
        DetalleCompra dCompra = new DetalleCompra();
        dCompra.setProducto(prodSelec);
        dCompra.setPrecio(vPre);
        dCompra.setCantidad(vCan);
        dCompra.setImporte(vImp);
        cargarDetalles.add(dCompra);
        tblDetalleCompras.setItems(cargarDetalles);
    }

    //metodo eliminar Producto de la tabla
    private void eliminarProductoTabla(){
        int fSelec = tblDetalleCompras.getSelectionModel().getSelectedIndex();
        if(fSelec!=-1){
            double vImp = this.colImp.getCellData(fSelec);
            vTotal =vTotal - vImp;
            txtTot.setText(String.valueOf(vTotal));
            tblDetalleCompras.getItems().remove(fSelec);    //elimina la fila seleccionada
        }
    }

    //metodo abrir Proveedores
    private void abrirProveedores(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formProveedores.fxml"));
        try {
            Parent root = loader.load();
            FormProveedoresController formProveedoresController = loader.getController();
            formProveedoresController.setFormComprasController(this);
            Scene scene = new Scene(root);
            Stage fProv = new Stage();
            fProv.setScene(scene);
            fProv.setResizable(false);
            fProv.setTitle("PROVEEDORES");
            fProv.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al abrir Proveedores..."+e.getMessage());
        }
    }

    //metodo abrir Productos
    private void abrirProductos(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formProductos.fxml"));
        try {
            Parent root = loader.load();
            FormProductosController formProductosController = loader.getController();
            formProductosController.setFormComprasController(this);
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

    //metodo limpiar campos
    private void limpiarCampos(){
        txtIdProv.setText("");
        txtTipo.setText("");
        txtNroF.setText("");
        txtRaz.setText("");
        txtCui.setText("");
        txtIdComp.setText("");
        txtIdProd.setText("");
        txtCod.setText("");
        txtDes.setText("");
        txtPre.setText("");
        txtCan.setText("");
        txtImp.setText("");
        txtTot.setText("");
    }

}
