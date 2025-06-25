package entidades;

import java.time.LocalDate;

public class Ticket {
    //atributos
    private int idTick;         //campo principal y auto_incrementable
    private LocalDate fechaTick;
    private String nroTick;
    private String tipoTick;
    private Cliente cliente;
    private ModoPago modoPago;
    private double descuento;
    private double total;
    private boolean estadoTick;
    //constructor
    public Ticket(){};

    public Ticket(int idTick, LocalDate fechaTick, String nroTick, String tipoTick, Cliente cliente, ModoPago modoPago, double descuento, double total, boolean estadoTick) {
        this.idTick = idTick;
        this.fechaTick = fechaTick;
        this.nroTick = nroTick;
        this.tipoTick = tipoTick;
        this.cliente = cliente;
        this.modoPago = modoPago;
        this.descuento = descuento;
        this.total = total;
        this.estadoTick = estadoTick;
    }

    public Ticket(LocalDate fechaTick, String nroTick, String tipoTick, Cliente cliente, ModoPago modoPago, double descuento, double total, boolean estadoTick) {
        this.fechaTick = fechaTick;
        this.nroTick = nroTick;
        this.tipoTick = tipoTick;
        this.cliente = cliente;
        this.modoPago = modoPago;
        this.descuento = descuento;
        this.total = total;
        this.estadoTick = estadoTick;
    }
    //metodos getter y setter

    public int getIdTick() {
        return idTick;
    }

    public void setIdTick(int idTick) {
        this.idTick = idTick;
    }

    public LocalDate getFechaTick() {
        return fechaTick;
    }

    public void setFechaTick(LocalDate fechaTick) {
        this.fechaTick = fechaTick;
    }

    public String getNroTick() {
        return nroTick;
    }

    public void setNroTick(String nroTick) {
        this.nroTick = nroTick;
    }

    public String getTipoTick() {
        return tipoTick;
    }

    public void setTipoTick(String tipoTick) {
        this.tipoTick = tipoTick;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ModoPago getModoPago() {
        return modoPago;
    }

    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isEstadoTick() {
        return estadoTick;
    }

    public void setEstadoTick(boolean estadoTick) {
        this.estadoTick = estadoTick;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTick=" + idTick +
                ", fechaTick=" + fechaTick +
                ", nroTick='" + nroTick + '\'' +
                ", tipoTick='" + tipoTick + '\'' +
                ", cliente=" + cliente +
                ", modoPago=" + modoPago +
                ", descuento=" + descuento +
                ", total=" + total +
                ", estadoTick=" + estadoTick +
                '}';
    }

}
