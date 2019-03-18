package meteo.Entity;

import javax.persistence.*;

@Entity
@Table(name="test.Meteo")
public class Meteo {

    private int idMeteo;
    private String meteoAjd;


    public Meteo(String meteoAjd) {
        this.meteoAjd = meteoAjd;
    }

    public Meteo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdMeteo() {
        return idMeteo;
    }

    public void setIdMeteo(int idMeteo) {
        this.idMeteo = idMeteo;
    }

    public String getMeteoAjd() {
        return meteoAjd;
    }

    public void setMeteoAjd(String meteoAjd) {
        this.meteoAjd = meteoAjd;
    }
}
