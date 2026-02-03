package com.example.studentportalspring.repository;

import com.example.studentportalspring.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
