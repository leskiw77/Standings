package jarema.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jarema on 05.01.2017.
 */
public class GenerateTeamsForSeason {
    public Clubs generate(int season){
        Path file = Paths.get("E:\\Dokumenty\\Studia\\Kody\\Java\\project\\src\\main\\java\\ioTest\\file.txt");

        System.out.println("Jestem w generowaniu");

        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine().toUpperCase();
            List<String> list = Arrays.asList(line.split(","));
            System.out.println(list);
            return new Clubs(list);

        } catch (IOException x) {
            System.err.println(x);
        }
        return null;
    }
}
