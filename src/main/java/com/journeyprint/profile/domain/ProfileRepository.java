package com.journeyprint.profile.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ProfileRepository {

    private List<Long> profiles = new ArrayList<>();

    public Optional<Long> getById(Long id) {
        return profiles.stream()
                .filter(profileId -> profileId.equals(id))
                .findFirst();
    }

    public Long save(Long id) {
        profiles.add(id);
        return id;
    }
}