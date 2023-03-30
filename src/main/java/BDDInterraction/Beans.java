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

    //a corriger, faut juste trouver le bon nom du champ
    @Column(name = "OrigineId")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrigineId")



    public void setIdBeans(int idBeans) {
        this.idBeans = idBeans;
    }


    public void setNom(String datum) {
    }
}

