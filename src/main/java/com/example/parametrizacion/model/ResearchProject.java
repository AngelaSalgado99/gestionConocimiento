package com.example.parametrizacion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "researchProjects")
public class ResearchProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The 'title' field is required")
    @Column(nullable = false)
    private String title;

    private String description;

    @NotNull(message = "The 'startDate' field is required")
    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    // 🔹 Relación con ResearchGroup
    @ManyToOne
    @JoinColumn(name = "research_group_id", nullable = false)
    private ResearchGroup researchGroup;

    // 🔹 Relación con ResearchSeedbed
    @ManyToOne
    @JoinColumn(name = "research_seedbed_id", nullable = false)
    private ResearchSeedbed researchSeedbed;

    // 🔹 Relación con ResearchLine
    @ManyToOne
    @JoinColumn(name = "research_line_id", nullable = false)
    private ResearchLines researchLine;

    public ResearchProject() {}

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ResearchGroup getResearchGroup() {
        return researchGroup;
    }

    public void setResearchGroup(ResearchGroup researchGroup) {
        this.researchGroup = researchGroup;
    }

    public ResearchSeedbed getResearchSeedbed() {
        return researchSeedbed;
    }

    public void setResearchSeedbed(ResearchSeedbed researchSeedbed) {
        this.researchSeedbed = researchSeedbed;
    }

    public ResearchLines getResearchLine() {
        return researchLine;
    }

    public void setResearchLine(ResearchLines researchLine) {
        this.researchLine = researchLine;
    }
}
