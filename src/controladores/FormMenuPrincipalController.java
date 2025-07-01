package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FormMenuPrincipalController implements Initializable {
    @FXML
    private MenuItem miSalir;
    @FXML
    private MenuItem miVentas;
    @FXML
    private MenuItem miClientes;
    @FXML
    private MenuItem miPagos;
    @FXML
    private MenuItem miCompras;
    @FXML
    private MenuItem miProveedores;
    @FXML
    private MenuItem miProductos;
    @FXML
    private MenuItem miCategorias;
    @FXML
    private MenuItem miUDeMedidas;
    @FXML
    private MenuItem miListasDePrecios;
    @FXML
    private MenuItem miActuPrecios;
    @FXML
    private MenuItem miMonedas;
    @FXML
    private MenuItem miInventarios;
    @FXML
    private MenuItem miInfCompras;
    @FXML
    private MenuItem miInfVentas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void onSalir(ActionEvent event){

    }
    @FXML
    private void onAbrirVentas(ActionEvent event){
        abrirTickets();
    }
    @FXML
    private void onAbrirClientes(ActionEvent event){
        abrirClientes();
    }
    @FXML
    private void onAbrirPagos(ActionEvent event){

    }
    @FXML
    private void onAbrirCompras(ActionEvent event){
        abrirCompras();
    }
    @FXML
    private void onAbrirProveedores(ActionEvent event){
        abrirProveedores();
    }
    @FXML
    private void onAbrirProductos(ActionEvent event){
        abrirProductos();
    }
    @FXML
    private void onAbrirCategorias(ActionEvent event){
        abrirCategorias();
    }
    @FXML
    private void onAbrirUDeMedidas(ActionEvent event){
        abrirUnidadMedidas();
    }
    @FXML
    private void onAbrirListasDePrecios(ActionEvent event){

    }
    @FXML
    private void onAbrirActualizarPrecios(ActionEvent event){
        abrirActualizarPrecios();
    }
    @FXML
    private void onAbrirUsuarios(ActionEvent event){

    }
    @FXML
    private void onAbrirMonedas(ActionEvent event){
        abrirMonedas();
    }
    @FXML
    private void onAbrirInventarios(ActionEvent event){
        abrirInverntarios();
    }
    @FXML
    private void onAbrirInfCompras(ActionEvent event){
        abrirInfCompras();
    }
    @FXML
    private void onAbrirInfVentas(ActionEvent event){
        abrirInfTickets();
    }

    //metodo abrir Categorias
    private void abrirCategorias(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formCategorias.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
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

    //metodo abrir Clientes
    private void abrirClientes(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formClientes.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
            Stage fClie = new Stage();
            fClie.setScene(scene);
            fClie.setResizable(false);
            fClie.setTitle("CLIENTES");
            fClie.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
           // System.out.println("Error al abrir Clientes..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir Proveedores
    private void abrirProveedores(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formProveedores.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
            Stage fProv = new Stage();
            fProv.setScene(scene);
            fProv.setResizable(false);
            fProv.setTitle("PROVEEDORES");
            fProv.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir UnidadesMedidas
    private void abrirUnidadMedidas(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formUMedidas.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
            Stage fUnid = new Stage();
            fUnid.setScene(scene);
            fUnid.setResizable(false);
            fUnid.setTitle("UNIDADES MEDIDAS");
            fUnid.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }
    //metodo abrir Tickets
    private void abrirTickets(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formTickets.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
            Stage fTick = new Stage();
            fTick.setScene(scene);
            fTick.setResizable(false);
            fTick.initStyle(StageStyle.UNDECORATED);
            fTick.setTitle("TICKETS");
            fTick.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir Productos
    private void abrirProductos(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formProductos.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
            Stage fProd = new Stage();
            fProd.setScene(scene);
            fProd.setResizable(false);
            fProd.initStyle(StageStyle.UNDECORATED);
            fProd.setTitle("PRODUCTOS");
            fProd.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir Monedas
    private void abrirMonedas(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formMonedas.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
            Stage fMone = new Stage();
            fMone.setScene(scene);
            fMone.setResizable(false);
            fMone.initStyle(StageStyle.UNDECORATED);
            fMone.setTitle("MONEDAS");
            fMone.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir Inventarios
    private void abrirInverntarios(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formInventarios.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage fInve = new Stage();
            fInve.setScene(scene);
            fInve.setResizable(false);
            fInve.initStyle(StageStyle.UNDECORATED);
            fInve.setTitle("INVENTARIOS");
            fInve.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir Compras
    private void abrirCompras(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formCompras.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilos.css").toExternalForm());
            Stage fComp = new Stage();
            fComp.setScene(scene);
            fComp.setResizable(false);
            fComp.initStyle(StageStyle.UNDECORATED);
            fComp.setTitle("COMPRAS");
            fComp.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir Actualizar Precios
    private void abrirActualizarPrecios(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formActPrecios.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage fActu = new Stage();
            fActu.setScene(scene);
            fActu.setResizable(false);
            fActu.initStyle(StageStyle.UNDECORATED);
            fActu.setTitle("ACTUALIZACION DE PRECIOS");
            fActu.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir informes Tickets
    private void abrirInfTickets(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formInfTickets.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage fInfT = new Stage();
            fInfT.setScene(scene);
            fInfT.setResizable(false);
            fInfT.initStyle(StageStyle.UNDECORATED);
            fInfT.setTitle("INFORMES DE VENTAS CARGADAS");
            fInfT.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //metodo abrir informes Compras
    private void abrirInfCompras(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/formInfCompras.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage fInfC = new Stage();
            fInfC.setScene(scene);
            fInfC.setResizable(false);
            fInfC.initStyle(StageStyle.UNDECORATED);
            fInfC.setTitle("INFORMES DE COMPRAS CARGADAS");
            fInfC.show();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            //System.out.println("Error al abrir Proveedores..."+e.getMessage());
            e.printStackTrace();
        }
    }

}
