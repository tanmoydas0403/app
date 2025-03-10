package com.app.service.evaluation;

import com.app.entity.evaluation.CustomerVisit;
import com.app.repository.evaluation.CustomerVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerVisitService {

    @Autowired
    private CustomerVisitRepository customerVisitRepository;

    public CustomerVisit addCustomerVisit(CustomerVisit customerVisit) {
        return customerVisitRepository.save(customerVisit);
    }
}