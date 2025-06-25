package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class MonedaData {
    //atributos
    private Connection con;

    //constructor
    public MonedaData() {
        this.con = Conexion.getConectar();
    }

    //metodo insertar Monedas
    public void insertarMoneda(Moneda moneda) {
        String sql = "Insert into monedas (simbolo_mone,descripcion_mone) values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, moneda.getSimboloMone());
            ps.setString(2, moneda.getDescripcionMone());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar la Moneda..." + e.getMessage());
        }
    }
    //metodo editar Moneda
    public void editarMoneda(Moneda moneda){
        String sql = "Update monedas set simbolo_mone=?,descripcion_mone=? where id_mone=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, moneda.getSimboloMone());
            ps.setString(2, moneda.getDescripcionMone());
            ps.setInt(3,moneda.getIdMone());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al editar la Moneda..." + e.getMessage());
        }
    }

    //metodo listar Monedas
    public List<Moneda> listarMonedas(){
        Moneda moneda = null;
        ObservableList<Moneda> lisMonedas = FXCollections.observableArrayList();
        String sql = "Select id_mone,simbolo_mone,descripcion_mone from monedas";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                moneda = new Moneda();
                moneda.setIdMone(rs.getInt("id_mone"));
                moneda.setSimboloMone(rs.getString("simbolo_mone"));
                moneda.setDescripcionMone(rs.getString("descripcion_mone"));
                lisMonedas.add(moneda);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar las Monedas..."+e.getMessage());
        }
        return lisMonedas;
    }
    //metodo buscar por id
    public Moneda buscarPorId(int vIdM){
        Moneda moneda = null;
        String sql = "Select id_mone,simbolo_mone,descripcion_mone from monedas where id_mone=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdM);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                moneda = new Moneda();
                moneda.setIdMone(rs.getInt("id_mone"));
                moneda.setSimboloMone(rs.getString("simbolo_mone"));
                moneda.setDescripcionMone(rs.getString("descripcion_mone"));
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar por id la Moneda..."+e.getMessage());
        }
        return moneda;
    }


}
