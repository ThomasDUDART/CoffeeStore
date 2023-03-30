package BDDInterraction;



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

        utilisateurs.setIdBeans(Integer.parseInt(data[0]));
        utilisateurs.setNom(data[1]);
        utilisateurs.setPoidsUnit(Integer.parseInt(data[2]));
        utilisateurs.setPrixUnit(Integer.parseInt(data[3]));
        utilisateurs.setUnit(Integer.parseInt(data[4]));
        utilisateurs.setScore(Integer.parseInt(data[5]));
        utilisateurs.setTorrefaction(Integer.parseInt(data[6]));
        utilisateurs.setOrigineId(Integer.parseInt(data[7]));

        return utilisateurs;
    }


    public void remplirUser(String chemin) {
        List<Beans> users = readUserFromCsv(chemin);
        beansRepository.saveAll(users);
    }
}

