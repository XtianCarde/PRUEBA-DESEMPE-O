package entity;

public class Coder {

    private int id;
    private String nombre;
    private String apellidos;
    private String documento;
    private String clan;
    private int cohorte;
    private String cv;

    public Coder() {
    }

    public Coder(int id, String nombre, String apellidos, String documento, String clan, int cohorte, String cv) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento = documento;
        this.clan = clan;
        this.cohorte = cohorte;
        this.cv = cv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public int getCohorte() {
        return cohorte;
    }

    public void setCohorte(int cohorte) {
        this.cohorte = cohorte;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "Coder: " +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", documento='" + documento + '\'' +
                ", cohorte=" + cohorte +
                ", cv='" + cv;
    }
}
