package spata.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class schedule {
    Long id;
    String name;
    String content;
    String password;
    String create_timestamp;
    String modify_timestamp;
}
