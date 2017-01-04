package jarema.service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jarema on 23.12.2016.
 */
public interface StandingsService {

    public Optional<Club> teamExists(String team);
    public boolean add(Standings standing);
    public boolean remove(Standings standing);
    public Standings getBySeason(int season);

}
