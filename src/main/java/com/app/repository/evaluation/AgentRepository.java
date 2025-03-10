package com.app.repository.evaluation;

import com.app.entity.evaluation.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}