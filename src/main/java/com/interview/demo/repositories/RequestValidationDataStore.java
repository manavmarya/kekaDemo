package com.interview.demo.repositories;

import com.interview.demo.models.AlertRequest;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class RequestValidationDataStore {

    private static Set<String> seenKeys = new HashSet<>();

    public boolean isPresent(AlertRequest alertRequest) {
        return seenKeys.contains(alertRequest.getDedupKey());
    }

    public boolean save(AlertRequest alertRequest) {
        seenKeys.add(alertRequest.getDedupKey());
        return true;
    }


}
