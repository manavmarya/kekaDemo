package com.interview.demo.service;

import com.interview.demo.models.AlertRequest;
import com.interview.demo.models.Incident;
import com.interview.demo.models.NotificationRequest;
import com.interview.demo.models.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

public class NotificationService {

    private RestClient restClient;

    public NotificationService(@Autowired RestClient restClient) {
        this.restClient = restClient;

    }

    public NotificationStatus notify(AlertRequest alertRequest, String phoneNo) {
        NotificationRequest request = new NotificationRequest(phoneNo, alertRequest.getSeverity().name() + ":" + alertRequest.getTitle());
        callExternalApi(request)
    }

    public Incident callExternalApi(NotificationRequest payload) {
        return restClient.post()
                .uri("/vi/calls")
                .body(payload)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (httpRequest, httpResponse) -> {
                })
                .onStatus(HttpStatusCode::is5xxServerError, errorHandler -> {

                })
                .toBodilessEntity();
    }
}
