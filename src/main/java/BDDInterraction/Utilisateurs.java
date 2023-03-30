package BDDInterraction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "utilisateur")
public class Utilisateurs {

    @Id
    @GeneratedValue
    @Column(name = "id_utilisateur")
    private int idUtilisateur;

    @Column(name = "login")
    private String login;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "droit_de_connexion")
    private boolean droitDeConnexion;

    @Column(name = "equipe_sofie")
    private String equipeSofie;

    @Column(name = "equipe")
    private String equipe;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "derniere_connexion")
    private LocalDate derniereConnexion;

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDerniereConnexion(LocalDate derniereConnexion) {
        this.derniereConnexion = derniereConnexion;
    }

    public void setDroitDeConnexion(boolean droitDeConnexion) {
        this.droitDeConnexion = droitDeConnexion;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public void setEquipeSofie(String equipeSofie) {
        this.equipeSofie = equipeSofie;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}

