package com.app.controller.evaluation;

import com.app.entity.evaluation.Agent;
import com.app.entity.evaluation.Area;
import com.app.entity.evaluation.CustomerVisit;
import com.app.repository.evaluation.AgentRepository;
import com.app.repository.evaluation.AreaRepository;
import com.app.repository.evaluation.CustomerVisitRepository;
import com.app.service.evaluation.SmsService;
import com.app.service.evaluation.WhatsappService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {
    private final AreaRepository areaRepository;
    private final AgentRepository agentRepository;
    private final CustomerVisitRepository customerVisitRepository;
    private final SmsService smsService;
    private final WhatsappService whatsappService;

    public CRMController(AreaRepository areaRepository, AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository, SmsService smsService, WhatsappService whatsappService) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
        this.customerVisitRepository = customerVisitRepository;
        this.smsService = smsService;
        this.whatsappService = whatsappService;
    }

    @GetMapping
    public ResponseEntity<List<Area>> searchAgent(@RequestParam int pinCode) {
        List<Area> areas = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PutMapping
    public String allocateAgent(@RequestParam long customerId, @RequestParam long agentId) {
        Optional<Agent> opAgent = agentRepository.findById(agentId);
        Agent agent = null;
        if (opAgent.isPresent()) {
            agent = opAgent.get();
        }
        CustomerVisit customerVisit = customerVisitRepository.findById(customerId).get();
        customerVisit.setAgent(agent);
        customerVisitRepository.save(customerVisit);
       smsService.sendSms("+919330432085", "Agent is now Allocated");
       whatsappService.sendWhatsappMessage("+919330432085", "Agent is now Allocated");
        return "Agent is now allocated";
    }
}