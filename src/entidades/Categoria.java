package entidades;

public class Categoria {
    //atributos
    private int idCate;  //campo principal y auto_incrementable
    private String nombreCate;
    //constructor
    public Categoria(){};
    public Categoria(int idCate,String nombreCate){
        this.idCate = idCate;
        this.nombreCate = nombreCate;
    }
    public Categoria(String nombreCate){
        this.nombreCate = nombreCate;
    }

    //metodos getter y setter
    public int getIdCate(){
        return idCate;
    }
    public void setIdCate(int idCate){
        this.idCate = idCate;
    }
    public String getNombreCate(){
        return nombreCate;
    }
    public void setNombreCate(String nombreCate){
        this.nombreCate = nombreCate;
    }
    @Override
    public String toString(){
        return idCate + " - " + nombreCate;
    }
}
