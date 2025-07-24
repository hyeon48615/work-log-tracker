package net.worklogtracker.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.worklogtracker.dto.WorkLogResponse;
import net.worklogtracker.entity.WorkLogEntity;
import net.worklogtracker.service.WorkLogService;
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
@RequestMapping("/")
public class WorkLogController {

    private final WorkLogService workLogService;

    @GetMapping
    public String home(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) LocalDate date,
            Model model
    ) {
        if (date == null) date = LocalDate.now();

        WorkLogResponse mylog = workLogService.getLogByUserAndDate(userDetails.getUsername(), date);
        List<WorkLogResponse> logs = workLogService.getLogsByDate(date);

        model.addAttribute("mylog", mylog);
        model.addAttribute("logs", logs);
        return "main";
    }
}
