package jarema.model;

import jarema.service.Standings;
import org.springframework.stereotype.Repository;

/*
 * Created by Jarema on 28.12.2016.
 */

@Repository
public interface StandingsRepository {
    Standings getBySeason(int season);
    boolean add(Standings standings);
    boolean delete(Standings standings);
}
