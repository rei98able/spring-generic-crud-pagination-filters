package com.example.springgenericcrudpaginationfilters.generic.service;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * This class is used to provide Spring Data Jpa Auditing info about User
 */
@Component
public class AuditAwareService implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        // TODO: After implementing your security type provide either ID or another info about User
        return Optional.empty();
    }
}

