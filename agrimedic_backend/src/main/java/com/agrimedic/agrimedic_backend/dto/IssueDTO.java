package com.agrimedic.agrimedic_backend.dto;

public class IssueDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Long farmerId;
    private Long expertId;
    private String diagnosis;
    private String status;

    public IssueDTO() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Long getFarmerId() { return farmerId; }
    public void setFarmerId(Long farmerId) { this.farmerId = farmerId; }
    public Long getExpertId() { return expertId; }
    public void setExpertId(Long expertId) { this.expertId = expertId; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}