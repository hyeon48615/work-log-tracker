package net.worklogtracker.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.worklogtracker.entity.WorkLogEntity;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkLogResponse {
    private Long id;
    private LocalDate date;
    private LocalTime start_time;
    private LocalTime end_time;
    private String username;

    public static WorkLogResponse from(WorkLogEntity entity) {
        return new WorkLogResponse(
                entity.getId(),
                entity.getDate(),
                entity.getStart_time(),
                entity.getEnd_time(),
                entity.getUser().getUsername()
        );
    }
}
