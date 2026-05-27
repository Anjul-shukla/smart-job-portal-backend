package com.smartjobportal.service.impl;

import com.smartjobportal.entity.AuditLog;
import com.smartjobportal.repository.AuditLogRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Async
    public void logAction(String email, String action, String description) {
        Long userId = null;
        if (email != null) {
            userId = userRepository.findByEmail(email)
                    .map(u -> u.getId())
                    .orElse(null);
        }

        AuditLog log = AuditLog.builder()
                .userId(userId)
                .action(action)
                .description(description)
                .build();
                
        auditLogRepository.save(log);
    }
}
