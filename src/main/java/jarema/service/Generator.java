package jarema.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jarema on 22.12.2016.
 */
public class Generator {
    private List<Club> clubs = new LinkedList<Club>();
    private List<ClubStandings> standings = new ArrayList<ClubStandings>();

    public Generator(Club[] values) {
        for (Club c : values){
            clubs.add(c);
        }
    }


    public List<ClubStandings> generates(){
        for(Club club : clubs){
            standings.add(new ClubStandings(club));
        }

        genrateRounds();

        return standings;
    }

    private void genrateRounds(){
        int n=standings.size();
        LinkedList<Club> first = new LinkedList<Club>();
        LinkedList<Club> second = new LinkedList<Club>();

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

    private ClubStandings getClubStandingByName(Club name){
        for (ClubStandings club : standings){
            if(club.getClubName() == name)
                return club;
        }
        return null;
    }

    private void addMatchesInRound(List<Club> club1, List<Club> club2){
        for (int i=0;i<club1.size();i++){
            Club first = club1.get(i);
            Club second= club2.get(i);
            Match firstMatch = new Match(first,second);
            Match secondMatch = new Match(second,first);

            getClubStandingByName(first).addMatchToPlayFirst(firstMatch);
            getClubStandingByName(second).addMatchToPlayFirst(firstMatch);
            getClubStandingByName(first).addMatchToPlayLast(secondMatch);
            getClubStandingByName(second).addMatchToPlayLast(secondMatch);

        }
    }

    public static void main(String [] args){
        Generator generator = new Generator(Club.values());
        List<ClubStandings> list = generator.generates();

        System.out.println(list.get(0).getMatchesToPlay());

    }
}
