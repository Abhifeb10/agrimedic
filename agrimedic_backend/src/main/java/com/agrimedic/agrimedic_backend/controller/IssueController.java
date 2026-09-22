package com.agrimedic.agrimedic_backend.controller;

import com.agrimedic.agrimedic_backend.dto.IssueDTO;
import com.agrimedic.agrimedic_backend.entity.Issue;
import com.agrimedic.agrimedic_backend.entity.User;
import com.agrimedic.agrimedic_backend.service.IssueService;
import com.agrimedic.agrimedic_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;
    private final UserService userService;

    public IssueController(IssueService issueService, UserService userService) {
        this.issueService = issueService;
        this.userService = userService;
    }

    // ------------------------ CREATE ISSUE -------------------------
    @PostMapping
    public ResponseEntity<?> create(@RequestBody IssueDTO dto) {
        Issue issue = new Issue();
        issue.setTitle(dto.getTitle());
        issue.setDescription(dto.getDescription());
        issue.setImageUrl(dto.getImageUrl());

        if (dto.getFarmerId() != null) {
            User farmer = userService.findById(dto.getFarmerId()).orElseThrow();
            issue.setFarmer(farmer);
        }

        Issue saved = issueService.create(issue);
        dto.setId(saved.getId());
        dto.setStatus(saved.getStatus());
        return ResponseEntity.ok(dto);
    }

    // ------------------------ GET ALL ISSUES -------------------------
    @GetMapping
    public List<IssueDTO> all() {
        return issueService.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    // ------------------------ GET ISSUE BY ID -------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return issueService.findById(id)
                .map(i -> ResponseEntity.ok(toDto(i)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ------------------------ DIAGNOSE ISSUE -------------------------
    @PreAuthorize("hasRole('EXPERT') or hasRole('ADMIN')")
    @PutMapping("/{id}/diagnose")
    public ResponseEntity<?> diagnose(@PathVariable Long id, @RequestBody IssueDTO dto) {
        Issue updated = issueService.diagnose(id, dto.getDiagnosis(), dto.getExpertId());
        return ResponseEntity.ok(toDto(updated));
    }

    // ------------------------ MY ISSUES (JWT) -------------------------
    @GetMapping("/mine")
    public List<IssueDTO> myIssues(Authentication auth) {
        String email = auth.getName();
        User farmer = userService.findByEmail(email).orElseThrow();

        List<Issue> issues = issueService.findByFarmerId(farmer.getId());
        return issues.stream().map(this::toDto).collect(Collectors.toList());
    }

    // ------------------------ DTO MAPPER -------------------------
    private IssueDTO toDto(Issue i) {
        IssueDTO d = new IssueDTO();
        d.setId(i.getId());
        d.setTitle(i.getTitle());
        d.setDescription(i.getDescription());
        d.setImageUrl(i.getImageUrl());

        if (i.getFarmer() != null) d.setFarmerId(i.getFarmer().getId());
        if (i.getExpert() != null) d.setExpertId(i.getExpert().getId());

        d.setDiagnosis(i.getDiagnosis());
        d.setStatus(i.getStatus());
        return d;
    }
    
    @PostMapping("/{id}/reply")
    public ResponseEntity<?> replyToIssue(
            @PathVariable Long id,
            @RequestBody IssueDTO dto,
            Authentication auth
    ) {
        String expertEmail = auth.getName();
        User expert = userService.findByEmail(expertEmail).orElseThrow();

        Issue updated = issueService.reply(id, dto.getDiagnosis(), expert.getId());

        return ResponseEntity.ok(toDto(updated));
    }

}
