package spata.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spata.schedule.dto.ScheduleRequestDTO;
import spata.schedule.dto.ScheduleResponseDTO;
import spata.schedule.service.ScheduleService;

// "/api하위에서 작동하도록 최상단에 맵핑합니다.";
@RestController
@RequestMapping("/api")
public class ScheduleController {
    ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

@PostMapping("/schedule")
public ResponseEntity<ScheduleResponseDTO> create_schedule(@RequestBody ScheduleRequestDTO dto){

        return new ResponseEntity<>(scheduleService.create_schedule(dto.getName(),dto.getContents(),dto.getPassword()), HttpStatus.CREATED);
}

}
