package com.aliz.skillmatrix.controllers;

import com.aliz.skillmatrix.model.UserSkill;
import com.aliz.skillmatrix.services.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-skills")
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;

    @GetMapping
    public List<UserSkill> findAll() {
        return userSkillService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSkill> findById(@PathVariable Long id) {
        return userSkillService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserSkill save(@RequestBody UserSkill userSkill) {
        return userSkillService.save(userSkill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSkill> update(@PathVariable Long id, @RequestBody UserSkill userSkill) {
        return userSkillService.findById(id)
                .map(existingUserSkill -> {
                    userSkill.setId(existingUserSkill.getId());
                    return ResponseEntity.ok(userSkillService.save(userSkill));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userSkillService.findById(id).ifPresent(userSkill -> userSkillService.deleteById(id));
        return ResponseEntity.noContent().build();
    }

}
