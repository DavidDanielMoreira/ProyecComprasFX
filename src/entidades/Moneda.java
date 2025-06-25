package entidades;

public class Moneda {
    //atributos
    private int idMone;         //campo princiapal y auto_incrementable
    private String simboloMone;
    private String descripcionMone;
    //constructor
    public Moneda(){};
    public Moneda(int idMone,String simboloMone,String descipcionMone){
        this.idMone = idMone;
        this.simboloMone = simboloMone;
        this.descripcionMone = descipcionMone;
    }
    public Moneda(String simboloMone,String descipcionMone){
        this.simboloMone = simboloMone;
        this.descripcionMone = descipcionMone;
    }
    //metodos getter y setter
    public int getIdMone(){
        return idMone;
    }
    public void setIdMone(int idMone){
        this.idMone = idMone;
    }
    public String getSimboloMone(){
        return simboloMone;
    }
    public void setSimboloMone(String simboloMone){
        this.simboloMone = simboloMone;
    }
    public String getDescripcionMone(){
        return descripcionMone;
    }

    public void setDescripcionMone(String descripcionMone){
     this.descripcionMone = descripcionMone;
    }

}
