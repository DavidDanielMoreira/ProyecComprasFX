package entidades;

public class Proveedor {
    //atributos
    private int idProv;     //campo principal y auto incrementable
    private String razonSocialProv;
    private String domicilioProv;
    private String cuitProv;        //máximo 11 caracteres
    private String telefonoProv;
    private String correoProv;
    private String contactoProv;
    private String otrosDatosProv;
    private boolean estadoProv;
    //constructor
    public Proveedor(){};

    public Proveedor(int idProv, String razonSocialProv, String domicilioProv, String cuitProv, String telefonoProv, String correoProv, String contactoProv, String otrosDatosProv, boolean estadoProv) {
        this.idProv = idProv;
        this.razonSocialProv = razonSocialProv;
        this.domicilioProv = domicilioProv;
        this.cuitProv = cuitProv;
        this.telefonoProv = telefonoProv;
        this.correoProv = correoProv;
        this.contactoProv = contactoProv;
        this.otrosDatosProv = otrosDatosProv;
        this.estadoProv = estadoProv;
    }

    public Proveedor(String razonSocialProv, String domicilioProv, String cuitProv, String telefonoProv, String correoProv, String contactoProv, String otrosDatosProv, boolean estadoProv) {
        this.razonSocialProv = razonSocialProv;
        this.domicilioProv = domicilioProv;
        this.cuitProv = cuitProv;
        this.telefonoProv = telefonoProv;
        this.correoProv = correoProv;
        this.contactoProv = contactoProv;
        this.otrosDatosProv = otrosDatosProv;
        this.estadoProv = estadoProv;
    }

    //metodos getter y setter

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public String getRazonSocialProv() {
        return razonSocialProv;
    }

    public void setRazonSocialProv(String razonSocialProv) {
        this.razonSocialProv = razonSocialProv;
    }

    public String getDomicilioProv() {
        return domicilioProv;
    }

    public void setDomicilioProv(String domicilioProv) {
        this.domicilioProv = domicilioProv;
    }

    public String getCuitProv() {
        return cuitProv;
    }

    public void setCuitProv(String cuitProv) {
        this.cuitProv = cuitProv;
    }

    public String getTelefonoProv() {
        return telefonoProv;
    }

    public void setTelefonoProv(String telefonoProv) {
        this.telefonoProv = telefonoProv;
    }

    public String getCorreoProv() {
        return correoProv;
    }

    public void setCorreoProv(String correoProv) {
        this.correoProv = correoProv;
    }

    public String getContactoProv() {
        return contactoProv;
    }

    public void setContactoProv(String contactoProv) {
        this.contactoProv = contactoProv;
    }

    public String getOtrosDatosProv() {
        return otrosDatosProv;
    }

    public void setOtrosDatosProv(String otrosDatosProv) {
        this.otrosDatosProv = otrosDatosProv;
    }

    public boolean isEstadoProv() {
        return estadoProv;
    }

    public void setEstadoProv(boolean estadoProv) {
        this.estadoProv = estadoProv;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProv=" + idProv +
                ", razonSocialProv='" + razonSocialProv + '\'' +
                ", domicilioProv='" + domicilioProv + '\'' +
                ", cuitProv='" + cuitProv + '\'' +
                ", telefonoProv='" + telefonoProv + '\'' +
                ", correoProv='" + correoProv + '\'' +
                ", contactoProv='" + contactoProv + '\'' +
                ", otrosDatosProv='" + otrosDatosProv + '\'' +
                '}';
    }
}
