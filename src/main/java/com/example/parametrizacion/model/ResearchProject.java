package com.example.parametrizacion.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "research_projects")
public class ResearchProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)  // Había un espacio extra
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "research_group_id")
    private ResearchGroup researchGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seedbed_id")
    private ResearchSeedbed researchSeedbed;  // Era "seedbed" - debe coincidir con el nombre de la clase

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "research_line_id", nullable = false)  // Era "reserch_line_id"
    private ResearchLines researchLine;  // Era "ResearchLine" - debe ser "ResearchLines"

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    // Constructores, getters y setters
    public ResearchProject() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public ResearchGroup getResearchGroup() { return researchGroup; }
    public void setResearchGroup(ResearchGroup researchGroup) { this.researchGroup = researchGroup; }
    
    public ResearchSeedbed getResearchSeedbed() { return researchSeedbed; }
    public void setResearchSeedbed(ResearchSeedbed researchSeedbed) { this.researchSeedbed = researchSeedbed; }
    
    public ResearchLines getResearchLine() { return researchLine; }
    public void setResearchLine(ResearchLines researchLine) { this.researchLine = researchLine; }
    
    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }
}