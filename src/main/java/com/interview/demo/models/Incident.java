package com.interview.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {
 *   "incidentId":         "inc-1029",
 *   "serviceId":          "svc-payments",
 *   "severity":           "CRITICAL",
 *   "status":             "NOTIFIED",     // TRIGGERED | NOTIFIED
 *   "notificationStatus": "SENT"          // SENT | FAILED | SKIPPED
 * }
 */

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Incident {
    private String incidentId;
    private String serviceId;
    private Severity severity;
    private Status status;
    private NotificationStatus notificationStatus;

    public Incident(String serviceId, Severity severity) {
        this.incidentId =
        this.serviceId = serviceId;
        this.severity = severity;
        this.status = Status.PENDING;
        this.notificationStatus = NotificationStatus.PENDING;
    }
}
