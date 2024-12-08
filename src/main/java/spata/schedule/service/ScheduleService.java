package spata.schedule.service;

import spata.schedule.dto.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO createSchedule(String userid,String name, String contents, String password,String email);
    List<ScheduleResponseDTO> findAllSchedule(String userid,String date);

    ScheduleResponseDTO findScheduleById(Long id);

    ScheduleResponseDTO reWriteScheduleById(Long id,String name,String contents,String password);

    void deleteScheduleById(Long id,String password);
}
