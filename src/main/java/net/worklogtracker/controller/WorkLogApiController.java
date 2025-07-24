package net.worklogtracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.worklogtracker.dto.WorkLogResponse;
import net.worklogtracker.service.WorkLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/log")
public class WorkLogApiController {

    private final WorkLogService workLogService;

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(@AuthenticationPrincipal UserDetails user) {
        try {
            workLogService.checkIn(user.getUsername(), LocalDate.now());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("출근 기록 처리 중 오류가 발생했습니다.");
        }

    }

    @PostMapping("/check-out")
    public ResponseEntity<?> checkOut(@AuthenticationPrincipal UserDetails user) {
        try {
            workLogService.checkOut(user.getUsername(), LocalDate.now());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("퇴근 기록 처리 중 오류가 발생했습니다.");
        }
        
    }
}
