package net.worklogtracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.worklogtracker.dto.WorkLogResponse;
import net.worklogtracker.entity.UserEntity;
import net.worklogtracker.entity.WorkLogEntity;
import net.worklogtracker.repository.UserRepository;
import net.worklogtracker.repository.WorkLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class WorkLogService {

    private final ModelMapper modelMapper;
    private final WorkLogRepository workLogRepository;
    private final UserRepository userRepository;

    public void checkIn(String username, LocalDate date) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkLogEntity log = workLogRepository.findByUserAndDate(user, date)
                .orElse(WorkLogEntity.builder().user(user).date(date).build());

        if (log.getStart_time() == null) {
            log.setStart_time(LocalTime.now());
            workLogRepository.save(log);
        }
    }

    public void checkOut(String username, LocalDate date) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkLogEntity log = workLogRepository.findByUserAndDate(user, date)
                .orElseThrow(() -> new RuntimeException("No check-in record"));

        if (log.getEnd_time() == null) {
            log.setEnd_time(LocalTime.now());
            workLogRepository.save(log);
        }
    }

    public WorkLogResponse getLogByUserAndDate(String username, LocalDate date) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkLogEntity entity = workLogRepository.findByUserAndDate(user, date)
                .orElse(WorkLogEntity.builder().user(user).date(date).build());

        return WorkLogResponse.from(entity);
    }

    public List<WorkLogResponse> getLogsByDate(LocalDate date) {
        List<WorkLogEntity> entities = workLogRepository.findAllByDate(date);
        return entities.stream()
                .map(WorkLogResponse::from)
                .collect(Collectors.toList());
    }
}
