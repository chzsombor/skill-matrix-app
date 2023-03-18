package com.aliz.skillmatrix.services;

import com.aliz.skillmatrix.model.Skill;
import com.aliz.skillmatrix.model.SkillType;
import com.aliz.skillmatrix.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }
    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Skill not found with id: " + id));
    }
    public Skill create(Skill skill) {
        return skillRepository.save(skill);
    }
    public Skill update(Long id, Skill skill) {
        Skill existingSkill = skillRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Skill not found with id: " + id));

        existingSkill.setName(skill.getName());
        existingSkill.setDescription(skill.getDescription());
        existingSkill.setSkillType(skill.getSkillType());

        return skillRepository.save(existingSkill);
    }
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }

    public List<Skill> findBySkillType(SkillType skillType) {
        return skillRepository.findBySkillType(skillType)
                .orElseThrow(() -> new NoSuchElementException("No skills found for the given skill type: " + skillType));
    }
}
