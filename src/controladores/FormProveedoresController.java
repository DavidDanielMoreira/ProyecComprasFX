package controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import entidades.*;
import accesoADatos.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormProveedoresController implements Initializable {
    private ObservableList<Proveedor> listarProveedores;
    private ObservableList<Proveedor> filtrarPorCuit;
    private FormProductosController formProductosController;
    private FormComprasController formComprasController;
    private ProveedorData pData = new ProveedorData();
    private Proveedor provSelec;
    private boolean editar = false;
    @FXML
    private TextField txtIdP;
    @FXML
    private TextField txtRazP;
    @FXML
    private TextField txtDomP;
    @FXML
    private TextField txtCuiP;
    @FXML
    private TextField txtTelP;
    @FXML
    private TextField txtCorP;
    @FXML
    private TextField txtConP;
    @FXML
    private TextArea txtOtrP;

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
    private TableView<Proveedor> tblProveedores;
    @FXML
    private TableColumn<Proveedor,Integer> colIdP;
    @FXML
    private TableColumn<Proveedor,String> colRazP;
    @FXML
    private TableColumn<Proveedor,String> colDomP;
    @FXML
    private TableColumn<Proveedor,String> colCuiP;
    @FXML
    private TableColumn<Proveedor,String> colTelP;
    @FXML
    private TableColumn<Proveedor,String> colCorP;
    @FXML
    private TableColumn<Proveedor,String> colConP;
    @FXML
    private TableColumn<Proveedor,String> colOtrP;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarProveedores = FXCollections.observableArrayList();
        filtrarPorCuit = FXCollections.observableArrayList();
        this.colIdP.setCellValueFactory(new PropertyValueFactory<>("idProv"));
        this.colRazP.setCellValueFactory(new PropertyValueFactory<>("razonSocialProv"));
        this.colDomP.setCellValueFactory(new PropertyValueFactory<>("domicilioProv"));
        this.colCuiP.setCellValueFactory(new PropertyValueFactory<>("cuitProv"));
        this.colTelP.setCellValueFactory(new PropertyValueFactory<>("telefonoProv"));
        this.colCorP.setCellValueFactory(new PropertyValueFactory<>("correoProv"));
        this.colConP.setCellValueFactory(new PropertyValueFactory<>("contactoProv"));
        this.colOtrP.setCellValueFactory(new PropertyValueFactory<>("otrosDatosProv"));
        cargarTabla();
    }
    //metodo que permite exportar a Productos
    public void setFormProductosController(FormProductosController fProductosController){
        this.formProductosController = fProductosController;
    }
    //metodo que permite exportar a Compras
    public void setFormComprasController(FormComprasController fComprasController){
        this.formComprasController = fComprasController;
    }
    //metodo exportar a Productos
    private void exportarAProducto(){
        formProductosController.recibirDatosProveedor(provSelec);
    }
    //metodo exportar a Compras
    private void exportarACompras(){
        formComprasController.recibirDatosProveedor(provSelec);
    }

    @FXML
    private void onBtnGuardar(ActionEvent event){
        if(!txtRazP.getText().isEmpty() && !txtCuiP.getText().isEmpty()){
            if(editar==false){
                Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeA.setTitle("ALTAS PROVEEDORES");
                mensajeA.setContentText("¿Confirma el alta del Proveedor?");
                Optional<ButtonType> opcion = mensajeA.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    System.out.println("Altas concretada");
                    insertProveedor();
                    cargarTabla();
                    limpiarCampos();
                }
            }else if(editar==true){
                Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeA.setTitle("EDITAR PROVEEDORES");
                mensajeA.setContentText("¿Confirma la edicion del Proveedor?");
                Optional<ButtonType> opcion = mensajeA.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    System.out.println("Edición concretada");
                    editProveedor();
                    cargarTabla();
                    limpiarCampos();
                }
            }else{
                limpiarCampos();
                editar=false;
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

    }
    @FXML
    private void onBtnCancelar(ActionEvent event){
        limpiarCampos();
        editar=false;
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onSelecFila(MouseEvent event){
        provSelec = tblProveedores.getSelectionModel().getSelectedItem();
        if(FormProductosController.isOpen==true && provSelec!=null){
            exportarAProducto();
        }else if(FormComprasController.isOpen==true && provSelec!=null){
            exportarACompras();
        }else{
            txtIdP.setText(String.valueOf(provSelec.getIdProv()));
            txtRazP.setText(provSelec.getRazonSocialProv());
            txtDomP.setText(provSelec.getDomicilioProv());
            txtCuiP.setText(provSelec.getCuitProv());
            txtTelP.setText(provSelec.getTelefonoProv());
            txtCorP.setText(provSelec.getCorreoProv());
            txtOtrP.setText(provSelec.getOtrosDatosProv());
        }
    }
    @FXML
    private void onBuscarPorCuit(KeyEvent event){
        buscarPorCuit();
    }

    //metodo cargar tabla
    private void cargarTabla(){
        listarProveedores = (ObservableList) pData.listarProveedores();
        if(!listarProveedores.isEmpty()){
            tblProveedores.setItems(listarProveedores);
        }
    }

    //metodo insertar Proveedor
    private void insertProveedor(){
        String vRaz = txtRazP.getText().trim();
        String vDom = txtDomP.getText().trim();
        String vCui = txtCuiP.getText().trim();
        String vTel = txtTelP.getText().trim();
        String vCor = txtCorP.getText().trim();
        String vCon = txtConP.getText().trim();
        String vOtr = txtOtrP.getText().trim();
        //creo el objeto
        Proveedor proveedor = new Proveedor(vRaz,vDom,vCui,vTel,vCor,vCon,vOtr,true);
        pData.insertarProveedor(proveedor);
    }

    //metodo editar Proveedor
    private void editProveedor(){
        int vId = Integer.parseInt(txtIdP.getText());
        String vRaz = txtRazP.getText().trim();
        String vDom = txtDomP.getText().trim();
        String vCui = txtCuiP.getText().trim();
        String vTel = txtTelP.getText().trim();
        String vCor = txtCorP.getText().trim();
        String vCon = txtConP.getText().trim();
        String vOtr = txtOtrP.getText().trim();
        //creo el objeto
        Proveedor proveedor = new Proveedor(vId,vRaz,vDom,vCui,vTel,vCor,vCon,vOtr,true);
        pData.editarProveedor(proveedor);
    }

    //metodo buscar por Cuit
    private void buscarPorCuit(){
        listarProveedores = (ObservableList) pData.listarProveedores();
        String vCui = txtCuiP.getText().trim();
        if(!listarProveedores.isEmpty()){
            if(vCui==null){
                tblProveedores.setItems(listarProveedores);
            }else{
                filtrarPorCuit.clear();
                for(Proveedor p: listarProveedores){
                    if(p.getCuitProv().contains(vCui)){
                        filtrarPorCuit.add(p);
                    }
                }
                tblProveedores.setItems(filtrarPorCuit);
            }
        }
    }

    //metodo limpiar campos
    private void limpiarCampos(){
        txtIdP.setText("");
        txtRazP.setText("");
        txtDomP.setText("");
        txtCuiP.setText("");
        txtTelP.setText("");
        txtCorP.setText("");
        txtConP.setText("");
        txtOtrP.setText("");
    }




}
