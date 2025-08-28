package com.example.parametrizacion.model;

import java.time.LocalDate;
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
@Table(name = "research_seedbeds")  // Agregué el name que faltaba
public class ResearchSeedbed {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "research_group_id", nullable = false)
    private ResearchGroup researchGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id", nullable = false)
    private Centers center;  // Era "Center" - debe ser "Centers"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regional_id", nullable = false)
    private Regionals regional;  // Era "Regional" - debe ser "Regionals"

    @Column(columnDefinition = "TEXT")
    private String vision;

    @Column(columnDefinition = "TEXT")
    private String mission;

    @Column(columnDefinition = "TEXT")
    private String generalObjective;

    @Column(columnDefinition = "TEXT")
    private String specificObjectives;

    @Column(columnDefinition = "TEXT")
    private String challenges;

    @Column(columnDefinition = "TEXT")
    private String opportunities;

    //Estrategias para la sostenibilidad del semillero
    @Column(columnDefinition = "TEXT")
    private String researchStrategies;

    @Column(columnDefinition = "TEXT")
    private String fundingStrategies;

    @Column(columnDefinition = "TEXT")
    private String disseminationStrategies;

    //Desarrollo proyectado del semillero
    @Column(columnDefinition = "TEXT")
    private String capacityDevelopment;

    @Column(columnDefinition = "TEXT")
    private String infrastructureDevelopment;

    @Column(columnDefinition = "TEXT")
    private String researchLinesDevelopment;

    @OneToMany(mappedBy = "researchSeedbed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  // Era "seedbed"
    private List<SeedbedMember> members = new ArrayList<>();

    @OneToMany(mappedBy = "researchSeedbed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  // Era "seedbed"
    private List<ResearchProject> projects = new ArrayList<>();

    @OneToMany(mappedBy = "researchSeedbed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  // Era "seedbed"
    private List<SeedbedEvent> events = new ArrayList<>();

    // Constructores, getters y setters
    public ResearchSeedbed() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    
    public ResearchGroup getResearchGroup() { return researchGroup; }
    public void setResearchGroup(ResearchGroup researchGroup) { this.researchGroup = researchGroup; }
    
    public Centers getCenter() { return center; }
    public void setCenter(Centers center) { this.center = center; }
    
    public Regionals getRegional() { return regional; }
    public void setRegional(Regionals regional) { this.regional = regional; }
    
    public String getVision() { return vision; }
    public void setVision(String vision) { this.vision = vision; }
    
    public String getMission() { return mission; }
    public void setMission(String mission) { this.mission = mission; }
    
    public String getGeneralObjective() { return generalObjective; }
    public void setGeneralObjective(String generalObjective) { this.generalObjective = generalObjective; }
    
    public String getSpecificObjectives() { return specificObjectives; }
    public void setSpecificObjectives(String specificObjectives) { this.specificObjectives = specificObjectives; }
    
    public String getChallenges() { return challenges; }
    public void setChallenges(String challenges) { this.challenges = challenges; }
    
    public String getOpportunities() { return opportunities; }
    public void setOpportunities(String opportunities) { this.opportunities = opportunities; }
    
    public String getResearchStrategies() { return researchStrategies; }
    public void setResearchStrategies(String researchStrategies) { this.researchStrategies = researchStrategies; }
    
    public String getFundingStrategies() { return fundingStrategies; }
    public void setFundingStrategies(String fundingStrategies) { this.fundingStrategies = fundingStrategies; }
    
    public String getDisseminationStrategies() { return disseminationStrategies; }
    public void setDisseminationStrategies(String disseminationStrategies) { this.disseminationStrategies = disseminationStrategies; }
    
    public String getCapacityDevelopment() { return capacityDevelopment; }
    public void setCapacityDevelopment(String capacityDevelopment) { this.capacityDevelopment = capacityDevelopment; }
    
    public String getInfrastructureDevelopment() { return infrastructureDevelopment; }
    public void setInfrastructureDevelopment(String infrastructureDevelopment) { this.infrastructureDevelopment = infrastructureDevelopment; }
    
    public String getResearchLinesDevelopment() { return researchLinesDevelopment; }
    public void setResearchLinesDevelopment(String researchLinesDevelopment) { this.researchLinesDevelopment = researchLinesDevelopment; }
    
    public List<SeedbedMember> getMembers() { return members; }
    public void setMembers(List<SeedbedMember> members) { this.members = members; }
    
    public List<ResearchProject> getProjects() { return projects; }
    public void setProjects(List<ResearchProject> projects) { this.projects = projects; }
    
    public List<SeedbedEvent> getEvents() { return events; }
    public void setEvents(List<SeedbedEvent> events) { this.events = events; }
}