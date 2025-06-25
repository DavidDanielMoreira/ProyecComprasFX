package entidades;

public class Cliente {
    //atributos
    private int idClie;         //campo principal y auto_incrementable
    private String apellidoClie;
    private String nombresClie;
    private String domicilioClie;
    private String dniClie;
    private String telefonoClie;
    private String correoClie;
    private String otrosDatosClie;
    private boolean estadoClie;
    //constructor
    public Cliente(){};
    public Cliente(int idClie,String apellidoClie,String nombresClie,String domicilioClie,String dniClie,String telefonoClie,String correoClie,String otrosDatosClie,boolean estadoClie){
        this.idClie = idClie;
        this.apellidoClie = apellidoClie;
        this.nombresClie = nombresClie;
        this.domicilioClie = domicilioClie;
        this.dniClie = dniClie;
        this.telefonoClie = telefonoClie;
        this.correoClie = correoClie;
        this.otrosDatosClie = otrosDatosClie;
        this.estadoClie = estadoClie;
    }
    public Cliente(String apellidoClie,String nombresClie,String domicilioClie,String dniClie,String telefonoClie,String correoClie,String otrosDatosClie,boolean estadoClie){
        this.apellidoClie = apellidoClie;
        this.nombresClie = nombresClie;
        this.domicilioClie = domicilioClie;
        this.dniClie = dniClie;
        this.telefonoClie = telefonoClie;
        this.correoClie = correoClie;
        this.otrosDatosClie = otrosDatosClie;
        this.estadoClie = estadoClie;
    }
    //metodos getter y setter
    public int getIdClie(){
        return idClie;
    }
    public void setIdClie(int idClie){
        this.idClie = idClie;
    }
    public String getApellidoClie(){
        return apellidoClie;
    }
    public void setApellidoClie(String apellidoClie){
        this.apellidoClie = apellidoClie;
    }
    public String getNombresClie(){
        return nombresClie;
    }
    public void setNombresClie(String nombresClie){
        this.nombresClie = nombresClie;
    }
    public String getDomicilioClie(){
        return domicilioClie;
    }
    public void setDomicilioClie(String domicilioClie){
        this.domicilioClie = domicilioClie;
    }
    public String getDniClie(){
        return dniClie;
    }
    public void setDniClie(String dniClie){
        this.dniClie = dniClie;
    }
    public String getTelefonoClie(){
        return telefonoClie;
    }
    public void setTelefonoClie(String telefonoClie){
        this.telefonoClie = telefonoClie;
    }
    public String getCorreoClie(){
        return correoClie;
    }
    public void setCorreoClie(String correoClie){
        this.correoClie = correoClie;
    }
    public String getOtrosDatosClie(){
        return otrosDatosClie;
    }
    public void setOtrosDatosClie(String otrosDatosClie){
        this.otrosDatosClie = otrosDatosClie;
    }
    public boolean getEstadoClie(){
        return estadoClie;
    }
    public void setEstadoClie(boolean estadoClie){
        this.estadoClie = estadoClie;
    }

    @Override
    public String toString(){
       return  idClie + " - " + apellidoClie + " - " + nombresClie + " - " + domicilioClie + " - " + dniClie + " - " + telefonoClie + " - " + correoClie + " - " + otrosDatosClie;
    }

}
