package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class ProveedorData {
    //atribtutos
    private Connection con;
    //constructor
    public ProveedorData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar Proveedor
    public void insertarProveedor(Proveedor proveedor){
        String sql = "Insert into proveedores (razon_social_prov,domicilio_prov,cuit_prov,telefono_prov,correo_prov,contacto_prov,otros_datos_prov,estado_prov) values (?,?,?,?,?,?,?,1)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proveedor.getRazonSocialProv());
            ps.setString(2, proveedor.getDomicilioProv());
            ps.setString(3, proveedor.getCuitProv());
            ps.setString(4, proveedor.getTelefonoProv());
            ps.setString(5, proveedor.getCorreoProv());
            ps.setString(6, proveedor.getContactoProv());
            ps.setString(7, proveedor.getOtrosDatosProv());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar el Proveedor..."+e.getMessage());
        }
    }

    //metodo editar Proveedor
    public void editarProveedor(Proveedor proveedor){
        String sql = "Update proveedores set razon_social_prov=?,domicilio_prov=?,cuit_prov=?,telefono_prov=?,correo_prov=?,contacto_prov=?,otros_datos_prov=? where id_prov=? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, proveedor.getRazonSocialProv());
            ps.setString(2, proveedor.getDomicilioProv());
            ps.setString(3, proveedor.getCuitProv());
            ps.setString(4, proveedor.getTelefonoProv());
            ps.setString(5, proveedor.getCorreoProv());
            ps.setString(6, proveedor.getContactoProv());
            ps.setString(7, proveedor.getOtrosDatosProv());
            ps.setInt(8,proveedor.getIdProv());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al editar el Proveedor..."+e.getMessage());
        }
    }

    //metodo listar Proveedores
    public List<Proveedor> listarProveedores(){
        Proveedor proveedor = null;
        ObservableList<Proveedor> lisProveedores = FXCollections.observableArrayList();
        String sql = "Select id_prov,razon_social_prov,domicilio_prov,cuit_prov,telefono_prov,correo_prov,contacto_prov,otros_datos_prov,estado_prov from proveedores where estado_prov=1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                proveedor = new Proveedor();
                proveedor.setIdProv(rs.getInt("id_prov"));
                proveedor.setRazonSocialProv(rs.getString("razon_social_prov"));
                proveedor.setDomicilioProv(rs.getString("domicilio_prov"));
                proveedor.setCuitProv(rs.getString("cuit_prov"));
                proveedor.setTelefonoProv(rs.getString("telefono_prov"));
                proveedor.setCorreoProv(rs.getString("correo_prov"));
                proveedor.setContactoProv(rs.getString("contacto_prov"));
                proveedor.setOtrosDatosProv(rs.getString("otros_datos_prov"));
                proveedor.setEstadoProv(true);
                lisProveedores.add(proveedor);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar los Proveedores..."+e.getMessage());
        }
        return lisProveedores;
    }

    //metodo buscar por id
    public Proveedor buscarPorId(int vIdP){
        Proveedor proveedor = null;
        String sql = "Select id_prov,razon_social_prov,domicilio_prov,cuit_prov,telefono_prov,correo_prov,contacto_prov,otros_datos_prov,estado_prov from proveedores where estado_prov=1 and id_prov=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                proveedor = new Proveedor();
                proveedor.setIdProv(rs.getInt("id_prov"));
                proveedor.setRazonSocialProv(rs.getString("razon_social_prov"));
                proveedor.setDomicilioProv(rs.getString("domicilio_prov"));
                proveedor.setCuitProv(rs.getString("cuit_prov"));
                proveedor.setTelefonoProv(rs.getString("telefono_prov"));
                proveedor.setCorreoProv(rs.getString("correo_prov"));
                proveedor.setContactoProv(rs.getString("contacto_prov"));
                proveedor.setOtrosDatosProv(rs.getString("otros_datos_prov"));
                proveedor.setEstadoProv(true);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar Proveedor por id..."+e.getMessage());
        }
        return proveedor;
    }

}
