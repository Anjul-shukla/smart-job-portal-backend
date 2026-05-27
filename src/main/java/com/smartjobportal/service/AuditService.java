package com.smartjobportal.service;

public interface AuditService {
    void logAction(String email, String action, String description);
}
