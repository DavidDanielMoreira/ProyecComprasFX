package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class CategoriaData {
    //atributos
    private Connection con;
    //constructor
    public CategoriaData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar Categoria
    public void insertarCategoria(Categoria categoria){
        String sql = "Insert into categorias (nombre_cate) values (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,categoria.getNombreCate());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar la Categoria..."+e.getMessage());
        }
    }
    //metodo editar Categoria
    public void editarCategoria(Categoria categoria){
        String sql = "Update categorias set nombre_cate=? where id_cate=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,categoria.getNombreCate());
            ps.setInt(2,categoria.getIdCate());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al editar la Categoria..."+e.getMessage());
        }
    }

    //metodo listar Categorias
    public List<Categoria> listarCategorias(){
        Categoria categoria = null;
        ObservableList<Categoria> lisCategorias = FXCollections.observableArrayList();
        String sql = "Select id_cate,nombre_cate from categorias";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                categoria = new Categoria();
                categoria.setIdCate(rs.getInt("id_cate"));
                categoria.setNombreCate(rs.getString("nombre_cate"));
                lisCategorias.add(categoria);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar las Categorias..."+e.getMessage());
        }
        return lisCategorias;
    }

    //metodo buscar por id
    public Categoria buscarPorId(int vIdC){
        Categoria categoria = null;
        String sql = "Select id_cate,nombre_cate from categorias where id_cate=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdC);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                categoria = new Categoria();
                categoria.setIdCate(rs.getInt("id_cate"));
                categoria.setNombreCate(rs.getString("nombre_cate"));
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar Categoria por id..."+e.getMessage());
        }
        return categoria;
    }
}
