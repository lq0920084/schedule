package spata.schedule.repository;

import spata.schedule.dto.ScheduleResponseDTO;
import spata.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Optional<Schedule> createSchedule(String userid,String name,String contents,String password,String email);
    Optional<Schedule> findScheduleById(Long id);
    List<Schedule> findAllSchedule();
    List<Schedule>  findScheduleByUserid(String userid);
    List<Schedule> findScheduleByDate(String date);
    List<Schedule> findScheduleByUseridAndDate(String userid,String date);
    int reWriteScheduleById(Long id,String name, String Contents);
    void deleteScheduleById(Long id);
}
