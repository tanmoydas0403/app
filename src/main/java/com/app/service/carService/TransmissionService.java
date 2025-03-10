package com.app.service.carService;

import com.app.entity.cars.Transmission;
import com.app.repository.TransmissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransmissionService {

    private final TransmissionRepository transmissionRepository;


    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public List<Transmission> getAllTransmission() {
        return transmissionRepository.findAll();
    }

    public Page<Transmission> getTransmission(Pageable pageable) {
        return transmissionRepository.findAll(pageable);
    }

    public Optional<Transmission> getTransmissionById(Long id) {
        return transmissionRepository.findById(id);
    }

    public Transmission saveTransmission(Transmission transmission) {
        return transmissionRepository.save(transmission);
    }

    public void deleteTransmission(Long id) {
        transmissionRepository.deleteById(id);
    }
}
