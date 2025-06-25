package accesoADatos;
import java.sql.*;
public class Conexion {
    //atributos estaticos
    private static String URL = "jdbc:mysql://localhost/bd_compras";
    private static String USER = "root";
    private static String PASS = "";
    private static Connection conexion = null;

    //metodo publico estatico y de tipo Connection para establecer la conexion a la base de datos
    public static Connection getConectar(){
        if(conexion==null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL,USER,PASS);
                //System.out.println("Conexion establecida!!!!");
            } catch (ClassNotFoundException e) {
                System.out.println("Error de drivers..."+e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error de base de datos..."+e.getMessage());
            }

        }
        return conexion;
    }
}
