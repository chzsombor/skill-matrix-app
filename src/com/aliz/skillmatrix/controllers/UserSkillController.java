package com.aliz.skillmatrix.controllers;

import com.aliz.skillmatrix.model.ProficiencyLevel;
import com.aliz.skillmatrix.model.SkillType;
import com.aliz.skillmatrix.model.User;
import com.aliz.skillmatrix.model.UserSkill;
import com.aliz.skillmatrix.services.UserSkillService;
import com.aliz.skillmatrix.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-skills")
public class UserSkillController {

    @Autowired
    private UserSkillService userSkillService;

    @GetMapping
    public ApiResponse<List<UserSkill>> findAll() {
        return new ApiResponse<>(HttpStatus.OK.value(), userSkillService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserSkill> findById(@PathVariable Long id) {
        return new ApiResponse<>(HttpStatus.OK.value(), userSkillService.findById(id).orElse(null));
    }

    @PostMapping
    public ApiResponse<UserSkill> create(@RequestBody UserSkill userSkill) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), userSkillService.create(userSkill));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserSkill> update(@PathVariable Long id, @RequestBody UserSkill userSkill) {
        return new ApiResponse<>(HttpStatus.OK.value(), userSkillService.update(id, userSkill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userSkillService.findById(id).ifPresent(userSkill -> userSkillService.delete(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{skillId}")
    public ApiResponse<UserSkill> addSkillToUser(@PathVariable Long userId, @PathVariable Long skillId, @RequestParam ProficiencyLevel proficiencyLevel) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), userSkillService.addSkillToUser(userId, skillId, proficiencyLevel));
    }

    @GetMapping("/{skillType}")
    public ApiResponse<List<User>> getUsersBySkillType(@PathVariable SkillType skillType) {
        return new ApiResponse<>(HttpStatus.OK.value(), userSkillService.getUsersBySkillType(skillType));
    }

    @GetMapping("/skill/{skillId}/level/{proficiencyLevel}")
    public ApiResponse<List<User>> getUsersBySkillAndProficiencyLevel(@PathVariable Long skillId, @PathVariable ProficiencyLevel proficiencyLevel) {
        return new ApiResponse<>(HttpStatus.OK.value(), userSkillService.getUsersBySkillAndProficiencyLevel(skillId, proficiencyLevel));
    }

    @DeleteMapping("/{skillId}")
    public ApiResponse<String> deleteSkillFromUser(@PathVariable Long userId, @PathVariable Long skillId) {
        userSkillService.deleteSkillFromUser(userId, skillId);
        return new ApiResponse<>(HttpStatus.OK.value(), "Skill deleted successfully from user");
    }

    @PutMapping("/{oldSkillId}/{newSkillId}")
    public ApiResponse<UserSkill> updateSkillOfUser(@PathVariable Long userId, @PathVariable Long oldSkillId, @PathVariable Long newSkillId) {
        return new ApiResponse<>(HttpStatus.OK.value(), userSkillService.updateSkillOfUser(userId, oldSkillId, newSkillId));
    }
}
