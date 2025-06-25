package entidades;

public class Producto {
    //atributos
    private int idProd;     //campo principal y auto_incrementable
    private String codigoProd;
    private String descripcionCortaProd;
    private String codigoBarraProd;
    private double precioC;
    private double margen;
    private double iva;
    private double precioV;
    private double stockI;
    private double stock;
    private double cantidad;
    private Moneda moneda;
    private Categoria categoria;
    private Proveedor proveedor;
    private UnidadMedida unidadMedida;
    private boolean estadoProd;

    //constructor
    public Producto() {};

    public Producto(int idProd, String codigoProd, String descripcionCortaProd, String codigoBarraProd, double precioC, double margen, double iva, double precioV, double stockI, double stock, double cantidad, Moneda moneda, Categoria categoria, Proveedor proveedor, UnidadMedida unidadMedida, boolean estadoProd) {
        this.idProd = idProd;
        this.codigoProd = codigoProd;
        this.descripcionCortaProd = descripcionCortaProd;
        this.codigoBarraProd = codigoBarraProd;
        this.precioC = precioC;
        this.margen = margen;
        this.iva = iva;
        this.precioV = precioV;
        this.stockI = stockI;
        this.stock = stock;
        this.cantidad = cantidad;
        this.moneda = moneda;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.unidadMedida = unidadMedida;
        this.estadoProd = estadoProd;
    }

    public Producto(String codigoProd, String descripcionCortaProd, String codigoBarraProd, double precioC, double margen, double iva, double precioV, double stockI, double stock, double cantidad, Moneda moneda, Categoria categoria, Proveedor proveedor, UnidadMedida unidadMedida, boolean estadoProd) {
        this.codigoProd = codigoProd;
        this.descripcionCortaProd = descripcionCortaProd;
        this.codigoBarraProd = codigoBarraProd;
        this.precioC = precioC;
        this.margen = margen;
        this.iva = iva;
        this.precioV = precioV;
        this.stockI = stockI;
        this.stock = stock;
        this.cantidad = cantidad;
        this.moneda = moneda;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.unidadMedida = unidadMedida;
        this.estadoProd = estadoProd;
    }

    //metodos getter y setter

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getCodigoProd() {
        return codigoProd;
    }

    public void setCodigoProd(String codigoProd) {
        this.codigoProd = codigoProd;
    }

    public String getDescripcionCortaProd() {
        return descripcionCortaProd;
    }

    public void setDescripcionCortaProd(String descripcionCortaProd) {
        this.descripcionCortaProd = descripcionCortaProd;
    }

    public String getCodigoBarraProd() {
        return codigoBarraProd;
    }

    public void setCodigoBarraProd(String codigoBarraProd) {
        this.codigoBarraProd = codigoBarraProd;
    }

    public double getPrecioC() {
        return precioC;
    }

    public void setPrecioC(double precioC) {
        this.precioC = precioC;
    }

    public double getMargen() {
        return margen;
    }

    public void setMargen(double margen) {
        this.margen = margen;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPrecioV() {
        return precioV;
    }

    public void setPrecioV(double precioV) {
        this.precioV = precioV;
    }

    public double getStockI() {
        return stockI;
    }

    public void setStockI(double stockI) {
        this.stockI = stockI;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public boolean isEstadoProd() {
        return estadoProd;
    }

    public void setEstadoProd(boolean estadoProd) {
        this.estadoProd = estadoProd;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProd=" + idProd +
                ", codigoProd='" + codigoProd + '\'' +
                ", descripcionCortaProd='" + descripcionCortaProd + '\'' +
                ", codigoBarraProd='" + codigoBarraProd + '\'' +
                ", precioC=" + precioC +
                ", margen=" + margen +
                ", iva=" + iva +
                ", precioV=" + precioV +
                ", stockI=" + stockI +
                ", stock=" + stock +
                ", cantidad=" + cantidad +
                ", moneda=" + moneda +
                ", categoria=" + categoria +
                ", proveedor=" + proveedor +
                ", unidadMedida=" + unidadMedida +
                ", estadoProd=" + estadoProd +
                '}';
    }
}