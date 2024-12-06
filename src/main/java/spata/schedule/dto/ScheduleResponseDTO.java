package spata.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spata.schedule.entity.Schedule;

@Getter
@AllArgsConstructor
public class ScheduleResponseDTO {
    Long id;
    String name;
    String contents;
    String create_timestamp;
    String modify_timestamp;

    public ScheduleResponseDTO(Schedule schedule){
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.contents = schedule.getContents();
        this.create_timestamp = schedule.getCreate_timestamp();
        this.modify_timestamp = schedule.getModify_timestamp();
    }


}
