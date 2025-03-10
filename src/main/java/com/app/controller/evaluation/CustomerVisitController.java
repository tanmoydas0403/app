package com.app.controller.evaluation;

import com.app.entity.evaluation.CustomerVisit;
import com.app.service.evaluation.CustomerVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer-visits")
public class CustomerVisitController {

    @Autowired
    private CustomerVisitService customerVisitService;

    @PostMapping
    public ResponseEntity<CustomerVisit> addCustomerVisit(@RequestBody CustomerVisit customerVisit) {
        CustomerVisit savedCustomerVisit = customerVisitService.addCustomerVisit(customerVisit);
        return ResponseEntity.ok(savedCustomerVisit);
    }
}