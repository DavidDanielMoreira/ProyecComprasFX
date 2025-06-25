package entidades;

public class UnidadMedida {
    //atributos
    private int idUnid;     //campo principal y auto_incrementable
    private String siglaUnid;
    private String descripcionUnid;
    //constructor
    public UnidadMedida(){};

    public UnidadMedida(int idUnid, String siglaUnid, String descripcionUnid) {
        this.idUnid = idUnid;
        this.siglaUnid = siglaUnid;
        this.descripcionUnid = descripcionUnid;
    }

    public UnidadMedida(String siglaUnid, String descripcionUnid) {
        this.siglaUnid = siglaUnid;
        this.descripcionUnid = descripcionUnid;
    }
    //metodos getter y setter

    public int getIdUnid() {
        return idUnid;
    }

    public void setIdUnid(int idUnid) {
        this.idUnid = idUnid;
    }

    public String getSiglaUnid() {
        return siglaUnid;
    }

    public void setSiglaUnid(String siglaUnid) {
        this.siglaUnid = siglaUnid;
    }

    public String getDescripcionUnid() {
        return descripcionUnid;
    }

    public void setDescripcionUnid(String descripcionUnid) {
        this.descripcionUnid = descripcionUnid;
    }

    @Override
    public String toString() {
        return "UnidadMedida{" +
                "idUnid=" + idUnid +
                ", siglaUnid='" + siglaUnid + '\'' +
                ", descripcionUnid='" + descripcionUnid + '\'' +
                '}';
    }

}
