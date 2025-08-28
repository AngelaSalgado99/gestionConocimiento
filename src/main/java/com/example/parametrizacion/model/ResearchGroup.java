package com.example.parametrizacion.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "research_groups")
public class ResearchGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "regionals_id", nullable = false)
    private Regionals regionals;  // Era "regionals" - debe ser singular

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "center_id", nullable = false)
    private Centers center;  // Era "centers" - debe ser singular

    @Column(columnDefinition = "TEXT")
    private String vision;

    @Column(columnDefinition = "TEXT")
    private String mission;

    @Column(columnDefinition = "TEXT")
    private String generalObjective;

    @Column(columnDefinition = "TEXT")
    private String specificObjectives;

    @Column(columnDefinition = "TEXT")
    private String strategicActions;

    @Column(columnDefinition = "TEXT")
    private String suceesIndicators;

    @Column(columnDefinition = "TEXT")
    private String applicationSectors;

    @OneToMany(mappedBy = "researchGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResearchSeedbed> seedbeds = new ArrayList<>();

    @OneToMany(mappedBy = "researchGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResearchGroupMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "researchGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResearchProject> projects = new ArrayList<>();

    // Constructores, getters y setters
    public ResearchGroup() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Regionals getRegionals() { return regionals; }
    public void setRegionals(Regionals regionals) { this.regionals = regionals; }
    
    public Centers getCenter() { return center; }
    public void setCenter(Centers center) { this.center = center; }
    
    public String getVision() { return vision; }
    public void setVision(String vision) { this.vision = vision; }
    
    public String getMission() { return mission; }
    public void setMission(String mission) { this.mission = mission; }
    
    public String getGeneralObjective() { return generalObjective; }
    public void setGeneralObjective(String generalObjective) { this.generalObjective = generalObjective; }
    
    public String getSpecificObjectives() { return specificObjectives; }
    public void setSpecificObjectives(String specificObjetives) { this.specificObjectives = specificObjetives; }
    
    public String getStrategicActions() { return strategicActions; }
    public void setStrategicActions(String strategicActions) { this.strategicActions = strategicActions; }
    
    public String getSuceesIndicators() { return suceesIndicators; }
    public void setSuceesIndicators(String suceesIndicators) { this.suceesIndicators = suceesIndicators; }
    
    public String getApplicationSectors() { return applicationSectors; }
    public void setApplicationSectors(String applicationSectors) { this.applicationSectors = applicationSectors; }
    
    public List<ResearchSeedbed> getSeedbeds() { return seedbeds; }
    public void setSeedbeds(List<ResearchSeedbed> seedbeds) { this.seedbeds = seedbeds; }
    
    public List<ResearchGroupMember> getMembers() { return members; }
    public void setMembers(List<ResearchGroupMember> members) { this.members = members; }
    
    public List<ResearchProject> getProjects() { return projects; }
    public void setProjects(List<ResearchProject> projects) { this.projects = projects; }
}