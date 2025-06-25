package controladores;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormInventariosController implements Initializable {
    public static boolean isOpen = false;
    private ObservableList<Producto> cargarLista;
    private ProductoData pData = new ProductoData();
    private Producto pSelec;
    private boolean entrada = false;
    private boolean salida = false;

    @FXML
    private TextField txtIdP;
    @FXML
    private TextField txtCodP;
    @FXML
    private TextField txtDesP;
    @FXML
    private TextField txtCanP;
    @FXML
    private TextField txtStoP;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnProductos;
    @FXML
    private RadioButton rbEntrada;
    @FXML
    private RadioButton rbSalida;
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto,Integer> colIdP;
    @FXML
    private TableColumn<Producto,Integer> colCodP;
    @FXML
    private TableColumn<Producto,Integer> colDesP;
    @FXML
    private TableColumn<Producto,Integer> colStoP;
    @FXML
    private TableColumn<Producto,Integer> colCanP;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isOpen = true;
        cargarLista = FXCollections.observableArrayList();
        this.colIdP.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        this.colCodP.setCellValueFactory(new PropertyValueFactory<>("codigoProd"));
        this.colDesP.setCellValueFactory(new PropertyValueFactory<>("descripcionCortaProd"));
        this.colStoP.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.colCanP.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        btnProductos.setDisable(true); //botom Productos inhabilitado
    }

    //metodo recibir datos Productos
    public void recibirDatosProducto(Producto producto){
        txtIdP.setText(String.valueOf(producto.getIdProd()));
        txtCodP.setText(producto.getCodigoProd());
        txtDesP.setText(producto.getDescripcionCortaProd());
        txtStoP.setText(String.valueOf(producto.getStock()));
    }

    @FXML
    private void onBtnAceptar(ActionEvent event){
            if(!cargarLista.isEmpty()){
                if(entrada==true){
                    for(Producto p: cargarLista){
                        int vIdP = p.getIdProd();
                        System.out.println("IdP: "+vIdP);
                        System.out.println("Stock: "+p.getStock());
                        System.out.println("Cantidad: "+p.getCantidad());
                        double vStoN = p.getStock()+p.getCantidad();
                        System.out.println("Stock nuevo: "+vStoN);
                        //actualizo el stock
                        pData.actualizarStock(vStoN,vIdP);
                    }
                }else if(salida==true){
                    for(Producto p: cargarLista){
                        int vIdP = p.getIdProd();
                        System.out.println("IdP: "+vIdP);
                        System.out.println("Stock: "+p.getStock());
                        System.out.println("Cantidad: "+p.getCantidad());
                        double vStoN = p.getStock()-p.getCantidad();
                        System.out.println("Stock nuevo: "+vStoN);
                        //actualizo el stock
                        pData.actualizarStock(vStoN,vIdP);
                    }
                }else{
                    limpiarCampos();
                    entrada = false;
                    salida =false;
                    System.out.println("No se concreto ninguna acción....");
                }
            }else{
                System.out.println("Tabla vacia...");
            }
    }

    @FXML
    private void onBtnAgregar(ActionEvent event){
        btnProductos.setDisable(false); //boton Productos habilitados
    }
    @FXML
    private void onBtnInsertar(ActionEvent event){
        insertProductoATabla();
    }
    @FXML
    private void onBtnEliminar(ActionEvent event){
        eliminProductoDeLaTabla();
        limpiarCampos();
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
        isOpen = false;
    }
    @FXML
    private void onBtnProductos(ActionEvent event){
        abrirProductos();
    }
    @FXML
    private void onRbEntrada(MouseEvent event){
        entrada = true;
    }
    @FXML
    private void onRbSalida(MouseEvent event){
        salida = true;
    }
    @FXML
    private void onSelecFila(MouseEvent event){

    }

    //metodo insertar Producto a tabla
    private void insertProductoATabla(){
        int vIdP = Integer.parseInt(txtIdP.getText());
        String vCod = txtCodP.getText().trim();
        String vDes = txtDesP.getText().trim();
        double vSto = Double.parseDouble(txtStoP.getText());
        double vCan = Double.parseDouble(txtCanP.getText());
        //creo el Objecto
        Producto p = new Producto();
        p.setIdProd(vIdP);
        p.setCodigoProd(vCod);
        p.setDescripcionCortaProd(vDes);
        p.setStock(vSto);
        p.setCantidad(vCan);
        cargarLista.add(p);
        //inserto el Producto a la tabla
        tblProductos.getItems().add(p);
    }

    //metodo eliminar Producto de la tabla
    private void eliminProductoDeLaTabla(){
        int fSelec=0;
        if(fSelec!=-1){
            tblProductos.getItems().remove(fSelec);
        }
    }

    //metodo abrir Productos
    private void abrirProductos(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formProductos.fxml"));
        try {
            Parent root = loader.load();
            FormProductosController formProductosController = loader.getController();
            formProductosController.setFormInventariosController(this);
            Scene scene = new Scene(root);
            Stage fProd = new Stage();
            fProd.setScene(scene);
            fProd.setResizable(false);
            fProd.initStyle(StageStyle.UNDECORATED);
            fProd.setTitle("PRODUCTOS");
            fProd.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al abrir Productos..."+e.getMessage());
        }
    }

    //metodo limpiar campos
    private void limpiarCampos(){
        txtIdP.setText("");
        txtCodP.setText("");
        txtDesP.setText("");
        txtCanP.setText("");
        txtStoP.setText("");
    }

}
