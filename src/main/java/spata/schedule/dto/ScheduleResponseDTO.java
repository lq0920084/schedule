package spata.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDTO {
    Long id;
    String name;
    String contents;
    String create_timestamp;
    String modify_timestamp;

//    public ScheduleResponseDTO(Long id,
//                               String name,
//                               String contents,
//                               String create_timestamp,
//                               String modify_timestamp){
//        this.id = id;
//        this.name =name;
//        this.contents = contents;
//        this.create_timestamp = create_timestamp;
//        this.modify_timestamp = modify_timestamp;
//    }

}
