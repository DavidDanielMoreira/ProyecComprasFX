package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class ClienteData {
    //atributos
    private Connection con;
    //constructor
    public ClienteData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar Cliente
    public void insertarCliente(Cliente cliente){
        String sql = "Insert into clientes (apellido_clie,nombres_clie,domicilio_clie,dni_clie,telefono_clie,correo_clie,otros_datos_clie,estado_clie) values (?,?,?,?,?,?,?,1)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cliente.getApellidoClie());
            ps.setString(2,cliente.getNombresClie());
            ps.setString(3,cliente.getDomicilioClie());
            ps.setString(4,cliente.getDniClie());
            ps.setString(5,cliente.getTelefonoClie());
            ps.setString(6,cliente.getCorreoClie());
            ps.setString(7,cliente.getOtrosDatosClie());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar el Cliente..."+e.getMessage());
        }
    }

    //metodo editar Cliente
    public void editarCliente(Cliente cliente){
        String sql = "Update clientes set apellido_clie=?,nombres_clie=?,domicilio_clie=?,dni_clie=?,telefono_clie=?,correo_clie=?,otros_datos_clie=? where id_clie=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,cliente.getApellidoClie());
            ps.setString(2,cliente.getNombresClie());
            ps.setString(3, cliente.getDomicilioClie());
            ps.setString(4, cliente.getDniClie());
            ps.setString(5, cliente.getTelefonoClie());
            ps.setString(6, cliente.getCorreoClie());
            ps.setString(7, cliente.getOtrosDatosClie());
            ps.setInt(8,cliente.getIdClie());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al editar el Cliente..."+e.getMessage());
        }
    }

    //metodo listar Clientes
    public List<Cliente> listarClientes(){
        Cliente cliente = null;
        ObservableList<Cliente> lisClientes = FXCollections.observableArrayList();
        String sql = "Select id_clie,apellido_clie,nombres_clie,domicilio_clie,dni_clie,telefono_clie,correo_clie,otros_datos_clie,estado_clie from clientes where estado_clie=1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                cliente.setIdClie(rs.getInt("id_clie"));
                cliente.setApellidoClie(rs.getString("apellido_clie"));
                cliente.setNombresClie(rs.getString("nombres_clie"));
                cliente.setDomicilioClie(rs.getString("domicilio_clie"));
                cliente.setDniClie(rs.getString("dni_clie"));
                cliente.setTelefonoClie(rs.getString("telefono_clie"));
                cliente.setCorreoClie(rs.getString("correo_clie"));
                cliente.setOtrosDatosClie(rs.getString("otros_Datos_clie"));
                lisClientes.add(cliente);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar los clientes..." +e.getMessage());
        }
        return lisClientes;
    }

    //metodo buscar Cliente por id
    public Cliente buscarPorId(int vIdC){
        Cliente cliente = null;
        String sql = "Select id_clie,apellido_clie,nombres_clie,domicilio_clie,dni_clie,telefono_clie,correo_clie,otros_datos_clie,estado_clie from clientes where estado_clie=1 and id_clie=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                cliente = new Cliente();
                cliente.setIdClie(rs.getInt("id_clie"));
                cliente.setApellidoClie(rs.getString("apellido_clie"));
                cliente.setNombresClie(rs.getString("nombres_clie"));
                cliente.setDomicilioClie(rs.getString("domicilio_clie"));
                cliente.setDniClie(rs.getString("dni_clie"));
                cliente.setTelefonoClie(rs.getString("telefono_clie"));
                cliente.setCorreoClie(rs.getString("correo_clie"));
                cliente.setOtrosDatosClie(rs.getString("otros_Datos_clie"));

            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar por Id ..." +e.getMessage());
        }
        return cliente;
    }
}
