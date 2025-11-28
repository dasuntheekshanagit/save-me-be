package com.saveme.saveme.repository;

import com.saveme.saveme.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.trueReports > 0")
    long countVerified();

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.trueReports = 0 AND n.spamReports = 0")
    long countUnverified();

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.severity = 'CRITICAL'")
    long countCriticalAlerts();
}
