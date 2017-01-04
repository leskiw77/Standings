package jarema.service;

import jarema.model.StandingsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jarema on 25.12.2016.
 */

@Service
public class StandingsServiceImpl implements StandingsService {

    private final StandingsRepositoryImpl standingsRepository;

    @Autowired
    public StandingsServiceImpl(StandingsRepositoryImpl standings) {
        this.standingsRepository = standings;
    }

    @Override
    public Optional<Club> teamExists(String team) {
        Club c;
        try {
            c = Club.valueOf(team.toUpperCase());
        }catch (IllegalArgumentException e){
            return Optional.empty();
        }
        return Optional.of(c);
    }

    @Override
    public boolean add(Standings standing) {
        return standingsRepository.add(standing);
    }

    @Override
    public boolean remove(Standings standing) {
        return standingsRepository.delete(standing);
    }

    @Override
    public Standings getBySeason(int season) {
        return standingsRepository.getBySeason(season);
    }
}
