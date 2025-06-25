package controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import entidades.*;
import accesoADatos.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FormUMedidasController implements Initializable {
    private ObservableList<UnidadMedida> listarUnidades;
    private FormProductosController formProductosController;
    private UnidadMedidaData umData = new UnidadMedidaData();
    private UnidadMedida umSelec;
    private boolean editar = false;
    private boolean bajas = false;

    @FXML
    private TextField txtIdU;
    @FXML
    private TextField txtSigU;
    @FXML
    private TextField txtDesU;
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
    private TableView<UnidadMedida> tblUMedidas;
    @FXML
    private TableColumn<UnidadMedida,Integer> colIdU;
    @FXML
    private TableColumn<UnidadMedida,String> colSigU;
    @FXML
    private TableColumn<UnidadMedida,String> colDesU;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarUnidades = FXCollections.observableArrayList();
        this.colIdU.setCellValueFactory(new PropertyValueFactory<>("idUnid"));
        this.colSigU.setCellValueFactory(new PropertyValueFactory<>("siglaUnid"));
        this.colDesU.setCellValueFactory(new PropertyValueFactory<>("descripcionUnid"));
        cargarTabla();
    }

    //metodo que permite exportar a Productos
    public void setFormProductosController(FormProductosController fProductosController){
        this.formProductosController = fProductosController;
    }
    //metodo exportar a Productos
    private void exportarAProductos(){
        formProductosController.recibirDatosUnidadMedidas(umSelec);
    }

    @FXML
    private void onBtnGuardar(ActionEvent event){

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

    }
    @FXML
    private void onSelecFila(MouseEvent event){
        umSelec = tblUMedidas.getSelectionModel().getSelectedItem();
        if(FormProductosController.isOpen==true && umSelec!=null){
            exportarAProductos();
        }else{
            txtIdU.setText(String.valueOf(umSelec.getIdUnid()));
            txtSigU.setText(umSelec.getSiglaUnid());
            txtDesU.setText(umSelec.getDescripcionUnid());
        }
    }

    //metodo cargar tabla
    private void cargarTabla(){
        listarUnidades = (ObservableList) umData.listarUnidades();
        if(!listarUnidades.isEmpty()){
            tblUMedidas.setItems(listarUnidades);
        }
    }

    //metodo crear UnidadMedida
    private void inserUindadMedida(){
        String vSig = txtSigU.getText().trim();
        String vDes = txtDesU.getText().trim();
        //creo el objecto
        UnidadMedida uMedida = new UnidadMedida(vSig,vDes);
        umData.insertarUnidadMedida(uMedida);
    }
}
