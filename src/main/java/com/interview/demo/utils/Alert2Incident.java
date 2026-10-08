package com.interview.demo.utils;

import com.interview.demo.models.AlertRequest;
import com.interview.demo.models.Incident;

public class Alert2Incident {

    public static Incident transform(AlertRequest alertRequest) {
        Incident incident = new Incident(alertRequest.getServiceId(), alertRequest.getSeverity());
        return incident;
    }
}
