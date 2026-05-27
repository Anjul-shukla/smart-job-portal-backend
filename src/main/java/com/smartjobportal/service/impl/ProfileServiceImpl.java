package com.smartjobportal.service.impl;

import com.smartjobportal.dto.ProfileDto;
import com.smartjobportal.entity.Profile;
import com.smartjobportal.entity.User;
import com.smartjobportal.exception.ResourceNotFoundException;
import com.smartjobportal.repository.ProfileRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProfileDto getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        Profile profile = profileRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Profile newProfile = new Profile();
                    newProfile.setUser(user);
                    return profileRepository.save(newProfile);
                });
                
        return mapToDto(profile);
    }

    @Override
    public ProfileDto updateProfile(String email, ProfileDto profileDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        Profile profile = profileRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Profile newProfile = new Profile();
                    newProfile.setUser(user);
                    return newProfile;
                });
                
        profile.setSkills(profileDto.getSkills());
        profile.setExperience(profileDto.getExperience());
        profile.setEducation(profileDto.getEducation());
        
        Profile updatedProfile = profileRepository.save(profile);
        return mapToDto(updatedProfile);
    }

    @Override
    public ProfileDto uploadResume(String email, String resumeUrl) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        Profile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
                
        profile.setResumeUrl(resumeUrl);
        Profile updatedProfile = profileRepository.save(profile);
        return mapToDto(updatedProfile);
    }
    
    private ProfileDto mapToDto(Profile profile) {
        return ProfileDto.builder()
                .id(profile.getId())
                .userId(profile.getUser().getId())
                .userName(profile.getUser().getName())
                .email(profile.getUser().getEmail())
                .skills(profile.getSkills())
                .experience(profile.getExperience())
                .education(profile.getEducation())
                .resumeUrl(profile.getResumeUrl())
                .completionPercentage(profile.calculateCompletionPercentage())
                .build();
    }
}
