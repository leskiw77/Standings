package jarema.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jarema on 22.12.2016.
 */
public class Generator {
    private List<String> clubs;
    private List<ClubStandings> standings = new ArrayList<ClubStandings>();

    public Generator(List <String> list) {
        clubs = list;
    }


    public List<ClubStandings> generates(){
        for(String club : clubs){
            standings.add(new ClubStandings(club));
        }

        genrateRounds();

        return standings;
    }

    private void genrateRounds(){
        int n=standings.size();
        LinkedList<String> first = new LinkedList<>();
        LinkedList<String> second = new LinkedList<>();

        for (int i=0;i<(n/2);i++){
            first.addLast(clubs.get(i));
            second.addLast(clubs.get(n-1-i));
        }


        for(int i=0;i<n-1;i++) {
            first.add(1, second.remove());
            second.addLast(first.removeLast());

            if(i%2 == 0){
                addMatchesInRound(first,second);
            }
            else {
                addMatchesInRound(second,first);
            }
        }
    }

    private ClubStandings getClubStandingByName(String name){
        for (ClubStandings club : standings){
            if(club.getClubName() == name)
                return club;
        }
        return null;
    }

    private void addMatchesInRound(List<String> club1, List<String> club2){
        for (int i=0;i<club1.size();i++){
            String first = club1.get(i);
            String second= club2.get(i);
            Match firstMatch = new Match(first,second);
            Match secondMatch = new Match(second,first);

            getClubStandingByName(first).addMatchToPlayFirst(firstMatch);
            getClubStandingByName(second).addMatchToPlayFirst(firstMatch);
            getClubStandingByName(first).addMatchToPlayLast(secondMatch);
            getClubStandingByName(second).addMatchToPlayLast(secondMatch);

        }
    }

    public static void main(String [] args){
        Path file = Paths.get("E:\\Dokumenty\\Studia\\Kody\\Java\\project\\src\\main\\java\\ioTest\\file.txt");


        try (InputStream in = Files.newInputStream(file)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line = reader.readLine();
                List<String> list = Arrays.asList(line.split(","));
                System.out.println(list);
                new Generator(list).generates();

            }
        } catch (IOException x) {
            System.err.println(x);
        }

    }
}
