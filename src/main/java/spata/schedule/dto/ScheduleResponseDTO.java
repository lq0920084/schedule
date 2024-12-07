package spata.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spata.schedule.entity.Schedule;

@Getter
@AllArgsConstructor
public class ScheduleResponseDTO {
    Long id;
    String userid;
    String name;
    String contents;
    String email;
    String create_timestamp;
    String modify_timestamp;

    public ScheduleResponseDTO(Schedule schedule){
        this.id = schedule.getId();
        this.userid = schedule.getUserid();
        this.name = schedule.getName();
        this.contents = schedule.getContents();
        this.email = schedule.getEmail();
        this.create_timestamp = schedule.getCreate_timestamp();
        this.modify_timestamp = schedule.getModify_timestamp();
    }


}
