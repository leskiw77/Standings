package jarema.service;

/**
 * Created by Jarema on 21.12.2016.
 */

public class Match {
    public Match(String home, String away) {
        this.home = home;
        this.away = away;
        played=false;
    }

    boolean played;

    public final String home;
    public final String away;

    private int homeGoals;
    private int awayGoals;

    public void setGoals(int home, int away) {
        this.homeGoals = home;
        this.awayGoals = away;
        played = true;
    }

    public int scoredBy(String c){
        if(c.equals(home))
            return homeGoals;
        else
            return awayGoals;
    }

    public int loosedBy(String c){
        if(c.equals(home))
            return awayGoals;
        else
            return homeGoals;
    }

    public int pointReachBy(String c){
        if(c.equals(home)){
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
        //TODO: gdzies sie jebie
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        if(match.home == home && match.away == away)
            return true;
        return false;
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
