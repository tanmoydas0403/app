package com.app.controller.evaluation;

import com.app.entity.evaluation.Agent;
import com.app.service.evaluation.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
        Agent savedAgent = agentService.addAgent(agent);
        return ResponseEntity.ok(savedAgent);
    }
}