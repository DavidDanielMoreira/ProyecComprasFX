package accesoADatos;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class ProductoData {
    //atributos
    private Connection con;
    private MonedaData mData = new MonedaData();
    private CategoriaData cData = new CategoriaData();
    private ProveedorData pData = new ProveedorData();
    private UnidadMedidaData umData = new UnidadMedidaData();
    private Moneda mSelec;
    private Categoria cSelec;
    private Proveedor pSelec;
    private UnidadMedida umSelec;
    //constructor
    public ProductoData(){
        this.con=Conexion.getConectar();
    }

    //metodo insertar Producto
    public void insertarProducto(Producto producto){
        String sql = "Insert into productos (codigo_prod,descripcion_corta_prod,codigo_barra_prod,precio_c,margen,iva,precio_v,stock_i,stock,cantidad,id_mone,id_cate,id_prov,id_unid,estado_prod) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,producto.getCodigoProd());
            ps.setString(2,producto.getDescripcionCortaProd());
            ps.setString(3,producto.getCodigoBarraProd());
            ps.setDouble(4,producto.getPrecioC());
            ps.setDouble(5,producto.getMargen());
            ps.setDouble(6,producto.getIva());
            ps.setDouble(7,producto.getPrecioV());
            ps.setDouble(8,producto.getStockI());
            ps.setDouble(9,producto.getStock());
            ps.setDouble(10,producto.getCantidad());
            ps.setInt(11,producto.getMoneda().getIdMone());
            ps.setInt(12,producto.getCategoria().getIdCate());
            ps.setInt(13,producto.getProveedor().getIdProv());
            ps.setInt(14,producto.getUnidadMedida().getIdUnid());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
           // throw new RuntimeException(e);
            System.out.println("Error al cargar el Proiducto..."+e.getMessage());
        }
    }
    //metodo editar Producto
    public void editarProducto(Producto producto){
        String sql = "Update productos set codigo_prod=?,descripcion_corta_prod=?,codigo_barra_prod=?,precio_c=?,margen=?,iva=?,precio_v=?,stock_i=?,stock=?,cantidad=?,id_mone=?,id_cate=?,id_prov=?,id_unid=? where id_prod=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, producto.getCodigoProd());
            ps.setString(2, producto.getDescripcionCortaProd());
            ps.setString(3, producto.getCodigoBarraProd());
            ps.setDouble(4, producto.getPrecioC());
            ps.setDouble(5, producto.getMargen());
            ps.setDouble(6, producto.getIva());
            ps.setDouble(7, producto.getPrecioV());
            ps.setDouble(8, producto.getStockI());
            ps.setDouble(9, producto.getStock());
            ps.setDouble(10, producto.getCantidad());
            ps.setInt(11, producto.getMoneda().getIdMone());
            ps.setInt(12, producto.getCategoria().getIdCate());
            ps.setInt(13, producto.getProveedor().getIdProv());
            ps.setInt(14, producto.getUnidadMedida().getIdUnid());
            ps.setInt(15, producto.getIdProd());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            System.out.println("Error al editar el Proiducto..."+e.getMessage());
        }
    }

    //metodo listar Productos activos
    public List<Producto> listarProductos(){
        Producto producto = null;
        ObservableList<Producto> listproductos = FXCollections.observableArrayList();
        String sql = "Select id_prod,codigo_prod,descripcion_corta_prod,codigo_barra_prod,precio_c,margen,iva,precio_v,stock_i,stock,cantidad,id_mone,id_cate,id_prov,id_unid,estado_prod from productos where estado_prod=1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                producto = new Producto();
                producto.setIdProd(rs.getInt("id_prod"));
                producto.setCodigoProd(rs.getString("codigo_prod"));
                producto.setDescripcionCortaProd(rs.getString("descripcion_corta_prod"));
                producto.setCodigoBarraProd(rs.getString("codigo_barra_prod"));
                producto.setPrecioC(rs.getDouble("precio_c"));
                producto.setMargen(rs.getDouble("margen"));
                producto.setIva(rs.getDouble("iva"));
                producto.setPrecioV(rs.getDouble("precio_v"));
                producto.setStockI(rs.getDouble("stock_i"));
                producto.setStock(rs.getDouble("stock"));
                producto.setCantidad(rs.getDouble("cantidad"));
                //busco la Moneda
                mSelec = mData.buscarPorId(rs.getInt("id_mone"));
                producto.setMoneda(mSelec);
                //busco la Categoria
                cSelec = cData.buscarPorId(rs.getInt("id_cate"));
                producto.setCategoria(cSelec);
                //busco el Proveedor
                pSelec = pData.buscarPorId(rs.getInt("id_prov"));
                producto.setProveedor(pSelec);
                //busco la UnidadMedida
                umSelec = umData.buscarPorId(rs.getInt("id_unid"));
                producto.setUnidadMedida(umSelec);
                listproductos.add(producto);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar los Productos..."+e.getMessage());
        }
        return listproductos;
    }
    //metodo buscar por id
    public Producto buscarPorId(int vIdP){
        Producto producto = null;
        String sql = "Select id_prod,codigo_prod,descripcion_corta_prod,codigo_barra_prod,precio_c,margen,iva,precio_v,stock_i,stock,cantidad,id_mone,id_cate,id_prov,id_unid,estado_prod from productos where estado_prod=1 and id_prod=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                producto = new Producto();
                producto.setIdProd(rs.getInt("id_prod"));
                producto.setCodigoProd(rs.getString("codigo_Prod"));
                producto.setDescripcionCortaProd(rs.getString("descripcion_corta_prod"));
                producto.setCodigoBarraProd(rs.getString("codigo_barra_prod"));
                producto.setPrecioC(rs.getDouble("precio_c"));
                producto.setMargen(rs.getDouble("margen"));
                producto.setIva(rs.getDouble("iva"));
                producto.setPrecioV(rs.getDouble("precio_v"));
                producto.setStockI(rs.getDouble("stock_i"));
                producto.setStock(rs.getDouble("stock"));
                producto.setCantidad(rs.getDouble("cantidad"));
                //busco la Moneda
                mSelec = mData.buscarPorId(rs.getInt("id_mone"));
                producto.setMoneda(mSelec);
                //busco la Categoria
                cSelec = cData.buscarPorId(rs.getInt("id_cate"));
                producto.setCategoria(cSelec);
                //busco el Proveedor
                pSelec = pData.buscarPorId(rs.getInt("id_prov"));
                producto.setProveedor(pSelec);
                //busco la UnidadMedida
                umSelec = umData.buscarPorId(rs.getInt("id_unid"));
                producto.setUnidadMedida(umSelec);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al buscar por id el Producto..."+e.getMessage());
        }
        return producto;
    }

    //metodo listar Productos por Proveedor
    public List<Producto> listarPorProveedor(int vIdProv){
        Producto producto = null;
        ObservableList<Producto> lisPorProv = FXCollections.observableArrayList();
        String sql = "Select id_prod,codigo_prod,descripcion_corta_prod,codigo_barra_prod,precio_c,margen,iva,precio_v,stock_i,stock,cantidad,id_mone,id_cate,id_prov,id_unid,estado_prod from productos where estado_prod=1 and id_prov=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdProv);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                producto = new Producto();
                producto.setIdProd(rs.getInt("id_prod"));
                producto.setCodigoProd(rs.getString("codigo_Prod"));
                producto.setDescripcionCortaProd(rs.getString("descripcion_corta_prod"));
                producto.setCodigoBarraProd(rs.getString("codigo_barra_prod"));
                producto.setPrecioC(rs.getDouble("precio_c"));
                producto.setMargen(rs.getDouble("margen"));
                producto.setIva(rs.getDouble("iva"));
                producto.setPrecioV(rs.getDouble("precio_v"));
                producto.setStockI(rs.getDouble("stock_i"));
                producto.setStock(rs.getDouble("stock"));
                producto.setCantidad(rs.getDouble("cantidad"));
                //busco la Moneda
                mSelec = mData.buscarPorId(rs.getInt("id_mone"));
                producto.setMoneda(mSelec);
                //busco la Categoria
                cSelec = cData.buscarPorId(rs.getInt("id_cate"));
                producto.setCategoria(cSelec);
                //busco el Proveedor
                pSelec = pData.buscarPorId(rs.getInt("id_prov"));
                producto.setProveedor(pSelec);
                //busco la UnidadMedida
                umSelec = umData.buscarPorId(rs.getInt("id_unid"));
                producto.setUnidadMedida(umSelec);
                lisPorProv.add(producto);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar Productos por Proveedor..."+e.getMessage());
        }
        return lisPorProv;
    }

    //metodo listar por Categorias
    public List<Producto> listarPorCategorias(int vIdC){
        Producto producto = null;
        ObservableList<Producto> lisPorCate = FXCollections.observableArrayList();
        String sql = "Select id_prod,codigo_prod,descripcion_corta_prod,codigo_barra_prod,precio_c,margen,iva,precio_v,stock_i,stock,cantidad,id_mone,id_cate,id_prov,id_unid,estado_prod from productos where estado_prod=1 and id_cate=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,vIdC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                producto = new Producto();
                producto.setIdProd(rs.getInt("id_prod"));
                producto.setCodigoProd(rs.getString("codigo_Prod"));
                producto.setDescripcionCortaProd(rs.getString("descripcion_corta_prod"));
                producto.setCodigoBarraProd(rs.getString("codigo_barra_prod"));
                producto.setPrecioC(rs.getDouble("precio_c"));
                producto.setMargen(rs.getDouble("margen"));
                producto.setIva(rs.getDouble("iva"));
                producto.setPrecioV(rs.getDouble("precio_v"));
                producto.setStockI(rs.getDouble("stock_i"));
                producto.setStock(rs.getDouble("stock"));
                producto.setCantidad(rs.getDouble("cantidad"));
                //busco la Moneda
                mSelec = mData.buscarPorId(rs.getInt("id_mone"));
                producto.setMoneda(mSelec);
                //busco la Categoria
                cSelec = cData.buscarPorId(rs.getInt("id_cate"));
                producto.setCategoria(cSelec);
                //busco el Proveedor
                pSelec = pData.buscarPorId(rs.getInt("id_prov"));
                producto.setProveedor(pSelec);
                //busco la UnidadMedida
                umSelec = umData.buscarPorId(rs.getInt("id_unid"));
                producto.setUnidadMedida(umSelec);
                lisPorCate.add(producto);
            }
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al listar Productos por Categorias..."+e.getMessage());
        }
        return lisPorCate;
    }

    //metodo actualizar stock
    public void actualizarStock(double vSto, int vIdP){
        String sql = "Update productos set stock=? where id_prod=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,vSto);
            ps.setInt(2,vIdP);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al actualizar el stock..."+e.getMessage());
        }

    }

    //metodo actualizar Precio
    public void actualizarPreciosPorProve(double vPreN,int vIdProv){
        String sql = "Update productos set precio_v=? where id_prov=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,vPreN);
            ps.setInt(2,vIdProv);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al actualizar los Precios..."+e.getMessage());
        }
    }

    //metodo actualizar por Categorias
    public void actualizarPreciosPorCate(double vPre,int vIdP,int vIdC){
        String sql = "Update productos set precio_v=? where id_prod=? and id_cate=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,vPre);
            ps.setInt(2,vIdP);
            ps.setInt(3,vIdC);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println("Error al actualizar los Precios..."+e.getMessage());
        }
    }

}
