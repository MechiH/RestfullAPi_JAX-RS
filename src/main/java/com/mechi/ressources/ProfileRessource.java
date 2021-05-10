package com.mechi.ressources;

import java.util.List;

import com.mechi.model.Profile;
import com.mechi.service.ProfileService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("profiles")
public class ProfileRessource {

    ProfileService profileService = new ProfileService();

    @GET
    @Produces(MediaType.APPLICATION_JSON) // format of return content type f
    public List<Profile> getProfiles() {
        return profileService.gettAllprofiles();
    }

    @GET
    @Path("/{profileName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON) // to specifi that this method consume a json
    @Produces(MediaType.APPLICATION_JSON) // format of return content type f
    public Profile addProfile(Profile profile) { // the arguement : recieve json convert to Profile
        return profileService.addProfile(profile);
    }

    @PUT
    @Path("/{profileName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Profile updateProfile(Profile profile, @PathParam("profileName") String profileName) {
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProfile(@PathParam("profileName") String profileName) {
        profileService.removeProfile(profileName);
    }

}
