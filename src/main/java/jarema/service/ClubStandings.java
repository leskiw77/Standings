package jarema.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jarema on 21.12.2016.
 */

class ClubsComparator implements Comparator<ClubStandings>{
    public int compare(ClubStandings o1, ClubStandings o2) {
        if(o1.getPoints() != o2.getPoints())
            return o2.getPoints() - o1.getPoints();

        int goals = o1.getGoalsScored()-o1.getGoalsLoosed()-o2.getGoalsScored()+o2.getGoalsLoosed();
        if(goals != 0)
            return (-1) * goals;
        return o2.getGoalsScored() - o1.getGoalsScored();

    }
}

public class ClubStandings{

    public final String clubName;
    private int points;
    private int wins, draws, looses;
    private int goalsScored,goalsLoosed;
    private int playedMatches;

    private LinkedList<Match> matchesToPlay = new LinkedList<Match>();
    private LinkedList<Match> finishedMatches = new LinkedList<Match>();

    public ClubStandings(String clubName) {
        this.clubName = clubName;
    }

    public boolean matchPlayed(Match match){
        if(matchesToPlay.isEmpty() || !matchesToPlay.getFirst().equals(match)){
            return false;
        }

        playedMatches++;
        goalsLoosed += match.loosedBy(clubName);
        goalsScored += match.scoredBy(clubName);
        int points = match.pointReachBy(clubName);
        this.points += points;
        switch (points){
            case 3:
                wins++;
                break;
            case 1:
                draws++;
                break;
            case 0:
                looses++;
        }

        finishedMatches.addFirst(match);
        matchesToPlay.remove();

        return true;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clubName);
        stringBuilder.append(" - " + playedMatches + " ");
        stringBuilder.append(wins+"-"+draws+"-"+looses+" ");
        stringBuilder.append(goalsScored+":"+goalsLoosed+"  ");
        stringBuilder.append(points);
        return stringBuilder.toString();
    }

    public String getClubName() {
        return clubName;
    }

    public void addMatchToPlayLast(Match match) {
        matchesToPlay.addLast(match);
    }

    public void addMatchToPlayFirst(Match match)
    {
        matchesToPlay.addFirst(match);
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsLoosed() {
        return goalsLoosed;
    }

    public LinkedList<Match> getMatchesToPlay() {
        return matchesToPlay;
    }

    public List<Match> getNext(int howMany) {
        return  matchesToPlay.subList(0,Math.min(howMany,matchesToPlay.size()));
    }

    public List<Match> getPlayed(int howMany){
        return  finishedMatches.subList(0,Math.min(howMany,finishedMatches.size()));
    }
}
