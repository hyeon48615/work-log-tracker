package net.worklogtracker.repository;

import net.worklogtracker.entity.UserEntity;
import net.worklogtracker.entity.WorkLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkLogRepository extends JpaRepository<WorkLogEntity, Long> {
    @Query("SELECT log FROM WorkLogEntity log JOIN FETCH log.user WHERE log.user = :user AND log.date = :date")
    Optional<WorkLogEntity> findByUserAndDate(UserEntity user, LocalDate date);

    @Query("SELECT log FROM WorkLogEntity log JOIN FETCH log.user WHERE log.date = :date ORDER BY log.start_time ASC")
    List<WorkLogEntity> findAllByDate(LocalDate date);
}
