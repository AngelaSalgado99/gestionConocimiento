package com.example.parametrizacion.service;

import com.example.parametrizacion.model.ResearchProject;
import com.example.parametrizacion.repository.ResearchProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResearchProjectService {

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    public List<ResearchProject> getAllResearchProjects() {
        return researchProjectRepository.findAll();
    }

    public Optional<ResearchProject> getResearchProjectById(Long id) {
        return researchProjectRepository.findById(id);
    }

    public List<ResearchProject> getResearchProjectsByGroup(Long groupId) {
        return researchProjectRepository.findByResearchGroupId(groupId);
    }

    public List<ResearchProject> getResearchProjectsBySeedbed(Long seedbedId) {
        return researchProjectRepository.findByResearchSeedbedId(seedbedId);
    }

    public List<ResearchProject> getResearchProjectsByResearchLine(Long researchLineId) {
        return researchProjectRepository.findByResearchLineId(researchLineId);
    }

    public ResearchProject createResearchProject(ResearchProject researchProject) {
        return researchProjectRepository.save(researchProject);
    }

    public ResearchProject updateResearchProject(Long id, ResearchProject researchProjectDetails) {
        ResearchProject project = researchProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        project.setTitle(researchProjectDetails.getTitle());
        project.setDescription(researchProjectDetails.getDescription());
        project.setStartDate(researchProjectDetails.getStartDate());
        project.setEndDate(researchProjectDetails.getEndDate());
        project.setResearchGroup(researchProjectDetails.getResearchGroup());
        project.setResearchSeedbed(researchProjectDetails.getResearchSeedbed());
        project.setResearchLine(researchProjectDetails.getResearchLine());

        return researchProjectRepository.save(project);
    }

    public void deleteResearchProject(Long id) {
        ResearchProject project = researchProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        researchProjectRepository.delete(project);
    }
}
