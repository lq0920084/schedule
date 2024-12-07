package spata.schedule.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import spata.schedule.dto.ScheduleResponseDTO;
import spata.schedule.entity.Schedule;
import spata.schedule.repository.ScheduleRepository;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    // 스케줄레포지토리 객체 할당.
    ScheduleRepository scheduleRepository;
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository=scheduleRepository;
    }

    //스케줄 생성 기능 구현.
    @Transactional
    @Override
    public ScheduleResponseDTO createSchedule(String userid,String name, String contents, String password,String email) {
        String encryptedPassword = encryptPassword(password);
        if(password==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Create Failed, Input Data error");
        }
        return ScheduleToDTO(scheduleRepository.createSchedule(userid,name,contents,encryptedPassword,email).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Create Failed, Input Data error")));
    }

    @Override
    public List<ScheduleResponseDTO> findAllSchedule(String name,String date){
        if(name==null&&date==null){
            return  scheduleListToDTO(scheduleRepository.findAllSchedule());
        }else if(date==null){
            return scheduleListToDTO(scheduleRepository.findScheduleByName(name));

        }else if(name==null){
            return scheduleListToDTO(scheduleRepository.findScheduleByDate(date));
        }
        else {
            return scheduleListToDTO(scheduleRepository.findScheduleByNameAndDate(name,date));
        }
    }

    public ScheduleResponseDTO findScheduleById(Long id){
            return ScheduleToDTO(scheduleRepository.findScheduleById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"does not Exist id="+id)));
    }

    @Override
    public ScheduleResponseDTO reWriteScheduleById(Long id, String name, String contents, String password) {
        if(passwordValidation(password,id)){
            scheduleRepository.reWriteScheduleById(id,name,contents);
               return findScheduleById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"does not matched password");
        }
    }

    @Override
    public void deleteScheduleById(Long id,String password) {
       if(passwordValidation(password,id)){
           scheduleRepository.deleteScheduleById(id);
       }else {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"does not matched password");
       }
    }

    //패스워드를 암호화하는 부분은 일정 생성 시 뿐만 아니라 수정과 삭제시에도 사용될 수 있으므로, 별개의 메서드로 분리합니다.
    private String encryptPassword(String password){
        //암호로 레인보우 테이블을 생성할 수 없도록 Salt를 추가합니다.
        password = password+"sparta";
        try {
            MessageDigest encryptInstance = MessageDigest.getInstance("SHA-256");
            byte[] encryptedPasswordToByte = encryptInstance.digest(password.getBytes(Charset.forName("UTF-8")));
            return String.format("%032X",new BigInteger(1,encryptedPasswordToByte));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //비밀번호를 제외한 데이터를 출력하기 위해 전체 리스트에서 비밀번호를 뺀 나머지를 반환합니다.
    private List<ScheduleResponseDTO> scheduleListToDTO(List<Schedule>  original){
        List<ScheduleResponseDTO>  destination =new ArrayList<ScheduleResponseDTO>();
        for(Schedule source : original){
            destination.add(new ScheduleResponseDTO(source.getId(),
                                                    source.getUserid(),
                                                    source.getName(),
                                                    source.getContents(),
                                                    source.getEmail(),
                                                    source.getCreate_timestamp(),
                                                    source.getModify_timestamp()));
        }
        return destination;
    }

    //비밀번호를 제외한 데이터를 출력하기 위해 전체 값에서 비밀번호를 뺀 나머지를 반환합니다.
    private ScheduleResponseDTO ScheduleToDTO(Schedule original){
         return new ScheduleResponseDTO(original.getId(),
                                        original.getUserid(),
                                        original.getName(),
                                        original.getContents(),
                                        original.getEmail(),
                                        original.getCreate_timestamp(),
                                        original.getModify_timestamp());
    }


    private boolean passwordValidation(String password,Long id){
        String savedPassword = scheduleRepository.findScheduleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"does not Exist id="+id))
                .getPassword();
        return encryptPassword(password).equals(savedPassword);
    }

}
