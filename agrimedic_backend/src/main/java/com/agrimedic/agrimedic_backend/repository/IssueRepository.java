package com.agrimedic.agrimedic_backend.repository;

import com.agrimedic.agrimedic_backend.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByFarmerId(Long farmerId);
}
