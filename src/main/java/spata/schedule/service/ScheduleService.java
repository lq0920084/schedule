package spata.schedule.service;

import spata.schedule.dto.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO createSchedule(String name,
                                               String contents,
                                               String password);
    List<ScheduleResponseDTO> findAllSchedule(String name,String date);

    ScheduleResponseDTO findScheduleById(Long id);

    ScheduleResponseDTO reWriteScheduleById(Long id,String name,String contents,String password);

    void deleteScheduleById(Long id,String password);
}
