package com.interview.demo.utils;

import com.interview.demo.models.AlertRequest;
import com.interview.demo.models.Severity;

public class NotificationCriteria {

    public static boolean isApplicable(AlertRequest alertRequest) {
        return Severity.CRITICAL.equals(alertRequest.getSeverity());
    }
}
