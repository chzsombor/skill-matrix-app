package com.aliz.skillmatrix.services;

import com.aliz.skillmatrix.model.UserSkill;
import com.aliz.skillmatrix.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;

    public List<UserSkill> findAll() {
        return userSkillRepository.findAll();
    }

    public Optional<UserSkill> findById(Long id) {
        return userSkillRepository.findById(id);
    }

    public UserSkill save(UserSkill userSkill) {
        return userSkillRepository.save(userSkill);
    }

    public void deleteById(Long id) {
        userSkillRepository.deleteById(id);
    }

}