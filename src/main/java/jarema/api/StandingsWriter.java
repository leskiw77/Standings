package jarema.api;

import jarema.service.Club;
import jarema.service.Match;
import jarema.service.Standings;
import jarema.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Jarema on 23.12.2016.
 */

@RestController
@RequestMapping(value = "api/", method = RequestMethod.POST)
public class StandingsWriter {

    private final StandingsService standingsService;

    @Autowired
    public StandingsWriter(StandingsService standingsService) {
        this.standingsService = standingsService;
    }

    @RequestMapping(value = "create")
    public ResponseEntity createStandings(@RequestParam(value = "season") int season){
        if(standingsService.add(new Standings(season)))
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "{season}/addMatch")
    public ResponseEntity addMatch(@PathVariable int season,@RequestBody StandingsRequest standingsRequest){

        Standings standings = standingsService.getBySeason(season);
        if(standings == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        //TODO: moze wyjatek
        Optional<Club> home = standingsService.teamExists(standingsRequest.getHome());
        Optional<Club> away = standingsService.teamExists(standingsRequest.getAway());

        if(!home.isPresent() || !away.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(standings.matchPlayed(home.get(),away.get(),standingsRequest.getGoalsHome(),standingsRequest.getGoalsAway())){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
}