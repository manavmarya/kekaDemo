package com.interview.demo.controllers;

import com.interview.demo.models.AlertRequest;
import com.interview.demo.models.Incident;
import com.interview.demo.service.IncidentService;
import com.interview.demo.service.RequestValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class IncidentController {

    private final RestClient restClient;
    private final IncidentService incidentService;
    private final RequestValidationService requestValidationService;

    public IncidentController(@Autowired RestClient restClient, @Autowired IncidentService incidentService, @Autowired RequestValidationService requestValidationService) {
        this.restClient = restClient;
        this.incidentService = incidentService;
        this.requestValidationService = requestValidationService;
    }

    @PostMapping("/alert")
    public ResponseEntity<Incident> getName(@RequestBody AlertRequest alertRequest) {
        if (!requestValidationService.isValidRequest(alertRequest)) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(409));
        }
        Optional<Incident> incident = incidentService.createIncidentAndNotify(alertRequest);
        if (incident.isEmpty()) return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        return ResponseEntity.ok(incident.get());
    }


}
