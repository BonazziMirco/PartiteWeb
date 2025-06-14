package unitn.bonazzi.partiteweb;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import unitn.bonazzi.partiteweb.pojos.Match;
import unitn.bonazzi.partiteweb.pojos.Team;
import unitn.bonazzi.partiteweb.repositories.MatchRepository;
import unitn.bonazzi.partiteweb.repositories.TeamRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PublicController {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public PublicController(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @PostMapping(value = "/matchList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Match> matchList(@RequestParam String sport, @RequestParam LocalDate date) {
        return matchRepository.getMatches(sport, date);
    }

    @PostMapping(value = "/matchCalendar", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Match> matchCalendar(@RequestParam String sport) {
        return matchRepository.getAllMatches(sport);
    }

    @PostMapping(value = "/getTeams", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getTeams(@RequestParam String sport) {
        return teamRepository.getTeams(sport);
    }

    @GetMapping(value = "/getAllTeams", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    @PostMapping(value = "/createMatches")
    public void createMatches(@RequestBody String sport) {
        matchRepository.createMatches(sport);
    }

    @PostMapping(value = "/getResults", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getResults(@RequestParam String sport,
                                  @RequestParam LocalDate date,
                                  @RequestParam List<Integer> predictions) {
        return matchRepository.getResults(sport, date, predictions);
    }
}
