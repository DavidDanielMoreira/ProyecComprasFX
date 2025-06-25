package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class UnidadMedidaData {
    //atributos
    private Connection con ;
    //constructor
    public UnidadMedidaData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar UnidadMedida
    public void insertarUnidadMedida(UnidadMedida uMedida){
        String sql = "Insert into u_medidas (sigla_unid,descripcion_unid) values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uMedida.getSiglaUnid());
            ps.setString(2, uMedida.getDescripcionUnid());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar Unidad Medida..." +e.getMessage());
        }
    }

    //metodo listar UnidadMedida
    public List<UnidadMedida> listarUnidades(){
        UnidadMedida uMedida = null;
        ObservableList<UnidadMedida> lisUnidades = FXCollections.observableArrayList();
        String sql = "Select id_unid,sigla_unid,descripcion_unid from u_medidas";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                uMedida = new UnidadMedida();
                uMedida.setIdUnid(rs.getInt("id_unid"));
                uMedida.setSiglaUnid(rs.getString("sigla_unid"));
                uMedida.setDescripcionUnid(rs.getString("descripcion_unid"));
                lisUnidades.add(uMedida);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar las Unidad Medidas..."+e.getMessage());
        }
        return lisUnidades;
    }
    //metodo buscar por id
    public UnidadMedida buscarPorId(int vIdU){
        UnidadMedida uMedida = null;
        String sql = "Select id_unid,sigla_unid,descripcion_unid from u_medidas where id_unid=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdU);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                uMedida = new UnidadMedida();
                uMedida.setIdUnid(rs.getInt("id_unid"));
                uMedida.setSiglaUnid(rs.getString("sigla_unid"));
                uMedida.setDescripcionUnid(rs.getString("descripcion_unid"));
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar Unidad de Medida por id..."+e.getMessage());
        }
        return uMedida;
    }

}
