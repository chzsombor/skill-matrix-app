package com.aliz.skillmatrix.repository;

import com.aliz.skillmatrix.model.Skill;
import com.aliz.skillmatrix.model.SkillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<List<Skill>> findBySkillType(SkillType skillType);
}
