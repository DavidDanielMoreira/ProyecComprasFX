package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class DetalleCompraData {
    //atrributos
    private Connection con;
    private CompraData cData = new CompraData();
    private ProductoData pData = new ProductoData();
    private Compra cSelec;
    private Producto pSelec;
    //constructor
    public DetalleCompraData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar DetalleCompra
    public void insertarDetalleCompra(DetalleCompra dCompra){
        String sql = "Insert into d_compras (id_comp,id_prod,cantidad,precio,importe) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,dCompra.getCompra().getIdComp());
            ps.setInt(2,dCompra.getProducto().getIdProd());
            ps.setDouble(3,dCompra.getCantidad());
            ps.setDouble(4,dCompra.getPrecio());
            ps.setDouble(5,dCompra.getImporte());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar el Detalle de la compra..."+e.getMessage());
        }
    }

    //metodo buscar por id
    public List<DetalleCompra> listarPorId(int vIdT){
        DetalleCompra dCompra=null;
        ObservableList<DetalleCompra> lisPorId = FXCollections.observableArrayList();
        String sql = "Select id_detc,id_comp,id_prod,cantidad,precio,importe from d_compras where id_comp=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdT);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dCompra = new DetalleCompra();
                dCompra.setIdDetC(rs.getInt("id_detc"));
                //busco el Ticket
                cSelec = cData.buscarPorId(rs.getInt("id_comp"));
                dCompra.setCompra(cSelec);
                //busco el Producto
                pSelec = pData.buscarPorId(rs.getInt("id_prod"));
                dCompra.setProducto(pSelec);
                dCompra.setCantidad(rs.getDouble("cantidad"));
                dCompra.setPrecio(rs.getDouble("precio"));
                dCompra.setImporte(rs.getDouble("importe"));
                lisPorId.add(dCompra);

            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar detalle de la compra por id..." +e.getMessage());
        }
        return lisPorId;
    }
}
