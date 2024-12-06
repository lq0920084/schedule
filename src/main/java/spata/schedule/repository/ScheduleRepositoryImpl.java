package spata.schedule.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import spata.schedule.dto.ScheduleResponseDTO;

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
    public Optional<ScheduleResponseDTO> createSchedule(String name, String contents, String password) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("name",name);
        parameters.put("contents",contents);
        parameters.put("password",password);

        Timestamp nowDateAndTime = Timestamp.valueOf(LocalDateTime.now());
        parameters.put("create_timestamp",nowDateAndTime);
        parameters.put("modify_timeStamp",nowDateAndTime);
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        //제대로 저장되었능지를 조회하기 위해 findScheduleById메서드를 후출하여 리턴받습니다.
        return findScheduleById(key.longValue());
    }


    public Optional<ScheduleResponseDTO> findScheduleById(Long id){
        List<ScheduleResponseDTO> result = jdbcTemplate.query("SELECT id,name,contents,create_timestamp,modify_timestamp FROM schedule where id=?", scheduleFindRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<ScheduleResponseDTO> findAllSchedule() {
        return jdbcTemplate.query("SELECT id,name,contents,create_timestamp,modify_timestamp FROM schedule ORDER BY modify_timestamp DESC", scheduleFindRowMapper());
    }

    @Override
    public List<ScheduleResponseDTO> findScheduleByName(String name) {
        return jdbcTemplate.query("SELECT id,name,contents,create_timestamp,modify_timestamp FROM schedule WHERE name=? ORDER BY modify_timestamp DESC",scheduleFindRowMapper(),name);
    }

    @Override
    public List<ScheduleResponseDTO> findScheduleByDate(String date) {
        return jdbcTemplate.query("SELECT id,name,contents,create_timestamp,modify_timestamp FROM schedule WHERE Date(modify_timestamp)=? ORDER BY modify_timestamp DESC",scheduleFindRowMapper(),date);
    }

    @Override
    public List<ScheduleResponseDTO> findScheduleByNameAndDate(String name, String date) {
        return jdbcTemplate.query("SELECT id,name,contents,create_timestamp,modify_timestamp FROM schedule WHERE name=? AND Date(modify_timestamp)=? ORDER BY modify_timestamp DESC",scheduleFindRowMapper(),name,date);
    }


    private RowMapper<ScheduleResponseDTO> scheduleFindRowMapper(){

        return new RowMapper<ScheduleResponseDTO> (){

            @Override
            public ScheduleResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new ScheduleResponseDTO(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("contents"),
                        rs.getTimestamp("create_timestamp").toString(),
                        rs.getTimestamp("modify_timestamp").toString());
            }


        };
    }
}



