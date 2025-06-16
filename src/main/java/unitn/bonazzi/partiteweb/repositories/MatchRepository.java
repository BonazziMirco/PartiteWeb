package unitn.bonazzi.partiteweb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import unitn.bonazzi.partiteweb.pojos.Match;
import unitn.bonazzi.partiteweb.pojos.Team;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Repository
public class MatchRepository {
    private final JdbcTemplate jdbc;
    private final TeamRepository teamRepo;

    public MatchRepository(JdbcTemplate jdbc, TeamRepository teamRepo) {
        this.jdbc = jdbc;
        this.teamRepo = teamRepo;
    }

    public List<Match> getMatches(String sport, LocalDate matchDate) {
        String sql = "SELECT * FROM matches WHERE sport=? and matchDate=?";
        RowMapper<Match> matchRowMapper = (r, i) -> {
            Match rowObject = new Match();
            rowObject.setId(r.getInt("ID"));
            rowObject.setHomeTeam(r.getString("HOMETEAM"));
            rowObject.setAwayTeam(r.getString("AWAYTEAM"));
            return rowObject;
        };
        return jdbc.query(sql, matchRowMapper, sport, matchDate);
    }

    public List<Integer> getResults(String sport, LocalDate matchDate) {
        String sql = "SELECT * FROM matches WHERE sport=? and matchDate=?";
        RowMapper<Integer> matchRowMapper = (r, i) -> {
            return r.getInt("MATCHRESULT");
        };
        return jdbc.query(sql, matchRowMapper, sport, matchDate);
    }

    public void createMatches(String sport) {
        String sql = "INSERT INTO matches (matchDate, homeTeam, awayTeam, sport, matchResult) VALUES (?, ?, ?, ?, ?)";
        List<Team> teams = teamRepo.getTeams(sport);
        Random random = new Random();
        int id1, id2;
        for (int i = 0; i < random.nextInt(5)+5; i++){
            id1 = random.nextInt(teams.size());
            do{
                id2 = random.nextInt(teams.size());
            }while (id1 == id2);
            jdbc.update(sql,
                    LocalDate.now(),
                    teams.get(id1).getTeamName(),
                    teams.get(id2).getTeamName(),
                    sport,
                    random.nextInt(3)
            );
        }

    }

    public List<Match> getAllMatches(String sport) {
        String sql = "SELECT * FROM matches WHERE sport=?";
        RowMapper<Match> matchRowMapper = (r, i) -> {
            Match rowObject = new Match();
            rowObject.setId(r.getInt("ID"));
            rowObject.setMatchDate(r.getDate("matchDate").toLocalDate());
            rowObject.setHomeTeam(r.getString("HOMETEAM"));
            rowObject.setAwayTeam(r.getString("AWAYTEAM"));
            return rowObject;
        };
        return jdbc.query(sql, matchRowMapper, sport);
    }
}
