package spata.schedule.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import spata.schedule.dto.ScheduleResponseDTO;
import spata.schedule.entity.Schedule;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    JdbcTemplate jdbcTemplate;
    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Schedule> createSchedule(String userid,String name,String contents,String password,String email) {
        SimpleJdbcInsert jdbcInsertSchedule = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsertSchedule.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String,Object> parameters = new HashMap<>();
        parameters.clear();
        parameters.put("userid",userid);
        parameters.put("contents",contents);
        parameters.put("password",password);
        Number key = jdbcInsertSchedule.executeAndReturnKey(new MapSqlParameterSource(parameters));

        SimpleJdbcInsert jdbcInsertUser = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsertUser.withTableName("user");

        parameters.put("userid",userid);
        parameters.put("name",name);
        parameters.put("email",email);
        Timestamp nowDateAndTime = Timestamp.valueOf(LocalDateTime.now());
        parameters.put("create_timestamp",nowDateAndTime);
        parameters.put("modify_timeStamp",nowDateAndTime);
        jdbcInsertUser.execute(new MapSqlParameterSource(parameters));





        //제대로 저장되었능지를 조회하기 위해 findScheduleById메서드를 후출하여 리턴받습니다.
        return findScheduleById(key.longValue());
    }


    public Optional<Schedule> findScheduleById(Long id){
        List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule s JOIN user u ON s.userid=u.userid where s.id=?", scheduleFindRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Schedule> findAllSchedule() {
        return jdbcTemplate.query("SELECT * FROM schedule s JOIN user u ON s.userid=u.userid ORDER BY modify_timestamp DESC", scheduleFindRowMapper());
    }

    @Override
    public List<Schedule> findScheduleByName(String name) {
        return jdbcTemplate.query("SELECT * FROM schedule s JOIN user u ON s.userid=u.userid WHERE s.name=? ORDER BY u.modify_timestamp DESC",scheduleFindRowMapper(),name);
    }

    @Override
    public List<Schedule> findScheduleByDate(String date) {
        return jdbcTemplate.query("SELECT * FROM schedule s JOIN user u ON s.userid=u.userid WHERE Date(u.modify_timestamp)=? ORDER BY u.modify_timestamp DESC",scheduleFindRowMapper(),date);
    }

    @Override
    public List<Schedule> findScheduleByNameAndDate(String name, String date) {
        return jdbcTemplate.query("SELECT * FROM schedule s JOIN user u ON s.userid=u.userid WHERE u.name=? AND Date(u.modify_timestamp)=? ORDER BY u.modify_timestamp DESC",scheduleFindRowMapper(),name,date);
    }

    @Override
    public int reWriteScheduleById(Long id, String name, String Contents) {
        Timestamp nowDateAndTime = Timestamp.valueOf(LocalDateTime.now());
        return jdbcTemplate.update("UPDATE schedule SET name=?,contents=?,modify_timestamp=? WHERE id=? ",name,Contents,nowDateAndTime,id);
    }

    @Override
    public void deleteScheduleById(Long id){
        jdbcTemplate.update("DELETE FROM schedule WHERE id=?",id);
    }

    private RowMapper<Schedule> scheduleFindRowMapper(){

        return new RowMapper<Schedule> (){

            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("s.userid"),
                        rs.getString("name"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("create_timestamp").toString(),
                        rs.getTimestamp("modify_timestamp").toString());
            }


        };
    }
}



