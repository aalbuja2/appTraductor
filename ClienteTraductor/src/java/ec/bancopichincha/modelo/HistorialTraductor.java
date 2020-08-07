package ec.bancopichincha.modelo;
// Generated 06/08/2020 20:30:10 by Hibernate Tools 4.3.1

public class HistorialTraductor implements java.io.Serializable {

    private Integer idHistorialTraductor;
    private String texto;
    private String traduccion;
    private String lenguaje;

    public HistorialTraductor() {
    }

    public HistorialTraductor(String texto, String traduccion, String lenguaje) {
        this.texto = texto;
        this.traduccion = traduccion;
        this.lenguaje = lenguaje;
    }

    public Integer getIdHistorialTraductor() {
        return this.idHistorialTraductor;
    }

    public void setIdHistorialTraductor(Integer idHistorialTraductor) {
        this.idHistorialTraductor = idHistorialTraductor;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTraduccion() {
        return this.traduccion;
    }

    public void setTraduccion(String traduccion) {
        this.traduccion = traduccion;
    }

    public String getLenguaje() {
        return this.lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

}
