package jarema.service;

import java.util.Optional;

/**
 * Created by Jarema on 23.12.2016.
 */
public interface StandingsService {

    public boolean teamExists(String team, int season);
    public boolean add(Standings standing);
    public boolean remove(Standings standing);
    public Standings getBySeason(int season);

}
