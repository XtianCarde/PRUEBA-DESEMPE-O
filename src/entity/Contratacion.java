package entity;


public class Contratacion {

    private int id;
    private int vacante_id;
    private int coder_id;
    private String fecha_aplicacion;
    private String estado;
    private double salario;
    private Vacante vacante;
    private Coder coder;

    public Contratacion() {
    }

    public Contratacion(int id, int vacante_id, int coder_id, String fecha_aplicacion, String estado, double salario, Vacante vacante, Coder coder) {
        this.id = id;
        this.vacante_id = vacante_id;
        this.coder_id = coder_id;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
        this.vacante = vacante;
        this.coder = coder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVacante_id() {
        return vacante_id;
    }

    public void setVacante_id(int vacante_id) {
        this.vacante_id = vacante_id;
    }

    public int getCoder_id() {
        return coder_id;
    }

    public void setCoder_id(int coder_id) {
        this.coder_id = coder_id;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Coder getCoder() {
        return coder;
    }

    public void setCoder(Coder coder) {
        this.coder = coder;
    }

    @Override
    public String toString() {
        return "Contratacion: " +
                "fecha_aplicacion' " + fecha_aplicacion + '\'' +
                ", estado: " + estado +
                ", salario: " + salario +
                ", " + vacante.toString() +
                ", " + coder.toString();
    }
}
