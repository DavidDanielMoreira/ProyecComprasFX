package controladores;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import entidades.*;
import accesoADatos.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormProductosController implements Initializable {
    private ObservableList<Producto> listarProductos;
    private ObservableList<Producto> filtrarPorCodigo;
    public static boolean isOpen=false;
    private FormTicketsController formTicketsController;
    private FormComprasController formComprasController;
    private FormInventariosController formInventariosController;
    private ProductoData prodData = new ProductoData();
    private CategoriaData cateData = new CategoriaData();
    private MonedaData moneData = new MonedaData();
    private ProveedorData provData = new ProveedorData();
    private UnidadMedidaData umData = new UnidadMedidaData();
    private Producto prodSelec;
    private Moneda moneSelec;
    private Categoria cateSelec;
    private Proveedor provSelec;
    private UnidadMedida umSelec;
    private boolean editar=false;
    private boolean bajas=false;

    @FXML
    private TextField txtIdP;
    @FXML
    private TextField txtIdC;
    @FXML
    private TextField txtIdU;
    @FXML
    private TextField txtCodP;
    @FXML
    private TextField txtDesP;
    @FXML
    private TextField txtCodBP;
    @FXML
    private TextField txtNomC;
    @FXML
    private TextField txtIdProv;
    @FXML
    private TextField txtIdM;
    @FXML
    private TextField txtSigU;
    @FXML
    private TextField txtRazProv;
    @FXML
    private TextField txtTipM;
    @FXML
    private TextField txtPreC;
    @FXML
    private TextField txtMar;
    @FXML
    private TextField txtIva;
    @FXML
    private TextField txtPreV;
    @FXML
    private TextField txtStoIn;
    @FXML
    private TextField txtSto;
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
    private Button btnCategorias;
    @FXML
    private Button btnUnidadMedidas;
    @FXML
    private Button btnProveedores;
    @FXML
    private Button btnMonedas;
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto,Integer> colIdP;
    @FXML
    private TableColumn<Producto,String> colCodP;
    @FXML
    private TableColumn<Producto,String> colDesP;
    @FXML
    private TableColumn<Producto,String> colCodBP;
    @FXML
    private TableColumn<Producto,Double> colPreCP;
    @FXML
    private TableColumn<Producto,Double> colMarP;
    @FXML
    private TableColumn<Producto,Double> colIvaP;
    @FXML
    private TableColumn<Producto,Double> colPreVP;
    @FXML
    private TableColumn<Producto,Double> colStoInP;
    @FXML
    private TableColumn<Producto,Double> colStoP;
    @FXML
    private TableColumn<Producto,Double> colCanP;
    @FXML
    private TableColumn<Producto,Integer> colIdM;
    @FXML
    private TableColumn<Producto,Integer> colIdC;
    @FXML
    private TableColumn<Producto,Integer> colIdPR;
    @FXML
    private TableColumn<Producto,Integer> colIdU;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isOpen=true;
        listarProductos = FXCollections.observableArrayList();
        filtrarPorCodigo = FXCollections.observableArrayList();
        this.colIdP.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        this.colCodP.setCellValueFactory(new PropertyValueFactory<>("codigoProd"));
        this.colDesP.setCellValueFactory(new PropertyValueFactory<>("descripcionCortaProd"));
        this.colCodBP.setCellValueFactory(new PropertyValueFactory<>("codigoBarraProd"));
        this.colPreCP.setCellValueFactory(new PropertyValueFactory<>("precioC"));
        this.colMarP.setCellValueFactory(new PropertyValueFactory<>("margen"));
        this.colIvaP.setCellValueFactory(new PropertyValueFactory<>("iva"));
        this.colPreVP.setCellValueFactory(new PropertyValueFactory<>("precioV"));
        this.colStoInP.setCellValueFactory(new PropertyValueFactory<>("stockIn"));
        this.colStoP.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.colCanP.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.colIdM.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMoneda().getIdMone()));
        this.colIdC.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategoria().getIdCate()));
        this.colIdPR.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProveedor().getIdProv()));
        this.colIdU.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUnidadMedida().getIdUnid()));
        cargarTabla();
    }
    //metodo que permite exportar a Tickets
    public void setFormTicketsController(FormTicketsController fTicketsController){
        this.formTicketsController = fTicketsController;
    }
    //metodo que permite exportar a Productos
    public void setFormComprasController(FormComprasController fComprasController){
        this.formComprasController = fComprasController;
    }
    //metodo que permite exportar a Inventarios
    public void setFormInventariosController(FormInventariosController fInventariosController){
        this.formInventariosController = fInventariosController;
    }
    //metodo exportar datos a Tickets
    private void exportarATicket(){
        formTicketsController.recibirDatosProducto(prodSelec);
    }
    //metodo exportar a Compras
    private void exportarACompras(){
        formComprasController.recibirDatosProducto(prodSelec);
    }
    //metodo exportar a Inventarios
    private void exportarAInventarios(){
        formInventariosController.recibirDatosProducto(prodSelec);
    }

    //metodo recibir datos Cartegoria
    public void recibirDatosCategorias(Categoria categoria){
        txtIdC.setText(String.valueOf(categoria.getIdCate()));
        txtNomC.setText(categoria.getNombreCate());
    }
    //recibir datos UnidadMedidas
    public void recibirDatosUnidadMedidas(UnidadMedida uMedida){
        txtIdU.setText(String.valueOf(uMedida.getIdUnid()));
        txtSigU.setText(uMedida.getSiglaUnid());
    }
    //recibir datos Proveedor
    public void recibirDatosProveedor(Proveedor proveedor){
        txtIdProv.setText(String.valueOf(proveedor.getIdProv()));
        txtRazProv.setText(proveedor.getRazonSocialProv());
    }

    //metodo recibir datos Monedas
    public void recibirDatosMoneda(Moneda moneda){
        txtIdM.setText(String.valueOf(moneda.getIdMone()));
        txtTipM.setText(moneda.getSimboloMone());
    }

    @FXML
    private void onBtnGuardar(ActionEvent event){
        if(!txtCodP.getText().isEmpty() && !txtDesP.getText().isEmpty() && !txtIdC.getText().isEmpty()){
            if(editar==false && bajas==false){
                Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeA.setTitle("ALTAS PRODUCTOS");
                mensajeA.setContentText("¿Confirma el alta del Producto?");
                mensajeA.setHeaderText(null);
                Optional<ButtonType> opcion = mensajeA.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    insertProducto();
                    cargarTabla();
                    limpiarCampos();
                }
            }else if(editar==true && bajas==false){
                System.out.println("Edición concretada");
                editProducto();
                cargarTabla();
                limpiarCampos();
                editar=false;
                bajas=false;
            }else if(editar==false && bajas==true){
                System.out.println("Bajas concretado");
                limpiarCampos();
                editar=false;
                bajas=false;
            }else{
                limpiarCampos();
                editar=false;
                bajas=false;
            }
        }else{
            System.out.println("Campos vacios...");
        }
    }
    @FXML
    private void onBtnEditar(ActionEvent event){
        editar=true;
    }
    @FXML
    private void onBtnBajas(ActionEvent event){
        bajas=true;
    }
    @FXML
    private void onBtnCancelar(ActionEvent event){
        editar=false;
        bajas=false;
        limpiarCampos();
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
        isOpen = false;
    }
    @FXML
    private void onBtnCategorias(ActionEvent event){
        abrirCategorias();
    }
    @FXML
    private void onBtnUnidadMedidas(ActionEvent event){
        abrirUnidadMedidas();
    }
    @FXML
    private void onBtnProveedores(ActionEvent event){
        abrirProveedores();
    }
    @FXML
    private void onBtnMonedas(ActionEvent event){
        abrirMonedas();
    }
    @FXML
    private void onSelecFila(MouseEvent event){
        prodSelec = tblProductos.getSelectionModel().getSelectedItem();
        if(FormTicketsController.isOpen==true && prodSelec!=null){
           exportarATicket();
        }else if(FormComprasController.isOpen==true && prodSelec!=null){
           exportarACompras();
        }else if(FormInventariosController.isOpen==true && prodSelec!=null){
            exportarAInventarios();
        }else{
            txtIdP.setText(String.valueOf(prodSelec.getIdProd()));
            txtCodP.setText(prodSelec.getCodigoProd());
            txtDesP.setText(prodSelec.getDescripcionCortaProd());
            txtCodBP.setText(prodSelec.getCodigoBarraProd());
            txtPreC.setText(String.valueOf(prodSelec.getPrecioC()));
            txtMar.setText(String.valueOf(prodSelec.getMargen()));
            txtIdM.setText(String.valueOf(prodSelec.getMargen()));
            txtIva.setText(String.valueOf(prodSelec.getIva()));
            txtPreV.setText(String.valueOf(prodSelec.getPrecioV()));
            txtStoIn.setText(String.valueOf(prodSelec.getStockI()));
            txtSto.setText(String.valueOf(prodSelec.getStock()));
            txtIdC.setText(String.valueOf(prodSelec.getCategoria().getIdCate()));
            txtNomC.setText(prodSelec.getCategoria().getNombreCate());
            txtIdU.setText(String.valueOf(prodSelec.getUnidadMedida().getIdUnid()));
            txtSigU.setText(prodSelec.getUnidadMedida().getSiglaUnid());
            txtIdProv.setText(String.valueOf(prodSelec.getProveedor().getIdProv()));
            txtRazProv.setText(prodSelec.getProveedor().getRazonSocialProv());
            txtIdM.setText(String.valueOf(prodSelec.getMoneda().getIdMone()));
            txtTipM.setText(prodSelec.getMoneda().getSimboloMone());
        }
    }
    @FXML
    private void onBuscarPorCodigo(KeyEvent event){
        listarProductos = (ObservableList) prodData.listarProductos();
        String vCod = txtCodP.getText().trim();
        if(!listarProductos.isEmpty()){
            if(vCod==null){
                tblProductos.setItems(listarProductos);
            }else{
                filtrarPorCodigo.clear();
                for(Producto p: listarProductos){
                    if(p.getCodigoProd().contains(vCod)){
                        filtrarPorCodigo.add(p);
                    }
                }
                tblProductos.setItems(filtrarPorCodigo);
            }
        }
    }

    //metodo cargar tabla
    private void cargarTabla(){
        listarProductos = (ObservableList) prodData.listarProductos();
        if(!listarProductos.isEmpty()){
            tblProductos.setItems(listarProductos);
        }
    }
    //metodo insertar Producto
    private void insertProducto(){
        String vCod = txtCodP.getText().trim();
        String vDes = txtDesP.getText().trim();
        String vCodB = txtCodBP.getText().trim();
        double vPreC = Double.parseDouble(txtPreC.getText());
        double vMar = Double.parseDouble(txtMar.getText());
        double vIva = Double.parseDouble(txtIva.getText());
        double vPreV = Double.parseDouble(txtPreV.getText());
        double vStoI = Double.parseDouble(txtStoIn.getText());
        double vSto = Double.parseDouble(txtSto.getText());
        double vCan = 0;
        int vIdM = Integer.parseInt(txtIdM.getText());
        int vIdC = Integer.parseInt(txtIdC.getText());
        int vIdProv = Integer.parseInt(txtIdProv.getText());
        int vIdU = Integer.parseInt(txtIdU.getText());
        //busco la Moneda
        moneSelec = moneData.buscarPorId(vIdM);
        //busco la Categoria
        cateSelec = cateData.buscarPorId(vIdC);
        //busco el Proveedor
        provSelec = provData.buscarPorId(vIdProv);
        //busco la UnidadMedida
        umSelec = umData.buscarPorId(vIdU);
        //creo el objecto
        Producto producto = new Producto(vCod,vDes,vCodB,vPreC,vMar,vIva,vPreV,vStoI,vSto,vCan,moneSelec,cateSelec,provSelec,umSelec,true);
        prodData.insertarProducto(producto);
    }

    //metodo editar Producto
    private void editProducto(){
        int vIdProd = Integer.parseInt(txtIdP.getText());
        String vCod = txtCodP.getText().trim();
        String vDes = txtDesP.getText().trim();
        String vCodB = txtCodBP.getText().trim();
        double vPreC = Double.parseDouble(txtPreC.getText());
        double vMar = Double.parseDouble(txtMar.getText());
        double vIva = Double.parseDouble(txtIva.getText());
        double vPreV = Double.parseDouble(txtPreV.getText());
        double vStoI = Double.parseDouble(txtStoIn.getText());
        double vSto = Double.parseDouble(txtSto.getText());
        double vCan = 0;
        int vIdM = Integer.parseInt(txtIdM.getText());
        int vIdC = Integer.parseInt(txtIdC.getText());
        int vIdProv = Integer.parseInt(txtIdProv.getText());
        int vIdU = Integer.parseInt(txtIdU.getText());
        //busco la Moneda
        moneSelec = moneData.buscarPorId(vIdM);
        //busco la Categoria
        cateSelec = cateData.buscarPorId(vIdC);
        //busco el Proveedor
        provSelec = provData.buscarPorId(vIdProv);
        //busco la UnidadMedida
        umSelec = umData.buscarPorId(vIdU);
        //creo el objecto
        Producto producto = new Producto(vIdProd,vCod,vDes,vCodB,vPreC,vMar,vIva,vPreV,vStoI,vSto,vCan,moneSelec,cateSelec,provSelec,umSelec,true);
        prodData.editarProducto(producto);
    }

    //metodo buscar por Codigo



    //metodo abrir Categorias
    private void abrirCategorias(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formCategorias.fxml"));
        try {
            Parent root = loader.load();
            FormCategoriasController fCategoriasController = loader.getController();
            fCategoriasController.setFormProductosController(this);
            Scene scene = new Scene(root);
            Stage fCate = new Stage();
            fCate.setScene(scene);
            fCate.setResizable(false);
            fCate.setTitle("CATEGORIAS");
            fCate.show();

        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al abrir Categorias..."+e.getMessage());
        }
    }

    //metodo abrir UnidadMedida
    private void abrirUnidadMedidas(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formUMedidas.fxml"));
        try {
            Parent root = loader.load();
            FormUMedidasController fUMedidasController = loader.getController();
            fUMedidasController.setFormProductosController(this);
            Scene scene = new Scene(root);
            Stage fUnid = new Stage();
            fUnid.setScene(scene);
            fUnid.setResizable(false);
            fUnid.setTitle("UNIDADES DE MEDIDAS");
            fUnid.show();

        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al abrir Unidades..."+e.getMessage());
        }
    }

    //metodo abrir Proveedores
    private void abrirProveedores(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formProveedores.fxml"));
        try {
            Parent root = loader.load();
            FormProveedoresController formProveedoresController = loader.getController();
            formProveedoresController.setFormProductosController(this);
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

    //metodo abrir Monedas
    private void abrirMonedas(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formMonedas.fxml"));
        try {
            Parent root = loader.load();
            FormMonedasController fMonedasController = loader.getController();
            fMonedasController.setFormProductosController(this);
            Scene scene = new Scene(root);
            Stage fMone = new Stage();
            fMone.setScene(scene);
            fMone.setResizable(false);
            fMone.setTitle("MONEDAS");
            fMone.show();

        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al abrir Monedas..."+e.getMessage());
        }
    }

        //metodo limnpiar campos
    private void limpiarCampos(){
        txtIdP.setText("");
        txtCodP.setText("");
        txtDesP.setText("");
        txtCodBP.setText("");
        txtPreC.setText("");
        txtMar.setText("");
        txtIva.setText("");
        txtPreV.setText("");
        txtStoIn.setText("");
        txtSto.setText("");

        txtIdM.setText("");
        txtIdC.setText("");
        txtIdProv.setText("");
        txtIdU.setText("");
    }

}
