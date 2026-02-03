package com.example.studentportalspring.service;

import com.example.studentportalspring.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> findAll();

    Skill save(Skill skill);

    Skill findById(Integer id);

    void deleteById(Integer id);
}
