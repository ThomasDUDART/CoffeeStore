package BDDInterraction;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beans")
public class Beans {

    @Id
    @GeneratedValue
    @Column(name = "beansid")
    private int idBeans;
    private String nom;
    private int poidsUnit;
    private int prixUnit;
    private int unit;
    private int score;
    private int torrefaction;
    private  int origineId;

    //a corriger, faut juste trouver le bon nom du champ
    @Column(name = "OrigineId")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrigineId")



    public void setIdBeans(int idBeans) {
        this.idBeans = idBeans;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoidsUnit(int poidsUnit) {
        this.poidsUnit = poidsUnit;
    }

    public void setPrixUnit(int prixUnit) {
        this.prixUnit = prixUnit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setOrigineId(int origineId) {
        this.origineId = origineId;
    }

    public void setTorrefaction(int torrefaction) {
        this.torrefaction = torrefaction;
    }

}

