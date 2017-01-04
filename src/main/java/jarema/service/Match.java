package jarema.service;

/**
 * Created by Jarema on 21.12.2016.
 */

public class Match {
    public Match(Club home, Club away) {
        this.home = home;
        this.away = away;
        played=false;
    }

    boolean played;

    public final Club home;
    public final Club away;

    private int homeGoals;
    private int awayGoals;

    public void setGoals(int home, int away) {
        this.homeGoals = home;
        this.awayGoals = away;
        played = true;
    }

    public int scoredBy(Club c){
        if(c == home)
            return homeGoals;
        else
            return awayGoals;
    }

    public int loosedBy(Club c){
        if(c == home)
            return awayGoals;
        else
            return homeGoals;
    }

    public int pointReachBy(Club c){
        if(c == home){
            if(homeGoals>awayGoals)
                return 3;
            if(homeGoals==awayGoals)
                return 1;
            return 0;
        }
        else {
            if(homeGoals<awayGoals)
                return 3;
            if(homeGoals==awayGoals)
                return 1;
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (home != match.home) return false;
        return away == match.away;
    }

    @Override
    public String toString(){
        if(played){
            return home + " " + homeGoals + " : " + awayGoals + " " + away;
        }
        else {
            return home +  " : " + away + "\n";
        }
    }
}
