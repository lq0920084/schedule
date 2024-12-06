package spata.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDTO {
    Long id;
    String name;
    String content;
    String password;
    String create_timestamp;
    String modify_timestamp;

    public ScheduleResponseDTO(String name,
                               String contents,
                               String password){
        this.name = name;
        this.content = contents;
        this.password = password;
    }
}
