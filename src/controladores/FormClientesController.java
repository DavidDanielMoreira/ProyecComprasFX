package controladores;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import entidades.*;
import accesoADatos.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormClientesController implements Initializable {
    private ObservableList<Cliente> listarClientes;
    private ObservableList<Cliente> filtrarPorDni;
    private ClienteData cData = new ClienteData();
    private Cliente cSelec;
    private FormTicketsController formTicketsController;
    private boolean editar=false;
    private boolean bajas=false;
    @FXML
    private TextField txtIdC;
    @FXML
    private TextField txtApeC;
    @FXML
    private TextField txtNomC;
    @FXML
    private TextField txtDomC;
    @FXML
    private TextField txtDniC;
    @FXML
    private TextField txtTelC;
    @FXML
    private TextField txtCorC;
    @FXML
    private TextArea txtOtrC;
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
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente,Integer> colIdC;
    @FXML
    private TableColumn<Cliente,String> colApeC;
    @FXML
    private TableColumn<Cliente,String> colNomC;
    @FXML
    private TableColumn<Cliente,String> colDomC;
    @FXML
    private TableColumn<Cliente,String> colDniC;
    @FXML
    private TableColumn<Cliente,String> colTelC;
    @FXML
    private TableColumn<Cliente,String> colCorC;
    @FXML
    private TableColumn<Cliente,String> colOtrC;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarClientes = FXCollections.observableArrayList();
        filtrarPorDni = FXCollections.observableArrayList();
        this.colIdC.setCellValueFactory(new PropertyValueFactory<>("idClie"));
        this.colApeC.setCellValueFactory(new PropertyValueFactory<>("apellidoClie"));
        this.colNomC.setCellValueFactory(new PropertyValueFactory<>("nombresClie"));
        this.colDomC.setCellValueFactory(new PropertyValueFactory<>("domicilioClie"));
        this.colDniC.setCellValueFactory(new PropertyValueFactory<>("dniClie"));
        this.colTelC.setCellValueFactory(new PropertyValueFactory<>("telefonoClie"));
        this.colCorC.setCellValueFactory(new PropertyValueFactory<>("correoClie"));
        this.colOtrC.setCellValueFactory(new PropertyValueFactory<>("otrosDatosClie"));
        cargarTabla();
    }
    //metodo que permite exportar a Tickets
    public void setFormTicketsController(FormTicketsController fTicketsController){
        this.formTicketsController = fTicketsController;
    }
    //metodo exportar a Tickets
    private void exportarATicket(){
        formTicketsController.recibirDatosCliente(cSelec);
    }

    @FXML
    private void onBtnGuardar(ActionEvent event){
        if(!txtApeC.getText().isEmpty() && !txtNomC.getText().isEmpty() && !txtDniC.getText().isEmpty()){
            if(editar==false){
                Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeA.setTitle("ALTAS CLIENTES");
                mensajeA.setContentText("¿Confirma el alta del Cliente?");
                mensajeA.setHeaderText(null);
                Optional<ButtonType> opcion = mensajeA.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    insertCliente();
                    cargarTabla();
                    limnpiarCampos();
                }

            }else if(editar==true){
                Alert mensajeE = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeE.setTitle("EDITAR CLIENTES");
                mensajeE.setContentText("¿Confirma la edición del Cliente?");
                mensajeE.setHeaderText(null);
                Optional<ButtonType> opc = mensajeE.showAndWait();
                if(opc.get()==ButtonType.OK) {
                    editCliente();
                    cargarTabla();
                    limnpiarCampos();
                    editar=false;
                }
            }
        }else{
            Alert mensajeI = new Alert(Alert.AlertType.INFORMATION);
            mensajeI.setTitle("MENSAJE");
            mensajeI.setContentText("Campos vacios...");
            mensajeI.setHeaderText(null);
            mensajeI.showAndWait();
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
        limnpiarCampos();
        editar=false;
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onBuscarPorDni(KeyEvent event){
        buscPorDni();
    }
    @FXML
    private void onCtrolDni(KeyEvent event){

    }
    @FXML
    private void onSelecFila(MouseEvent event){
        cSelec = tblClientes.getSelectionModel().getSelectedItem();
        if(FormTicketsController.isOpen && cSelec!=null){
            exportarATicket();
        }else{
            txtIdC.setText(String.valueOf(cSelec.getIdClie()));
            txtApeC.setText(cSelec.getApellidoClie());
            txtNomC.setText(cSelec.getNombresClie());
            txtDomC.setText(cSelec.getDomicilioClie());
            txtDniC.setText(cSelec.getDniClie());
            txtTelC.setText(cSelec.getTelefonoClie());
            txtCorC.setText(cSelec.getCorreoClie());
            txtOtrC.setText(cSelec.getOtrosDatosClie());
        }
    }


    //metodo cargar tabla
    private void cargarTabla(){
        listarClientes = (ObservableList) cData.listarClientes();
        if(!listarClientes.isEmpty()){
            tblClientes.setItems(listarClientes);
        }
    }

    //metodo insertar Cliente
    private void insertCliente(){
        String vApe = txtApeC.getText().trim();
        String vNom = txtNomC.getText().trim();
        String vDom = txtDomC.getText().trim();
        String vDni = txtDniC.getText().trim();
        String vTel = txtTelC.getText().trim();
        String vCor = txtCorC.getText().trim();
        String vOtr = txtOtrC.getText().trim();
        //creo el objecto
        Cliente cliente = new Cliente(vApe,vNom,vDom,vDni,vTel,vCor,vOtr,true);
        cData.insertarCliente(cliente);
    }

    //metodo dar de baja Cliente

    //metodo editar Cliente
    private void editCliente(){
        int vIdC = Integer.parseInt(txtIdC.getText());
        String vApe = txtApeC.getText().trim();
        String vNom = txtNomC.getText().trim();
        String vDom = txtDomC.getText().trim();
        String vDni = txtDniC.getText().trim();
        String vTel = txtTelC.getText().trim();
        String vCor = txtCorC.getText().trim();
        String vOtr = txtOtrC.getText().trim();
        //creo el objecto
        Cliente cliente = new Cliente(vIdC,vApe,vNom,vDom,vDni,vTel,vCor,vOtr,true);
        cData.editarCliente(cliente);
    }

    //metodo buscar por Dni
    private void buscPorDni(){
        listarClientes = (ObservableList) cData.listarClientes();
        String vDni = txtDniC.getText().trim();
        if(!listarClientes.isEmpty()){
            if(vDni.isEmpty()){
                tblClientes.setItems(listarClientes);
            }else{
                filtrarPorDni.clear();
                for(Cliente c: listarClientes){
                    if(c.getDniClie().contains(vDni)){
                        filtrarPorDni.add(c);
                    }
                }
                tblClientes.setItems(filtrarPorDni);
            }
        }
    }

    //metodo limpiar campos
    private void limnpiarCampos(){
        txtIdC.setText("");
        txtApeC.setText("");
        txtNomC.setText("");
        txtDomC.setText("");
        txtDniC.setText("");
        txtTelC.setText("");
        txtCorC.setText("");
        txtOtrC.setText("");
    }

}
