package com.interview.demo.service;

import com.interview.demo.models.AlertRequest;
import com.interview.demo.repositories.RequestValidationDataStore;
import org.springframework.stereotype.Service;

@Service
public class RequestValidationService {

    public RequestValidationDataStore requestValidationDataStore;

    public RequestValidationService() {
        this.requestValidationDataStore = new RequestValidationDataStore();
    }

    public boolean isValidRequest(AlertRequest alertRequest) {
        if (requestValidationDataStore.isPresent(alertRequest)) {
            return false;
        }
        requestValidationDataStore.save(alertRequest);
        return true;
    }
}
