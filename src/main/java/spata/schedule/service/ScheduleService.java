package spata.schedule.service;

import spata.schedule.dto.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    public ScheduleResponseDTO createSchedule(String name,
                                               String contents,
                                               String password);
    public List<ScheduleResponseDTO> findAllSchedule(String name,String date);

}
