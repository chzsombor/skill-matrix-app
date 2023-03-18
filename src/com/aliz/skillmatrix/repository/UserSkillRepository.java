package com.aliz.skillmatrix.repository;

import com.aliz.skillmatrix.model.ProficiencyLevel;
import com.aliz.skillmatrix.model.Skill;
import com.aliz.skillmatrix.model.User;
import com.aliz.skillmatrix.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    List<UserSkill> findByUser(User user);
    List<UserSkill> findBySkill(Skill skill);
    List<UserSkill> findBySkillAndProficiencyLevel(Skill skill, ProficiencyLevel proficiencyLevel);
    Optional<UserSkill> findByUserAndSkill(User user, Skill skill);
    List<UserSkill> findByProficiencyLevelIn(List<ProficiencyLevel> proficiencyLevels);

    void deleteByUserAndSkill(User user, Skill skill);
}
