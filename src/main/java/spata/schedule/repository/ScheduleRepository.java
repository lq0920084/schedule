package spata.schedule.repository;

import spata.schedule.dto.ScheduleResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Optional<ScheduleResponseDTO> createSchedule(String name,String contents,String password);
    Optional<ScheduleResponseDTO> findScheduleById(Long id);
    List<ScheduleResponseDTO> findAllSchedule();
    List<ScheduleResponseDTO>  findScheduleByName(String name);
    List<ScheduleResponseDTO> findScheduleByDate(String date);
    List<ScheduleResponseDTO> findScheduleByNameAndDate(String name,String date);
}
