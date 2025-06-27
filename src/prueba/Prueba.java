package prueba;
import accesoADatos.*;
import java.sql.*;
import entidades.*;
public class Prueba {
    //atributos
    private static Connection con;
    private CategoriaData cData = new CategoriaData();
    private ModoPagoData mpData = new ModoPagoData();
    private ProductoData prodData = new ProductoData();
    private TicketData tData = new TicketData();
    private CompraData compData = new CompraData();
    private ClienteData clieData = new ClienteData();
    private DetalleTicketData dtData = new DetalleTicketData();
    public static void main(String[] args){
       // con = Conexion.getConectar();
        Prueba prueba = new Prueba();
        prueba.getPrueba();
    }

    //metodo estatico para realizar pruebas
    public void getPrueba(){
        //System.out.println(cData.listarCategorias());
        //System.out.println(mpData.listarModos());
        //System.out.println(prodData.listarProductos());
        //System.out.println(tData.ultimoId());
        //System.out.println(compData.ultimoId());
        //System.out.println(compData.listarCompras());
        //System.out.println(clieData.listarClientes());
        //System.out.println(clieData.buscarPorId(vIdC));
        //System.out.println(dtData.listarPorId(vIdT));
        System.out.println(prodData.listarProductosAReponer());
    }
}
