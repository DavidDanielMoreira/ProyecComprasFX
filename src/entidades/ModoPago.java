package entidades;

public class ModoPago {
    //atributos
    private int idModo;             //campo principal y auto_incrementable
    private String modoPago;        // EFECTIVO-OTROS MEDIOS-CTACTE
    //constructor
    public ModoPago(){};

    public ModoPago(int idModo, String modoPago) {
        this.idModo = idModo;
        this.modoPago = modoPago;
    }

    public ModoPago(String modoPago) {
        this.modoPago = modoPago;
    }

    //metodos getter y setter

    public int getIdModo() {
        return idModo;
    }

    public void setIdModo(int idModo) {
        this.idModo = idModo;
    }

    public String getModoPago() {
        return modoPago;
    }

    public void setModoPago(String modoPago) {
        this.modoPago = modoPago;
    }

    @Override
    public String toString() {
        return
                 idModo +
                " - " + modoPago ;
    }
}
