package entidades;

public class DetalleCompra {
    //atributos
    private int idDetC;         //campo principal y auto_incrementable
    private Compra compra;      //clase Compra
    private Producto producto;  //clase Producto
    private double cantidad;
    private double precio;
    private double importe;         //precio*cantidad
    //constructor
    public DetalleCompra(){};

    public DetalleCompra(int idDetC, Compra compra, Producto producto, double cantidad, double precio, double importe) {
        this.idDetC = idDetC;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    public DetalleCompra(Compra compra, Producto producto, double cantidad, double precio, double importe) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    //metodos getter y setter

    public int getIdDetC() {
        return idDetC;
    }

    public void setIdDetC(int idDetC) {
        this.idDetC = idDetC;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" +
                "idDetC=" + idDetC +
                ", compra=" + compra +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", importe=" + importe +
                '}';
    }
}
