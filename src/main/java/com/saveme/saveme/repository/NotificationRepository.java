package com.saveme.saveme.repository;

import com.saveme.saveme.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.severity = 'CRITICAL' AND n.isOpen = true")
    long countCriticalAlerts();

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.isOpen = true")
    long countOpenAlerts();

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.isOpen = false")
    long countClosedAlerts();

    @Query("SELECT COALESCE(SUM(n.affectedPeople), 0) FROM Notification n WHERE n.isOpen = false")
    long countSavedPeople();
}
