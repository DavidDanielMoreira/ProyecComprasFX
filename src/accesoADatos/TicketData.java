package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class TicketData {
    //atributos
    private Connection con;
    private ClienteData cData = new ClienteData();
    private ModoPagoData mpData = new ModoPagoData();
    private Cliente cSelec;
    private ModoPago mpSelec;

    //constructor
    public TicketData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar Ticket
    public void insertarTicket(Ticket ticket){
        String sql = "Insert into tickets (fecha_tick,nro_tick,tipo_tick,id_clie,id_modo,descuento,total,estado_tick) values (?,?,?,?,?,?,?,1)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1,Date.valueOf(ticket.getFechaTick()));
            ps.setString(2,ticket.getNroTick());
            ps.setString(3,ticket.getTipoTick());
            ps.setInt(4,ticket.getCliente().getIdClie());
            ps.setInt(5,ticket.getModoPago().getIdModo());
            ps.setDouble(6,ticket.getDescuento());
            ps.setDouble(7,ticket.getTotal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar el comprobante");
        }
    }

    //metodo dar de baja por id
    public void bajasPorId(int vIdT){
        String sql = "Update tickets estado_tick=? where id_tick=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al dar de bajas por id...");
        }
    }

    //metodo listar Comprobantes
    public List<Ticket> listarTickets(){
        Ticket ticket = null;
        ObservableList<Ticket> lisTickets = FXCollections.observableArrayList();
        String sql = "Select id_tick,fecha_tick,nro_tick,tipo_tick,id_clie,id_modo,descuento,total,estado_tick from tickets";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ticket = new Ticket();
                ticket.setIdTick(rs.getInt("id_tick"));
                ticket.setFechaTick(rs.getDate("fecha_tick").toLocalDate());
                ticket.setNroTick(rs.getString("nro_tick"));
                ticket.setTipoTick(rs.getString("tipo_tick"));
                //busco el Cliente
                cSelec = cData.buscarPorId(rs.getInt("id_clie"));
                ticket.setCliente(cSelec);
                //busco el ModoPago
                mpSelec = mpData.buscarPorId(rs.getInt("id_modo"));
                ticket.setModoPago(mpSelec);
                ticket.setDescuento(rs.getDouble("descuento"));
                ticket.setTotal(rs.getDouble("total"));
                lisTickets.add(ticket);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar los comprobantes..."+e.getMessage());
        }
        return lisTickets;
    }

    //metodo buscar por id
    public Ticket buscarPorId(int vIdT){
        Ticket ticket = null;
        String sql = "Select id_tick,fecha_tick,nro_tick,tipo_tick,id_clie,id_modo,descuento,total,estado_tick from tickets where id_tick=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdT);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ticket = new Ticket();
                ticket.setIdTick(rs.getInt("id_tick"));
                ticket.setFechaTick(rs.getDate("fecha_tick").toLocalDate());
                ticket.setNroTick(rs.getString("nro_tick"));
                ticket.setTipoTick(rs.getString("tipo_tick"));
                //busco el Cliente
                cSelec = cData.buscarPorId(rs.getInt("id_clie"));
                ticket.setCliente(cSelec);
                //busco el ModoPago
                mpSelec = mpData.buscarPorId(rs.getInt("id_modo"));
                ticket.setModoPago(mpSelec);
                ticket.setDescuento(rs.getDouble("descuento"));
                ticket.setTotal(rs.getDouble("total"));
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar por id..."+e.getMessage());
        }
        return ticket;
    }

    //metodo recuperar ultimo id
    public int ultimoId(){
        int vUltId=0;
        String sql = "Select max(id_tick) as ultimo from tickets";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vUltId = rs.getInt("ultimo");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar el comprobante..."+e.getMessage());
        }
        return vUltId;
    }

}
