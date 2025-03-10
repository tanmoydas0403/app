package com.app.controller.carController;

import com.app.entity.cars.Transmission;
import com.app.service.carService.TransmissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transmissions")
public class TransmissionController {
    private final TransmissionService transmissionService;

    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }
    @GetMapping
    public List<Transmission> getAllTransmissions() {
        return transmissionService.getAllTransmission();
    }

    @GetMapping("/paginated")
    public Page<Transmission> getTransmissions(Pageable pageable){
        return transmissionService.getTransmission(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transmission> getTransmissionById(@PathVariable Long id){
        Optional<Transmission> transmission = transmissionService.getTransmissionById(id);
        return transmission.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transmission createTransmission(@RequestBody Transmission transmission){
        return transmissionService.saveTransmission(transmission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transmission> updateTransmission(
            @PathVariable Long id,
            @RequestBody Transmission transmissionDetail
    ){
        Optional<Transmission> transmission = transmissionService.getTransmissionById(id);
        if(transmission.isPresent()){
            Transmission updateTransmission = transmission.get();
            updateTransmission.setType(transmissionDetail.getType());
            return ResponseEntity.ok(transmissionService.saveTransmission(updateTransmission));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransmission(@PathVariable Long id){
        transmissionService.deleteTransmission(id);
        return ResponseEntity.noContent().build();
    }
}
