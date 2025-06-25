package entidades;

public class DetalleTicket {
   //atributos
    private int idDett;         //campo principal y auto_incrementable
    private Ticket ticket;
    private Producto producto;
    private double cantidad;
    private double precio;
    private double importe;
    //constructor
    public DetalleTicket(){};

    public DetalleTicket(int idDett, Ticket ticket, Producto producto, double cantidad, double precio, double importe) {
        this.idDett = idDett;
        this.ticket = ticket;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    public DetalleTicket(Ticket ticket, Producto producto, double cantidad, double precio, double importe) {
        this.ticket = ticket;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }
    //metodos getter y setter

    public int getIdDett() {
        return idDett;
    }

    public void setIdDett(int idDett) {
        this.idDett = idDett;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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
        return "DetalleTicket{" +
                "idDett=" + idDett +
                ", ticket=" + ticket +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", importe=" + importe +
                '}';
    }
}
