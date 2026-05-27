package com.smartjobportal.service;

import com.smartjobportal.dto.ProfileDto;

public interface ProfileService {
    ProfileDto getProfile(String email);
    ProfileDto updateProfile(String email, ProfileDto profileDto);
    ProfileDto uploadResume(String email, String resumeUrl);
}
