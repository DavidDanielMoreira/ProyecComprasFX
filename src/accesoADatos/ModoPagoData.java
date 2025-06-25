package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class ModoPagoData {
    //atributos
    private Connection con;
    //constructor
    public ModoPagoData(){
        this.con = Conexion.getConectar();
    }

    //metodo insertar ModoPago
    public void insertarModoPago(ModoPago mPago){
        String sql = "Insert into m_pagos (tipo_pago) values (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,mPago.getModoPago());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al cargar el Modo de Pago..."+e.getMessage());
        }
    }

    //metodo listar ModoPago
    public List<ModoPago> listarModos(){
        ModoPago mPago;
        ObservableList<ModoPago> lisModos = FXCollections.observableArrayList();
        String sql = "Select id_modo,tipo_pago from m_pagos";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                mPago = new ModoPago();
                mPago.setIdModo(rs.getInt("id_modo"));
                mPago.setModoPago(rs.getString("tipo_pago"));
                lisModos.add(mPago);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar los Modos de pagos"+e.getMessage());
        }
        return lisModos;
    }
    //metodo buscar por Id
    public ModoPago buscarPorId(int vIdMP){
        ModoPago mPago = null;
        String sql = "Select id_modo,tipo_pago from m_pagos where id_modo=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdMP);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                mPago = new ModoPago();
                mPago.setIdModo(rs.getInt("id_modo"));
                mPago.setModoPago(rs.getString("tipo_pago"));
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar los Modos de pagos"+e.getMessage());
        }
        return mPago;
    }
}
