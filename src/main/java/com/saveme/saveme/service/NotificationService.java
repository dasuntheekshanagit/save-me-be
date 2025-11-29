package com.saveme.saveme.service;

import com.saveme.saveme.dto.NotificationDto;
import com.saveme.saveme.dto.SummaryDto;
import com.saveme.saveme.model.*;
import com.saveme.saveme.repository.AcknowledgementRepository;
import com.saveme.saveme.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    public Notification createNotification(NotificationDto notificationDto, List<MultipartFile> photos) throws IOException {
        Notification notification = new Notification();
        notification.setReporterName(notificationDto.getReporterName());

        if (notificationDto.getTelephoneNumbers() != null && !notificationDto.getTelephoneNumbers().isEmpty()) {
            List<Contact> contacts = notificationDto.getTelephoneNumbers().stream().map(phone -> {
                Contact contact = new Contact();
                contact.setPhoneNumber(phone);
                contact.setNotification(notification);
                return contact;
            }).collect(Collectors.toList());
            notification.setReporterContacts(contacts);
        }

        Location location = new Location();
        location.setLatitude(notificationDto.getLatitude());
        location.setLongitude(notificationDto.getLongitude());
        notification.setLocation(location);
        notification.setLocationDescription(notificationDto.getLocation());

        notification.setIncidentType(notificationDto.getType());
        notification.setSeverity(notificationDto.getSeverity());
        notification.setRoadBlocked(notificationDto.isRoadBlocked());
        notification.setIncidentTime(LocalDateTime.now());
        notification.setAffectedPeople(notificationDto.getAffectedPeople());
        notification.setAffectedChildren(notificationDto.getAffectedChildren());
        notification.setAffectedAdults(notificationDto.getAffectedAdults());
        notification.setAffectedInjured(notificationDto.getAffectedInjured());
        notification.setComments(notificationDto.getComments());

        if ("flood".equals(notificationDto.getType()) && notificationDto.getWaterLevel() != null) {
            notification.setWaterLevel(notificationDto.getWaterLevel());
        }

        Notification savedNotification = notificationRepository.save(notification);

        if (photos != null && !photos.isEmpty()) {
            List<Photo> photoList = new ArrayList<>();
            for (MultipartFile photoFile : photos) {
                String filename = fileStorageService.save(photoFile);
                Photo photo = new Photo();
                photo.setUrl("/uploads/" + filename);
                photo.setNotification(savedNotification);
                photoList.add(photo);
            }
            savedNotification.setPhotos(photoList);
            return notificationRepository.save(savedNotification);
        }

        return savedNotification;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Transactional
    public Notification voteTrue(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setTrueReports(notification.getTrueReports() + 1);
        return notificationRepository.save(notification);
    }

    @Transactional
    public Notification voteSpam(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setSpamReports(notification.getSpamReports() + 1);
        return notificationRepository.save(notification);
    }

    @Transactional
    public Notification acknowledge(Long notificationId, String phoneNumber) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        
        Acknowledgement acknowledgement = new Acknowledgement();
        acknowledgement.setPhoneNumber(phoneNumber);
        acknowledgement.setNotification(notification);
        
        // Add the new acknowledgement to the list before saving
        notification.getAcknowledgements().add(acknowledgement);
        
        // The save is cascaded from the notification, so we only need to save the parent
        return notificationRepository.save(notification);
    }

    @Transactional
    public Notification closeNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setOpen(false);
        return notificationRepository.save(notification);
    }

    public SummaryDto getSummary() {
        SummaryDto summary = new SummaryDto();
        summary.setCriticalAlerts(notificationRepository.countCriticalAlerts());
        summary.setOpenAlerts(notificationRepository.countOpenAlerts());
        summary.setClosedAlerts(notificationRepository.countClosedAlerts());
        summary.setSavedPeople(notificationRepository.countSavedPeople());
        return summary;
    }
}
