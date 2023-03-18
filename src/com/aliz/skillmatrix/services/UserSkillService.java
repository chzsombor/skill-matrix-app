package com.aliz.skillmatrix.services;

import com.aliz.skillmatrix.model.*;
import com.aliz.skillmatrix.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private SkillService skillService;

    public List<UserSkill> findAll() {
        return userSkillRepository.findAll();
    }

    public Optional<UserSkill> findById(Long id) {
        return userSkillRepository.findById(id);
    }

    public UserSkill create(UserSkill userSkill) {
        return userSkillRepository.save(userSkill);
    }

    public UserSkill addSkillToUser(Long userId, Long skillId, ProficiencyLevel proficiencyLevel) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Skill skill = skillService.findById(skillId);

        UserSkill userSkill = new UserSkill(user, skill, proficiencyLevel);
        return userSkillRepository.save(userSkill);
    }

    public List<User> getUsersBySkillType(SkillType skillType) {
        List<Skill> skills = skillService.findBySkillType(skillType); // TODO: manage if no skills
        return skills.stream()
                .flatMap(skill -> userSkillRepository.findBySkill(skill).stream())
                .map(UserSkill::getUser)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<User> getUsersBySkillAndProficiencyLevel(Long skillId, ProficiencyLevel proficiencyLevel) {
        Skill skill = skillService.findById(skillId);
        return userSkillRepository.findBySkillAndProficiencyLevel(skill, proficiencyLevel).stream()
                .map(UserSkill::getUser)
                .collect(Collectors.toList());
    }

    public void deleteSkillFromUser(Long userId, Long skillId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Skill skill = skillService.findById(skillId);

        userSkillRepository.deleteByUserAndSkill(user, skill);
    }

    public UserSkill updateSkillOfUser(Long userId, Long oldSkillId, Long newSkillId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Skill oldSkill = skillService.findById(oldSkillId);
        Skill newSkill = skillService.findById(newSkillId);

        UserSkill userSkill = userSkillRepository.findByUserAndSkill(user, oldSkill)
                .orElseThrow(() -> new RuntimeException("UserSkill not found for the user and skill combination"));
        userSkill.setSkill(newSkill);
        return userSkillRepository.save(userSkill);
    }

    public UserSkill updateProficiencyLevel(Long userId, Long skillId, ProficiencyLevel proficiencyLevel) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Skill skill = skillService.findById(skillId);

        UserSkill userSkill = userSkillRepository.findByUserAndSkill(user, skill)
                .orElseThrow(() -> new RuntimeException("UserSkill not found for the user and skill combination"));
        userSkill.setProficiencyLevel(proficiencyLevel);
        return userSkillRepository.save(userSkill);
    }

    public List<User> getUsersWithExpertOrAdvancedProficiencyLevel() {
        List<ProficiencyLevel> proficiencyLevels = Arrays.asList(ProficiencyLevel.EXPERT, ProficiencyLevel.ADVANCED);
        List<UserSkill> userSkills = userSkillRepository.findByProficiencyLevelIn(proficiencyLevels);

        return userSkills.stream()
                .map(UserSkill::getUser)
                .distinct()
                .collect(Collectors.toList());
    }

    public UserSkill update(Long id, UserSkill updatedUserSkill) {
        UserSkill userSkill = userSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserSkill not found with id: " + id));

        userSkill.setSkill(updatedUserSkill.getSkill());
        userSkill.setProficiencyLevel(updatedUserSkill.getProficiencyLevel());

        return userSkillRepository.save(userSkill);
    }

    public void delete(Long id) {
        userSkillRepository.deleteById(id);
    }

}
