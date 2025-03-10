package com.app.service.evaluation;

import com.app.entity.evaluation.Agent;
import com.app.repository.evaluation.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    public Agent addAgent(Agent agent) {
        return agentRepository.save(agent);
    }
}