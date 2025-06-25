package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class DetalleTicketData {
    //atributos
    private Connection con;
    private TicketData tData = new TicketData();
    private ProductoData pData = new ProductoData();
    private Ticket tSelec;
    private Producto pSelec;
    //constructor
    public DetalleTicketData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar DetalleTicket
    public void insertarDetalleTicket(DetalleTicket dTicket){
        String sql = "Insert into d_tickets (id_tick,id_prod,cantidad,precio,importe) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,dTicket.getTicket().getIdTick());
            ps.setInt(2,dTicket.getProducto().getIdProd());
            ps.setDouble(3,dTicket.getCantidad());
            ps.setDouble(4,dTicket.getPrecio());
            ps.setDouble(5,dTicket.getImporte());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar el Detalle..."+e.getMessage());
        }
    }

    //metodo listar Detalle por idTick
    public List<DetalleTicket> listarPorId(int vIdT){
        DetalleTicket dTicket = null;
        ObservableList<DetalleTicket> lisPorId = FXCollections.observableArrayList();
        String sql = "Select id_dett,id_tick,id_prod,cantidad,precio,importe from d_tickets where id_tick=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdT);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dTicket = new DetalleTicket();
                dTicket.setIdDett(rs.getInt("id_Dett"));
                //busco el Ticket
                tSelec = tData.buscarPorId(rs.getInt("id_tick"));
                dTicket.setTicket(tSelec);
                //busco el Producto
                pSelec = pData.buscarPorId(rs.getInt("id_prod"));
                dTicket.setProducto(pSelec);
                dTicket.setCantidad(rs.getDouble("cantidad"));
                dTicket.setPrecio(rs.getDouble("precio"));
                dTicket.setImporte(rs.getDouble("importe"));
                lisPorId.add(dTicket);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar detalles por id ticket..."+e.getMessage());
        }
        return lisPorId;
    }

}
