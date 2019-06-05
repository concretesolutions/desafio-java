package cl.accenture.concrete.desafiojava.models;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "citycode")
    private String citycode;

    @Column(name = "countrycode")
    private String countrycode;

    @ManyToOne
    @JoinColumn (name = "idusuario")
    private Usuario usuario;

    public Telefono(String phone, String citycode, String countrycode, Usuario usuario) {
        this.phone = phone;
        this.citycode = citycode;
        this.countrycode = countrycode;
        this.usuario = new Usuario();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @Override
    public String toString() {
        return "Telefono[" +
                "phone:'" + phone + '\'' +
                ", citycode:'" + citycode + '\'' +
                ", countrycode:'" + countrycode + '\'' +
                ']';
    }
}
