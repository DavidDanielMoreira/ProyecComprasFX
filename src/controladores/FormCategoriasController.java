package controladores;

import entidades.Categoria;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import entidades.*;
import accesoADatos.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormCategoriasController implements Initializable {
    private ObservableList<Categoria> listarCategorias;
    private CategoriaData cData = new CategoriaData();
    private Categoria cSelec;
    private FormProductosController formProductosController;
    private boolean editar = false;
    @FXML
    private TitledPane titledPane;
    @FXML
    private TextField txtIdC;
    @FXML
    private TextField txtNomC;
    @FXML
    private Button btnCargar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnBajas;
    @FXML
    private Button btnCerrar;
    @FXML
    private TableView<Categoria> tblCategorias;
    @FXML
    private TableColumn<Categoria,Integer> colIdC;
    @FXML
    private TableColumn<Categoria,String> colNomC;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titledPane.getStyleClass().add("fondo_titledPane");
        listarCategorias = FXCollections.observableArrayList();
        this.colIdC.setCellValueFactory(new PropertyValueFactory<>("idCate"));
        this.colNomC.setCellValueFactory(new PropertyValueFactory<>("nombreCate"));
        cargarTabla();
    }
    //metodo que permite exportar a Productos
    public void setFormProductosController(FormProductosController fProductosController){
        this.formProductosController =fProductosController;
    }
    //metodo exportar a Producto
    private void exportarAProducto(){
        formProductosController.recibirDatosCategorias(cSelec);
    }

    @FXML
    private void onBtnCargar(ActionEvent event){
        if(!txtNomC.getText().isEmpty()){
            if(editar==false){
                Alert mensajeA = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeA.setTitle("ALTAS CATEGORIAS");
                mensajeA.setContentText("¿Confirma el alta de Categoria?");
                mensajeA.setHeaderText(null);
                Optional<ButtonType> opcion = mensajeA.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    insertCategoria();
                    cargarTabla();
                    limpiarCampos();
                }
            }else if(editar==true){
                Alert mensajeE = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeE.setTitle("EDITAR CATEGORIAS");
                mensajeE.setContentText("¿Confirma la edicióon de la Categoria?");
                mensajeE.setHeaderText(null);
                Optional<ButtonType> opcion = mensajeE.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    editCategoria();
                    cargarTabla();
                    limpiarCampos();
                    editar=false;
                }
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

    }
    @FXML
    private void onSelecFila(MouseEvent event){
        cSelec = tblCategorias.getSelectionModel().getSelectedItem();
        if(FormProductosController.isOpen && cSelec!=null){
            exportarAProducto();
        }else{
            txtIdC.setText(String.valueOf(cSelec.getIdCate()));
            txtNomC.setText(cSelec.getNombreCate());
        }
    }

    //metodo cargar tabla
    private void cargarTabla(){
        listarCategorias = (ObservableList) cData.listarCategorias();
        if(!listarCategorias.isEmpty()){
            tblCategorias.setItems(listarCategorias);
        }
    }

    //metodo insertar Categoria
    private void insertCategoria(){
        String vNomC = txtNomC.getText().trim();
        //creo el objecto
        Categoria categoria = new Categoria(vNomC);
        cData.insertarCategoria(categoria);
    }
    //metodo editar Categoria
    private void editCategoria(){
        int vIdC = Integer.parseInt(txtIdC.getText());
        String vNomC = txtNomC.getText().trim();
        //creo el objecto
        Categoria categoria = new Categoria(vIdC,vNomC);
        cData.editarCategoria(categoria);
    }

    //metodo limpiar campos
    private void limpiarCampos(){
        txtIdC.setText("");
        txtNomC.setText("");
    }
}
