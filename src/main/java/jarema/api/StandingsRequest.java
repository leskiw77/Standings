package jarema.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jarema on 23.12.2016.
 */

public class StandingsRequest {
    private String home;
    private String away;
    private int goalsHome;
    private int goalsAway;

    public StandingsRequest(String home, String away, int goalsHome, int goalsAway) {
        this.home = home;
        this.away = away;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    public StandingsRequest(){}

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }
}
