package controladores;

import accesoADatos.MonedaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import entidades.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FormMonedasController implements Initializable {
    private ObservableList<Moneda> listarMonedas;
    private FormProductosController formProductosController;
    private MonedaData mData = new MonedaData();
    private Moneda mSelec;
    private boolean editar = false;
    @FXML
    private TextField txtIdM;
    @FXML
    private TextField txtSimM;
    @FXML
    private TextField txtDesM;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnBajas;
    @FXML
    private Button btnCerrar;
    @FXML
    private TableView<Moneda> tblMonedas;
    @FXML
    private TableColumn<Moneda,Integer> colIdM;
    @FXML
    private TableColumn<Moneda,String> colSimM;
    @FXML
    private TableColumn<Moneda,String> colDesM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarMonedas = FXCollections.observableArrayList();
        this.colIdM.setCellValueFactory(new PropertyValueFactory<>("idMone"));
        this.colSimM.setCellValueFactory(new PropertyValueFactory<>("simboloMone"));
        this.colDesM.setCellValueFactory(new PropertyValueFactory<>("descripcionMone"));
        cargarTabla();
    }
    //metodo que permite exportar a Productos
    public void setFormProductosController(FormProductosController fProductosController){
        this.formProductosController = fProductosController;
    }
    //metodo exportar a Producto
    private void exportarAProducto(){
        formProductosController.recibirDatosMoneda(mSelec);
    }

    @FXML
    private void onBtnGuardar(ActionEvent event){
        if(!txtSimM.getText().isEmpty() && !txtDesM.getText().isEmpty()){
            if(editar==false){
                Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeA.setTitle("ALTAS MONEDAS");
                mensajeA.setContentText("¿Confirma el alta de la moneda?");
                mensajeA.setHeaderText(null);
                Optional<ButtonType> opcion = mensajeA.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    System.out.println("Altas concretado");
                    insertMoneda();
                    cargarTabla();
                    limpiarCampos();
                }
            }else if(editar==true){
                Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeA.setTitle("EDITAR MONEDAS");
                mensajeA.setContentText("¿Confirma la edición de la moneda?");
                mensajeA.setHeaderText(null);
                Optional<ButtonType> opcion = mensajeA.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    System.out.println("Edición concretado");
                    editMoneda();
                    cargarTabla();
                    limpiarCampos();
                    editar = false;
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
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onSelecFila(MouseEvent event){
        mSelec = tblMonedas.getSelectionModel().getSelectedItem();
        if(FormProductosController.isOpen==true && mSelec!=null){
            exportarAProducto();
        }else{
            txtIdM.setText(String.valueOf(mSelec.getIdMone()));
            txtSimM.setText(mSelec.getSimboloMone());
            txtDesM.setText(mSelec.getDescripcionMone());
        }
    }

    //metodo cargar tabla
    private void cargarTabla(){
        listarMonedas = (ObservableList) mData.listarMonedas();
        if(!listarMonedas.isEmpty()){
            tblMonedas.setItems(listarMonedas);
        }
    }

    //metodo insertar Moneda
    private void insertMoneda(){
        String vSim = txtSimM.getText().trim();
        String vDes = txtDesM.getText().trim();
        //creo el objecto
        Moneda moneda = new Moneda(vSim,vDes);
        mData.insertarMoneda(moneda);
    }
    //metodo editar Moneda
    private void editMoneda(){
        int vId = Integer.parseInt(txtIdM.getText());
        String vSim = txtSimM.getText().trim();
        String vDes = txtDesM.getText().trim();
        //creo el objecto
        Moneda moneda = new Moneda(vId,vSim,vDes);
        mData.editarMoneda(moneda);
    }

    //metodo limpiar campos
    private void limpiarCampos(){
        txtIdM.setText("");
        txtSimM.setText("");
        txtDesM.setText("");
    }

}
