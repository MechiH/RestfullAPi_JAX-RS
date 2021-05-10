package com.mechi.service;

import com.mechi.database.DatabaseClass;
import com.mechi.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {
    private static Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
        profiles.put("El 7ou", new Profile((long) 1, "El 7ou", "Houssem", "Mechi"));
        profiles.put("Mimou", new Profile((long) 2f, "Mimou", "Oumayma", "Mechi"));
    }

    public List<Profile> gettAllprofiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId((long) (profiles.size() + 1));
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }
}
