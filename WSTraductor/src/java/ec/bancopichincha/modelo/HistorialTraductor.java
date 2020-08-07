package ec.bancopichincha.modelo;
// Generated 06/08/2020 20:30:10 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HistorialTraductor generated by hbm2java
 */
@Entity
@Table(name="historial_traductor"
    ,catalog="bdtraductor"
)
public class HistorialTraductor  implements java.io.Serializable {


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
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_historial_traductor", unique=true, nullable=false)
    public Integer getIdHistorialTraductor() {
        return this.idHistorialTraductor;
    }
    
    public void setIdHistorialTraductor(Integer idHistorialTraductor) {
        this.idHistorialTraductor = idHistorialTraductor;
    }

    
    @Column(name="texto", nullable=false, length=1024)
    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }

    
    @Column(name="traduccion", nullable=false, length=1024)
    public String getTraduccion() {
        return this.traduccion;
    }
    
    public void setTraduccion(String traduccion) {
        this.traduccion = traduccion;
    }

    
    @Column(name="lenguaje", nullable=false, length=256)
    public String getLenguaje() {
        return this.lenguaje;
    }
    
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

}


