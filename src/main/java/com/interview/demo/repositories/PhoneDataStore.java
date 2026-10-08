package com.interview.demo.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PhoneDataStore {

    private static final Map<String, String> service2PhoneNo = new HashMap<>();
    static {
        service2PhoneNo.put("svc-payments", "+919900112233");
        service2PhoneNo.put("svc-checkout", "+919900445566");
        service2PhoneNo.put("svc-search", "+919900778899");
    }

    public Optional<String> getPhoneNo(String service) {
        if (service2PhoneNo.containsKey(service)) {
            return Optional.of(service2PhoneNo.get(service));
        } else {
            return Optional.empty();
        }
    }
}
