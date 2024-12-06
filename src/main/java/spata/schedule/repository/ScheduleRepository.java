package spata.schedule.repository;

import spata.schedule.dto.ScheduleResponseDTO;

import java.util.Optional;

public interface ScheduleRepository {
    Optional<ScheduleResponseDTO> create_schedule(String name,String contents,String password);
    Optional<ScheduleResponseDTO> findScheduleById(Long id);
}
