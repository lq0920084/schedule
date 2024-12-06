package spata.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spata.schedule.dto.ScheduleRequestDTO;
import spata.schedule.dto.ScheduleResponseDTO;
import spata.schedule.service.ScheduleService;

import java.util.List;

// "/api하위에서 작동하도록 최상단에 맵핑합니다.";
@RestController
@RequestMapping("/api")
public class ScheduleController {
    ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

@PostMapping("/schedule")
public ResponseEntity<ScheduleResponseDTO> createSchedule(@RequestBody ScheduleRequestDTO dto){

        return new ResponseEntity<>(scheduleService.createSchedule(dto.getName(),dto.getContents(),dto.getPassword()), HttpStatus.CREATED);
}

@GetMapping("/schedule")
public List<ScheduleResponseDTO> findAllSchedule(@RequestParam(value="name",required=false)String name,
                                                 @RequestParam(value="date",required=false) String date){



        return scheduleService.findAllSchedule(name,date);
}

@GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDTO> findScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleById(id),HttpStatus.OK);
}

@PutMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDTO> reWriteScheduleById(@PathVariable Long id,
                                                                   @RequestBody ScheduleRequestDTO dto){

        return new ResponseEntity<>(scheduleService.reWriteScheduleById(id,dto.getName(),dto.getContents(),dto.getPassword()),HttpStatus.OK);
}

@DeleteMapping("/schedule/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long id,
                                                                  @RequestBody ScheduleRequestDTO dto){
    scheduleService.deleteScheduleById(id,dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
}
}
