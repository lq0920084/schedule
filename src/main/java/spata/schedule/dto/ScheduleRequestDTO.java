package spata.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDTO {
    String name;
    String content;
    String password;
    String create_timestamp;
    String modify_timestamp;
}
