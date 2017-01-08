package jarema.api;

import jarema.service.Clubs;
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
        Clubs clubs = standingsService.readClubsFromFile(season);
        if (clubs == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(standingsService.add(new Standings(season,clubs)))
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "{season}/addMatch")
    public ResponseEntity addMatch(@PathVariable int season,@RequestBody StandingsRequest standingsRequest){

        Standings standings = standingsService.getBySeason(season);
        if(standings == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);



        if(! standingsService.teamExists(standingsRequest.getHome(),season)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(! standingsService.teamExists(standingsRequest.getAway(),season)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


        String home = standingsRequest.getHome();
        String away = standingsRequest.getAway();


        if(standings.matchPlayed(home,away,standingsRequest.getGoalsHome(),standingsRequest.getGoalsAway())){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);

    }

    @RequestMapping(value = "clubsInSeason/{season}")
    public ResponseEntity clubsInSeason(@PathVariable int season,@RequestBody String clubStr){
        if(standingsService.writeClubsToFile(season,clubStr))
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}