package com.app.service.carService;

import com.app.entity.cars.Year;
import com.app.repository.YearRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YearService {

    private final YearRepository yearRepository;

    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    public List<Year> getAllYears() {
        return yearRepository.findAll();
    }

    public Page<Year> getYears(Pageable pageable) {
        return yearRepository.findAll(pageable);
    }

    public Optional<Year> getYearById(Long id) {
        return yearRepository.findById(id);
    }

    public Year saveYear(Year year) {
        return yearRepository.save(year);
    }

    public void deleteYear(Long id) {
        yearRepository.deleteById(id);
    }
}
