package com.agrimedic.agrimedic_backend.service.impl;

import com.agrimedic.agrimedic_backend.entity.Issue;
import com.agrimedic.agrimedic_backend.entity.User;
import com.agrimedic.agrimedic_backend.repository.IssueRepository;
import com.agrimedic.agrimedic_backend.repository.UserRepository;
import com.agrimedic.agrimedic_backend.service.IssueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepo;
    private final UserRepository userRepo;

    public IssueServiceImpl(IssueRepository issueRepo, UserRepository userRepo) {
        this.issueRepo = issueRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Issue create(Issue issue) {
        issue.setStatus("PENDING");
        return issueRepo.save(issue);
    }

    @Override
    public List<Issue> findAll() {
        return issueRepo.findAll();
    }

    @Override
    public Optional<Issue> findById(Long id) {
        return issueRepo.findById(id);
    }

    @Override
    public Issue diagnose(Long id, String diagnosis, Long expertId) {
        Issue issue = issueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        User expert = userRepo.findById(expertId)
                .orElseThrow(() -> new RuntimeException("Expert not found"));

        issue.setDiagnosis(diagnosis);
        issue.setExpert(expert);
        issue.setStatus("SOLVED");

        return issueRepo.save(issue);
    }

    @Override
    public List<Issue> findByFarmerId(Long farmerId) {
        return issueRepo.findByFarmerId(farmerId);
    }

    public Issue reply(Long id, String replyText, Long expertId) {
        Issue issue = issueRepo.findById(id).orElseThrow();
        User expert = userRepo.findById(expertId).orElseThrow();

        issue.setDiagnosis(replyText);
        issue.setExpert(expert);
        issue.setStatus("SOLVED");

        return issueRepo.save(issue);
    }

}
