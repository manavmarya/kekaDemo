package com.interview.demo.service;

import com.interview.demo.models.AlertRequest;
import com.interview.demo.models.Incident;
import com.interview.demo.models.NotificationStatus;
import com.interview.demo.models.Status;
import com.interview.demo.repositories.IncidentDataStore;
import com.interview.demo.repositories.PhoneDataStore;
import com.interview.demo.utils.Alert2Incident;
import com.interview.demo.utils.NotificationCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class IncidentService {

    private IncidentDataStore incidentDataStore;
    private PhoneDataStore phoneDataStore;
    private NotificationService notificationService;

    public IncidentService(@Autowired NotificationService notificationService) {
        this.incidentDataStore = new IncidentDataStore();
        this.phoneDataStore = new PhoneDataStore();
        this.notificationService = notificationService;
    }

    public Optional<Incident> createIncidentAndNotify(AlertRequest alertRequest) {
        Incident incident = Alert2Incident.transform(alertRequest);
        incidentDataStore.save(incident, alertRequest.getDedupKey());
        if (NotificationCriteria.isApplicable(alertRequest)) {
            Optional<String> phoneNo = phoneDataStore.getPhoneNo(alertRequest.getServiceId());
            if (phoneNo.isEmpty()) {
                incident.setNotificationStatus(NotificationStatus.SKIPPED);
            }
            NotificationStatus notificationStatus = notificationService.notify(alertRequest, phoneNo.get());
            incident.setNotificationStatus(notificationStatus);
        } else {
            incident.setNotificationStatus(NotificationStatus.SKIPPED);
        }
        incident.setStatus(Status.NOTIFIED);
        return Optional.of(incident);
    }
}
