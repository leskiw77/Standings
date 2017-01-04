package jarema.api;

import jarema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jarema on 23.12.2016.
 */

@RestController
@RequestMapping(value = "api/", method = RequestMethod.GET)
public class StandingsReader {

    private final StandingsService standingsService;

    @Autowired
    public StandingsReader(StandingsService standingsService) {
        this.standingsService = standingsService;
    }

    @RequestMapping(value = "{season}/all")
    public ResponseEntity<String> showOne(@PathVariable int season){
        Standings s = standingsService.getBySeason(season);
        if(s == null)
            return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
        return new ResponseEntity <> (s.toString(),HttpStatus.OK);
    }

    @RequestMapping(value = "{season}/next")
    public ResponseEntity<String> nextMatchForTeam(@PathVariable int season, @RequestParam(value = "name" ) String name,
                                                   @RequestParam(value = "howMany") int howMany){
        Optional<Club> club = standingsService.teamExists(name);

        if(!club.isPresent())
            return new ResponseEntity(HttpStatus.CONFLICT);

        Standings s = standingsService.getBySeason(season);
        if(s == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List <Match> list = s.getClubStandingsByName(club.get()).getNext(howMany);

        return new ResponseEntity<>(list.toString(),HttpStatus.OK);
    }

    @RequestMapping(value = "{season}/played")
    public ResponseEntity<String> playedMatchForTeam(@PathVariable int season, @RequestParam(value = "name" ) String name,
                                                   @RequestParam(value = "howMany") int howMany){
        Optional<Club> club;
        club = standingsService.teamExists(name);

        if(!club.isPresent())
            return new ResponseEntity(HttpStatus.CONFLICT);

        Standings s = standingsService.getBySeason(season);
        if(s == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        List <Match> list = s.getClubStandingsByName(club.get()).getPlayed(howMany);

        return new ResponseEntity<>(list.toString(),HttpStatus.OK);
    }

}
