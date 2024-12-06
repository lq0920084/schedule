package spata.schedule.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import spata.schedule.dto.ScheduleResponseDTO;
import spata.schedule.repository.ScheduleRepository;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public ScheduleResponseDTO createSchedule(String name, String contents, String password) {
        String encryptedPassword = encryptPassword(password);
        if(password==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Create Failed, Input Data error");
        }
        return scheduleRepository.createSchedule(name,contents,encryptedPassword).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Create Failed, Input Data error"));
    }

    @Override
    public List<ScheduleResponseDTO> findAllSchedule(String name,String date){
        if(name==null&&date==null){
            return scheduleRepository.findAllSchedule();
        }else if(date==null){
            return scheduleRepository.findScheduleByName(name);

        }else if(name==null){
            return scheduleRepository.findScheduleByDate(date);
        }
        else {
            return scheduleRepository.findScheduleByNameAndDate(name,date);
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
}
