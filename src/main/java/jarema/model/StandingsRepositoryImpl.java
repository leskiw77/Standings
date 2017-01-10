package jarema.model;

import jarema.service.Standings;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jarema on 28.12.2016.
 */

@Service
public class StandingsRepositoryImpl implements StandingsRepository {

    private Set<Standings> set = new HashSet<>();

    @Override
    public Standings getBySeason(int season) {
        for (Standings standing : set){
            if(standing.getSeason() == season)
                return standing;
        }
        return null;
    }

    @Override
    public boolean add(Standings standing) {
        for(Standings stands : set){
            if(stands.equals(standing))
                return false;
        }
        set.add(standing);
        return true;
    }

    @Override
    public boolean delete(Standings standings) {
        return set.remove(standings);
    }

}