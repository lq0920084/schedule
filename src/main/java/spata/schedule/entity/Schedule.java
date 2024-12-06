package spata.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {
    Long id;
    String name;
    String contents;
    String password;
    String create_timestamp;
    String modify_timestamp;

}
