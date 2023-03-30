package BDDInterraction;


import Repository.RoleRepository;
import Repository.SiteRepository;
import Repository.UtilisateursRepository;
import domain.Utilisateurs;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class Remplissage {
    InterfaceBeans beansRepository;



    public List<Beans> readUserFromCsv(String chemin) {
        List<Beans> users = new ArrayList<>();

        Path path = Paths.get(chemin);

        try (BufferedReader bufferedReader = Files.newBufferedReader(
                path, StandardCharsets.UTF_8)) {
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] attributes = line.split(";");

                Beans utilisateurs = createUser(attributes);

                users.add(utilisateurs);

                line = bufferedReader.readLine();
            }

        }
        catch (IOException ignored) {}
        return users;
    }

    public Beans createUser(String[] data) {
        Beans utilisateurs = new Beans();

        utilisateurs.setNom(data[0]);
        utilisateurs.setPrenom(data[1]);
        utilisateurs.setLogin(data[2]);
        utilisateurs.setEquipeSofie(data[3]);
        utilisateurs.setEquipe(data[4]);
        utilisateurs.setMotDePasse(data[5]);
        utilisateurs.setDerniereConnexion(LocalDate.parse(data[6], DateTimeFormatter.ISO_DATE));

        return utilisateurs;
    }


    public void remplirUser(String chemin) {
        List<Beans> users = readUserFromCsv(chemin);
        beansRepository.saveAll(users);
    }
}

