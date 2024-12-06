package spata.schedule.service;

import spata.schedule.dto.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {
    public ScheduleResponseDTO create_schedule(String name,
                                               String contents,
                                               String password);


}
