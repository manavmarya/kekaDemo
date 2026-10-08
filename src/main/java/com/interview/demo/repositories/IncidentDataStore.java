package com.interview.demo.repositories;

import com.interview.demo.models.Incident;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class IncidentDataStore {

    private static final Map<String, Incident> inMemoryIncident = new HashMap<>();
    private static AtomicInteger serialKey = new AtomicInteger(0);
    private static final String INCIDENT_ID_PREFIX = "inc-";

    public boolean save(Incident incident, String dedupKey) {
        incident.setIncidentId(INCIDENT_ID_PREFIX + serialKey.incrementAndGet());
        inMemoryIncident.put(dedupKey, incident);
        return true;
    }
}
