package com.aliz.skillmatrix.controllers;

import com.aliz.skillmatrix.model.Skill;
import com.aliz.skillmatrix.model.SkillType;
import com.aliz.skillmatrix.services.SkillService;
import com.aliz.skillmatrix.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ApiResponse<List<Skill>> findAll() {
        return new ApiResponse<>(HttpStatus.OK.value(), skillService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Skill> findById(@PathVariable Long id) {
        Skill skill = skillService.findById(id);
        return new ApiResponse<>(HttpStatus.OK.value(), skill);
    }

    @PostMapping
    public ApiResponse<Skill> create(@RequestBody Skill skill) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), skillService.create(skill));
    }

    @PutMapping("/{id}")
    public ApiResponse<Skill> update(@PathVariable Long id, @RequestBody Skill skill) {
        return new ApiResponse<>(HttpStatus.OK.value(), skillService.update(id, skill));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        skillService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Skill deleted successfully");
    }

    @GetMapping("/type/{skillType}")
    public ApiResponse<List<Skill>> findBySkillType(@PathVariable SkillType skillType) {
        return new ApiResponse<>(HttpStatus.OK.value(), skillService.findBySkillType(skillType));
    }
}
