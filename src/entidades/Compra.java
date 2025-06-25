package entidades;

import java.time.LocalDate;

public class Compra {
    //atributos
    private int idComp;     //campo principal y auto_incrementable
    private LocalDate fechaComp;
    private String tipoFacturaComp; //A-B-C
    private String nroFacturaComp;
    private Proveedor proveedor;        //clase Proveedor
    private ModoPago modoPado;          //clase ModoPago
    private double total;
    private boolean estadoComp;
    //constructor
    public Compra(){};

    public Compra(int idComp, LocalDate fechaComp, String tipoFacturaComp, String nroFacturaComp, Proveedor proveedor, ModoPago modoPado, double total, boolean estadoComp) {
        this.idComp = idComp;
        this.fechaComp = fechaComp;
        this.tipoFacturaComp = tipoFacturaComp;
        this.nroFacturaComp = nroFacturaComp;
        this.proveedor = proveedor;
        this.modoPado = modoPado;
        this.total = total;
        this.estadoComp = estadoComp;
    }

    public Compra(LocalDate fechaComp, String tipoFacturaComp, String nroFacturaComp, Proveedor proveedor, ModoPago modoPado, double total, boolean estadoComp) {
        this.fechaComp = fechaComp;
        this.tipoFacturaComp = tipoFacturaComp;
        this.nroFacturaComp = nroFacturaComp;
        this.proveedor = proveedor;
        this.modoPado = modoPado;
        this.total = total;
        this.estadoComp = estadoComp;
    }
    //metodos getter y setter

    public int getIdComp() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp = idComp;
    }

    public LocalDate getFechaComp() {
        return fechaComp;
    }

    public void setFechaComp(LocalDate fechaComp) {
        this.fechaComp = fechaComp;
    }

    public String getTipoFacturaComp() {
        return tipoFacturaComp;
    }

    public void setTipoFacturaComp(String tipoFacturaComp) {
        this.tipoFacturaComp = tipoFacturaComp;
    }

    public String getNroFacturaComp() {
        return nroFacturaComp;
    }

    public void setNroFacturaComp(String nroFacturaComp) {
        this.nroFacturaComp = nroFacturaComp;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public ModoPago getModoPado() {
        return modoPado;
    }

    public void setModoPado(ModoPago modoPado) {
        this.modoPado = modoPado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isEstadoComp() {
        return estadoComp;
    }

    public void setEstadoComp(boolean estadoComp) {
        this.estadoComp = estadoComp;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idComp=" + idComp +
                ", fechaComp=" + fechaComp +
                ", tipoFacturaComp='" + tipoFacturaComp + '\'' +
                ", nroFacturaComp='" + nroFacturaComp + '\'' +
                ", proveedor=" + proveedor +
                ", modoPado=" + modoPado +
                ", total=" + total +
                '}';
    }

}
