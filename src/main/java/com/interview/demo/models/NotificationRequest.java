package com.interview.demo.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotificationRequest {
    private final String to;
    private final String message;
}
