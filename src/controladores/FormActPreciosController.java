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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.ResourceBundle;

public class FormActPreciosController implements Initializable {
    private ObservableList<Producto> listarProductos;       //lista todos los Productos cargados
    private ObservableList<Categoria> listarCategorias;     //lista todas las Categorias cargadas
    private ObservableList<Proveedor> listarProveedores;    //lista los Proveedores cargados
    private ObservableList<Producto> filtrarPorCategorias;  //lista todos los Productos segun Categoria seleccionada
    private ObservableList<Producto> filtrarPorProveedores; //lista todos los Productos segun Proveedor seleccionado
    private ObservableList<Producto> listaPreciosActu;
    private CategoriaData cateData = new CategoriaData();
    private ProveedorData provData = new ProveedorData();
    private ProductoData prodData = new ProductoData();
    private Proveedor provSelec;
    private Categoria cateSelec;
    private boolean cate = false;
    private boolean prov = false; //se utiliza para que la actualilizacion de precios sea por Proveedor
    @FXML
    private ComboBox<Categoria> cmbCategorias;
    @FXML
    private ComboBox<Proveedor> cmbProveedores;
    @FXML
    private RadioButton rbTodos;
    @FXML
    private TextField txtMar;
    @FXML
    private Button btnVerLista;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnAnular;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnCerrar;
    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto, Integer> colIdP;
    @FXML
    private TableColumn<Producto, String> colCodP;
    @FXML
    private TableColumn<Producto, Integer> colDesP;
    @FXML
    private TableColumn<Producto, Integer> colPreP;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            listarProductos = FXCollections.observableArrayList();
            listarCategorias = FXCollections.observableArrayList();
            listarProveedores = FXCollections.observableArrayList();
            filtrarPorCategorias = FXCollections.observableArrayList();
            filtrarPorProveedores = FXCollections.observableArrayList();
            listaPreciosActu = FXCollections.observableArrayList();
            this.colIdP.setCellValueFactory(new PropertyValueFactory<>("idProd"));
            this.colCodP.setCellValueFactory(new PropertyValueFactory<>("codigoProd"));
            this.colDesP.setCellValueFactory(new PropertyValueFactory<>("descripcionCortaProd"));
            this.colPreP.setCellValueFactory(new PropertyValueFactory<>("precioV"));
            cargarComboCate();
            cargarComboProv();
    }
    @FXML
    private void onBtnVerLista(ActionEvent event){
        if(!txtMar.getText().isEmpty()){
            actualPrecios();
        }else{
            System.out.println("Ingrese el margen");
            txtMar.requestFocus();
        }
    }
    @FXML
    private void onBtnConfirmar(ActionEvent event){
            if(!listaPreciosActu.isEmpty()){
                Alert mensajeC = new Alert(Alert.AlertType.CONFIRMATION);
                mensajeC.setTitle("ACTUALIZACION PRECIOS");
                mensajeC.setContentText("¿Confirma la actualización de precios?");
                mensajeC.setHeaderText(null);
                Optional<ButtonType> opcion = mensajeC.showAndWait();
                if(opcion.get()==ButtonType.OK){
                    confirmarActPrecios();
                    listaPreciosActu.clear();
                }else{
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                    mensaje.setTitle("ACTUALIZACION PRECIOS");
                    mensajeC.setContentText("No se concreto la actualización de precios");
                    mensajeC.setHeaderText(null);
                    listaPreciosActu.clear();
                }
            }
    }
    @FXML
    private void onBtnAnular(ActionEvent event){

    }
    @FXML
    private void onBtnCancelar(ActionEvent event){
        txtMar.setText("");
        listarCategorias.clear();
        listarProveedores.clear();
        listaPreciosActu.clear();
        tblProductos.getItems().clear();
        cmbCategorias.getSelectionModel().clearSelection();     //reinicio el combo
        cmbProveedores.getSelectionModel().clearSelection();
    }
    @FXML
    private void onBtnCerrar(ActionEvent event){
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActPrecios(MouseEvent event){

    }
    @FXML
    private void onSelecCate(MouseEvent event){
        cateSelec = cmbCategorias.getSelectionModel().getSelectedItem();
        listarCategorias.clear();
        cate = true;
        prov = false;
        if(cateSelec!=null){
            int vIdC = cateSelec.getIdCate();
            filtrarPorCategorias = (ObservableList) prodData.listarPorCategorias(vIdC);
            tblProductos.setItems(filtrarPorCategorias);
        }
    }
    @FXML
    private void onSelecProve(MouseEvent event){
            //selecciono el Proveedor y muestra en la tabla los productos de ese Proveedor
        provSelec = cmbProveedores.getSelectionModel().getSelectedItem();
        filtrarPorProveedores.clear();
        prov = true;
        cate = false;
        if(provSelec!=null){
            int vIdProv = provSelec.getIdProv();
            filtrarPorProveedores = (ObservableList<Producto>) prodData.listarPorProveedor(vIdProv);
            tblProductos.setItems(filtrarPorProveedores);
        }
    }

    //metodo cargar combo Categorias
    private void cargarComboCate(){
        listarCategorias = (ObservableList) cateData.listarCategorias();

        if(!listarCategorias.isEmpty()){
            cmbCategorias.getItems().addAll(listarCategorias);
            cmbCategorias.setConverter(new StringConverter<Categoria>() {
                @Override
                public String toString(Categoria categoria) {
                    if(categoria != null){
                        return categoria.getNombreCate();
                    }else{
                        return "";
                    }
                }

                @Override
                public Categoria fromString(String s) {
                    return null;
                }
            });
        }
    }

    //cargar combo Proveedores
    private void cargarComboProv(){
        listarProveedores = (ObservableList) provData.listarProveedores();
        if(!listarProveedores.isEmpty()){
            cmbProveedores.getItems().addAll(listarProveedores);
            cmbProveedores.setConverter(new StringConverter<Proveedor>() {
                @Override
                public String toString(Proveedor proveedor) {
                    if(proveedor!=null){
                        return proveedor.getRazonSocialProv();
                    }else{
                        return "";
                    }
                }

                @Override
                public Proveedor fromString(String s) {
                    return null;
                }
            });
        }
    }


    //metodo actualizar precios
    private void actualPrecios(){
        double vPreV;
        double vMar = Double.parseDouble(txtMar.getText());
        if(!filtrarPorCategorias.isEmpty()){
            listaPreciosActu.clear();
            for(Producto p: filtrarPorCategorias){
                Producto producto = new Producto();
                producto.setIdProd(p.getIdProd());
                producto.setCodigoProd(p.getCodigoProd());
                producto.setDescripcionCortaProd(p.getDescripcionCortaProd());
                vPreV = (p.getPrecioV() + (p.getPrecioV()*vMar));
                producto.setPrecioV(vPreV);
                listaPreciosActu.add(producto);
            }
            tblProductos.setItems(listaPreciosActu);
            tblProductos.refresh();
        }else if(!filtrarPorProveedores.isEmpty()){
            listaPreciosActu.clear();
            for(Producto p: filtrarPorProveedores){
                Producto producto = new Producto();
                producto.setIdProd(p.getIdProd());
                producto.setCodigoProd(p.getCodigoProd());
                producto.setDescripcionCortaProd(p.getDescripcionCortaProd());
                vPreV = (p.getPrecioV() + (p.getPrecioV()*vMar));
                producto.setPrecioV(vPreV);
                listaPreciosActu.add(producto);
            }
            tblProductos.setItems(listaPreciosActu);
            tblProductos.refresh();
        }


    }

    //metodo actualizar el Precio original
    private void confirmarActPrecios(){
        if(!listaPreciosActu.isEmpty() && cate==true && prov==false){
            System.out.println(listaPreciosActu);

            for(Producto p: listaPreciosActu){
                System.out.println(p.getIdProd());
                System.out.println(p.getCodigoProd());
                System.out.println(p.getDescripcionCortaProd());
                System.out.println(p.getPrecioV());
                int vIdC = cateSelec.getIdCate();
                int vIdP = p.getIdProd();
                double vPreN = p.getPrecioV();

                //actualiza el precio a la base de datos
                prodData.actualizarPreciosPorCate(vPreN,vIdP,vIdC);
            }
        }else if(!listaPreciosActu.isEmpty() && prov==true && cate==false){
            for(Producto p: listaPreciosActu) {
                System.out.println(p.getIdProd());
                System.out.println(p.getCodigoProd());
                System.out.println(p.getDescripcionCortaProd());
                System.out.println(p.getPrecioV());
                int vIdProv = provSelec.getIdProv();
                int vIdP = p.getIdProd();
                double vPreN = p.getPrecioV();
                //actualiza el precio a la base de datos
                prodData.actualizarPreciosPorProve(vPreN, vIdP, vIdProv);
            }
        }
    }

}
