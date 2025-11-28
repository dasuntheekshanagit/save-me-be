package com.saveme.saveme.service;

import com.saveme.saveme.dto.AcknowledgementDto;
import com.saveme.saveme.dto.NotificationDto;
import com.saveme.saveme.dto.SummaryDto;
import com.saveme.saveme.model.*;
import com.saveme.saveme.repository.AcknowledgementRepository;
import com.saveme.saveme.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final AcknowledgementRepository acknowledgementRepository;
    private final FileStorageService fileStorageService;

    public NotificationService(NotificationRepository notificationRepository, AcknowledgementRepository acknowledgementRepository, FileStorageService fileStorageService) {
        this.notificationRepository = notificationRepository;
        this.acknowledgementRepository = acknowledgementRepository;
        this.fileStorageService = fileStorageService;
    }

    public Notification createNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();

        Location location = new Location();
        location.setLatitude(notificationDto.getLatitude());
        location.setLongitude(notificationDto.getLongitude());
        notification.setLocation(location);

        notification.setIncidentType(notificationDto.getType());
        notification.setSeverity(notificationDto.getSeverity());
        notification.setRoadBlocked(notificationDto.isRoadBlocked());
        notification.setIncidentTime(LocalDateTime.now());
        notification.setAffectedPeople(notificationDto.getAffectedPeople());
        notification.setAffectedChildren(notificationDto.getAffectedChildren());
        notification.setAffectedAdults(notificationDto.getAffectedAdults());
        notification.setComments(notificationDto.getComments());
        notification.setWaterLevel(notificationDto.getWaterLevel());

        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Acknowledgement acknowledgeNotification(AcknowledgementDto acknowledgementDto) {
        Notification notification = notificationRepository.findById(acknowledgementDto.getIncidentId())
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        Acknowledgement acknowledgement = new Acknowledgement();
        acknowledgement.setNotification(notification);
        acknowledgement.setPhoneNumber(acknowledgementDto.getPhoneNumber());
        acknowledgement.setTrue(acknowledgementDto.isTrue());
        acknowledgement.setSpam(acknowledgementDto.isSpam());

        if (acknowledgement.isTrue()) {
            notification.setTrueReports(notification.getTrueReports() + 1);
        }
        if (acknowledgement.isSpam()) {
            notification.setSpamReports(notification.getSpamReports() + 1);
        }
        notificationRepository.save(notification);

        return acknowledgementRepository.save(acknowledgement);
    }

    public String storePhoto(Long notificationId, MultipartFile file) throws IOException {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        String filename = fileStorageService.save(file);
        Photo photo = new Photo();
        photo.setUrl("/uploads/" + filename);
        photo.setNotification(notification);
        notification.getPhotos().add(photo);
        notificationRepository.save(notification);
        return photo.getUrl();
    }

    public SummaryDto getSummary() {
        SummaryDto summary = new SummaryDto();
        summary.setTapped(notificationRepository.countVerified());
        summary.setMissing(notificationRepository.countUnverified());
        summary.setCriticalAlerts(notificationRepository.countCriticalAlerts());
        return summary;
    }
}
