package com.interview.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {
 *   "serviceId":  "svc-payments",
 *   "severity":   "CRITICAL",              // INFO | WARNING | CRITICAL
 *   "title":      "Payment gateway timeout",
 *   "dedupKey":   "payments-latency-001"
 * }
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertRequest {
    private String serviceId;
    private Severity severity;
    private String title;
    private String dedupKey;
}
