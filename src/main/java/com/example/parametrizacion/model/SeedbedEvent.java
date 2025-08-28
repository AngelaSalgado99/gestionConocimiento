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
@Table(name = "seedbed_events")
public class SeedbedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Cambié a IDENTITY para Long
    private Long id;  // Cambié de String a Long

    @Column(nullable = false)  // Era "nnullable"
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;  // Era "starDate" y "localDate"

    @Column(nullable = false)
    private LocalDate endDate;  // Era "localDate"

    private String location;

    private Integer participantCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seedbed_id", nullable = false)
    private ResearchSeedbed researchSeedbed;  // Era "seedbed" - debe coincidir con el nombre

    @Enumerated(EnumType.STRING)
    private EventType eventType; // Enum para el tipo de evento

    private String presenter; // Nombre del presentador o conferencista 

    // Constructores, getters y setters
    public SeedbedEvent() {}

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
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Integer getParticipantCount() { return participantCount; }
    public void setParticipantCount(Integer participantCount) { this.participantCount = participantCount; }
    
    public ResearchSeedbed getResearchSeedbed() { return researchSeedbed; }
    public void setResearchSeedbed(ResearchSeedbed researchSeedbed) { this.researchSeedbed = researchSeedbed; }
    
    public EventType getEventType() { return eventType; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
    
    public String getPresenter() { return presenter; }
    public void setPresenter(String presenter) { this.presenter = presenter; }
}