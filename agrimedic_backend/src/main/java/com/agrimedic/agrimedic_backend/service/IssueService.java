package com.agrimedic.agrimedic_backend.service;

import com.agrimedic.agrimedic_backend.entity.*;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue create(Issue issue);
    List<Issue> findAll();
    Optional<Issue> findById(Long id);
    Issue diagnose(Long id, String diagnosis, Long expertId);
    List<Issue> findByFarmerId(Long farmerId);
	Issue reply(Long id, String diagnosis, Long id2);
}