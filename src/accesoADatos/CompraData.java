package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class CompraData {
    //atributos
    private Connection con;
    private ProveedorData provData = new ProveedorData();
    private ModoPagoData mpData = new ModoPagoData();
    private Proveedor provSelec;
    private ModoPago mpSelec;
    //constructor
    public CompraData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar Compra
    public void insertarCompra(Compra compra){
        String sql = "Insert into compras (fecha_comp,tipo_factura_comp,nro_factura_comp,id_prov,id_modo,total,estado_comp) values (?,?,?,?,?,?,1)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1,Date.valueOf(compra.getFechaComp()));
            ps.setString(2,compra.getTipoFacturaComp());
            ps.setString(3,compra.getNroFacturaComp());
            ps.setDouble(4,compra.getProveedor().getIdProv());
            ps.setDouble(5,compra.getModoPado().getIdModo());
            ps.setDouble(6,compra.getTotal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
           //throw new RuntimeException(e);
            System.out.println("Error al cargar la compra..."+e.getMessage());
        }
    }

    //metodo listar Compras
    public List<Compra> listarCompras(){
        Compra compra = null;
        ObservableList<Compra> lisCompras = FXCollections.observableArrayList();
        String sql = "Select id_comp,fecha_comp,tipo_factura_comp,nro_factura_comp,id_prov,id_modo,total,estado_comp from compras";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                compra = new Compra();
                compra.setIdComp(rs.getInt("id_comp"));
                compra.setFechaComp(rs.getDate("fecha_comp").toLocalDate());
                compra.setTipoFacturaComp(rs.getString("tipo_factura_comp"));
                compra.setNroFacturaComp(rs.getString("nro_factura_comp"));
                //busco el Proveedor
                provSelec = provData.buscarPorId(rs.getInt("id_prov"));
                compra.setProveedor(provSelec);
                //busco el ModoPago
                mpSelec = mpData.buscarPorId(rs.getInt("id_modo"));
                compra.setModoPado(mpSelec);
                compra.setTotal(rs.getDouble("total"));
                lisCompras.add(compra);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar las compras cargadas..."+e.getMessage());
        }
        return lisCompras;
    }

    //metodo buscar por id
    public Compra buscarPorId(int vIdC){
        Compra compra = null;
        String sql = "Select id_comp,fecha_comp,tipo_factura_comp,nro_factura_comp,id_prov,id_modo,total,estado_comp from compras where id_comp=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdC);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                compra = new Compra();
                compra.setIdComp(rs.getInt("id_comp"));
                compra.setFechaComp(rs.getDate("fecha_comp").toLocalDate());
                compra.setTipoFacturaComp(rs.getString("tipo_factura_comp"));
                compra.setNroFacturaComp(rs.getString("nro_factura_comp"));
                //busco el Proveedor
                provSelec = provData.buscarPorId(rs.getInt("id_prov"));
                compra.setProveedor(provSelec);
                //busco el ModoPago
                mpSelec = mpData.buscarPorId(rs.getInt("id_modo"));
                compra.setModoPado(mpSelec);
                compra.setTotal(rs.getDouble("total"));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar la Compra por id..."+e.getMessage());
        }
        return compra;
    }

    //metodo recuperar ultimo id
    public int ultimoId(){
        int vIdUlt=0;
        String sql = "Select max(id_comp) as ultimo from compras";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vIdUlt = rs.getInt("ultimo");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al recuperar el ultimo id ingresado..."+e.getMessage());
        }
        return vIdUlt;
    }
}
