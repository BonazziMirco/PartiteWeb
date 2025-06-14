package unitn.bonazzi.partiteweb.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import unitn.bonazzi.partiteweb.pojos.Team;

import java.util.List;

@Repository
public class TeamRepository {
    private final JdbcTemplate jdbc;

    public TeamRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Team> getTeams(String sport){
        String sql = "SELECT * FROM teams WHERE sport=?";
        RowMapper<Team> teamRowMapper = (r, i) -> {
            Team rowObject = new Team();
            rowObject.setId(r.getInt("ID"));
            rowObject.setTeamName(r.getString("TEAMNAME"));
            return rowObject;
        };
        return jdbc.query(sql, teamRowMapper, sport);
    }

    public List<Team> getAllTeams(){
        String sql = "SELECT * FROM teams";
        RowMapper<Team> teamRowMapper = (r, i) -> {
            Team rowObject = new Team();
            rowObject.setId(r.getInt("ID"));
            rowObject.setTeamName(r.getString("TEAMNAME"));
            rowObject.setSport(r.getString("SPORT"));
            return rowObject;
        };
        return jdbc.query(sql, teamRowMapper);
    }

//    public String getTeamName(int id){
//        String sql = "SELECT * FROM teams WHERE ID=?";
//        RowMapper<String> teamNameRowMapper = (r, i) -> {
//            return r.getString("TEAMNAME");
//        };
//        return jdbc.queryForObject(sql,teamNameRowMapper, id);
//    }
}
