package Beans;

/**
 * Created by Daniel Ramos Rivera on 7/4/2017.
 */

public class Clientes {
    private     int id;
    private String nombre, email;
    public Clientes(int id,String nombre, String email){
        super();
       this.id =id;
        this.nombre = nombre;
        this.email=email;

    }
    public Clientes(String nombre, String email){
        super();
        this.nombre =nombre;
        this.email = email;

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(){
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
