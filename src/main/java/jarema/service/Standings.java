package jarema.service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jarema on 21.12.2016.
 */
public class Standings {
    private int teamsAmount = 16;
    private List<ClubStandings> standings;
    private int season;

    private Clubs teams;

    public Standings(int season, int teamsAmount, Clubs clubs) {
        this.season = season;
        this.teamsAmount = teamsAmount;
        this.teams = clubs;
        this.standings = new Generator(teams.getList()).generates();
    }

    public Standings(int season, Clubs clubs){
        this.season = season;
        this.teams = clubs;
        this.standings = new Generator(teams.getList()).generates();
    }

    public Standings(int season){
        this.season = season;
        this.teams = new GenerateTeamsForSeason().generate(season);
        this.standings = new Generator(teams.getList()).generates();
    }

    public ClubStandings getClubStandingsByName(String clubName){
        for (ClubStandings clubStandings : standings){
            if(clubStandings.getClubName().equals(clubName))
                return clubStandings;
        }
        return null;
    }

    public void sortTeams(){
        Collections.sort(standings,new ClubsComparator());
    }

    public int getSeason() {
        return season;
    }

    public boolean matchPlayed(String home, String away, int goalsHome, int goalsAway){

        Match match = new Match(home,away);
        match.setGoals(goalsHome,goalsAway);

        ClubStandings team1 = getClubStandingsByName(match.away);
        ClubStandings team2 = getClubStandingsByName(match.home);
        if(team1.getMatchesToPlay().getFirst() != team2.getMatchesToPlay().getFirst()){
            return false;
        }

        boolean toRet = team1.matchPlayed(match) && team2.matchPlayed(match);
        sortTeams();
        return toRet;
    }

    public Clubs getTeams() {
        return teams;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int i=1;
        for (ClubStandings s : standings){
            stringBuilder.append(i + ". " + s + "\n");
            i++;
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Standings standings = (Standings) o;
        return this.season == standings.season;
    }

}
