package com.smartjobportal.repository;

import com.smartjobportal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByJobIdAndUserId(Long jobId, Long userId);
    List<Application> findByUserId(Long userId);
    List<Application> findByJobId(Long jobId);
    Optional<Application> findByIdAndJob_Company_Id(Long applicationId, Long companyId);
}
