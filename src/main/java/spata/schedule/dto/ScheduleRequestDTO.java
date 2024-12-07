package spata.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDTO {
    String userid;
    String name;
    String contents;
    String password;
    String email;
}
