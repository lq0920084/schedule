package spata.schedule.repository;

import spata.schedule.dto.ScheduleResponseDTO;
import spata.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Optional<Schedule> createSchedule(String name,String contents,String password);
    Optional<Schedule> findScheduleById(Long id);
    List<Schedule> findAllSchedule();
    List<Schedule>  findScheduleByName(String name);
    List<Schedule> findScheduleByDate(String date);
    List<Schedule> findScheduleByNameAndDate(String name,String date);
    int reWriteScheduleById(Long id,String name, String Contents);
}
