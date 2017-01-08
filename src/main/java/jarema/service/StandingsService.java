package jarema.service;

import java.util.Optional;

/**
 * Created by Jarema on 23.12.2016.
 */
public interface StandingsService {

    boolean teamExists(String team, int season);
    boolean add(Standings standing);
    boolean remove(Standings standing);
    Standings getBySeason(int season);
    Clubs readClubsFromFile(int season);
    boolean writeClubsToFile(int season, String str);

}
